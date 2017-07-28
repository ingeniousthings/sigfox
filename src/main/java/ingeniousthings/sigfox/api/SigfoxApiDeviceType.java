/*
 * Copyright 2017 Ingeniousthings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ingeniousthings.sigfox.api;

import ingeniousthings.sigfox.api.elements.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;

import java.lang.System;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;

/**
 * Summary
 *
 * This class manage the DeviceType Component from the SigfoxApi
 * ----------------------------------------------------------------------------------
 * Requires:
 *   This class requieres SpringBoot framework
 *   This class requieres
 *     compile("org.apache.httpcomponents:httpcore:4.4.6")
 *     compile("commons-codec:commons-codec:1.10")
 * ----------------------------------------------------------------------------------
 * Support :
 *
 *
 * @author Paul Pinault
 */
public class SigfoxApiDeviceType extends SigfoxApiConnector {

    public SigfoxApiDeviceType(String login, String password) {
        super(login,password);
    }

    // ========================================================================
    // Get the list of all the Devicetype you can access to
    // This returns all the information about the device type itself but not the
    // associated callback
    public List<SigfoxApiDeviceTypeInformation> getSigfoxAllDeviceType() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SigfoxApiDeviceTypeInformationList> response =
                restTemplate.exchange(
                        this.connectionString(
                                "devicetypes",
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        /*new ParameterizedTypeReference<List<SigfoxApiDeviceTypeInformation>>(){}*/
                        SigfoxApiDeviceTypeInformationList.class);
        SigfoxApiDeviceTypeInformationList devicetype = response.getBody();

        log.info(devicetype.toString());
        return Arrays.asList(devicetype.getData());
    }

    // ========================================================================
    // Get the information about a Specific Devicetype based on its Id
    // the input is a deviceType id. this function also request the information
    // about the callbacks when type is custom
    public SigfoxApiDeviceTypeInformation getSigfoxDeviceTypeById(String id) {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<SigfoxApiDeviceTypeInformation> response =
                    restTemplate.exchange(
                            this.connectionString(
                                    "devicetypes/"+id,
                                    null
                            ),
                            HttpMethod.GET,
                            this.generateRequestHeaders(),
                            SigfoxApiDeviceTypeInformation.class);
            SigfoxApiDeviceTypeInformation devicetype = response.getBody();


            ResponseEntity<SigfoxApiCallbackList> response2 =
                restTemplate.exchange(
                        this.connectionString(
                                "devicetypes/"+id+"/callbacks",
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiCallbackList.class);
            SigfoxApiCallbackList callbacks = response2.getBody();
            devicetype.setCallback(callbacks);


            log.info(devicetype.toString());
            return devicetype;
    }

    // ========================================================================
    // Create a device type from an initialized object
    public SigfoxApiDeviceTypeInformation publishSigfoxDeviceType(SigfoxApiDeviceTypeInformation o) {
        RestTemplate restTemplate = new RestTemplate();

        // Create device type
        ResponseEntity<SigfoxApiDeviceTypeInformation> response =
                restTemplate.exchange(
                        this.connectionString(
                                "devicetypes/create",
                                null
                        ),
                        HttpMethod.POST,
                        this.generateRequestHeaders(true,o.toPublication()),
                        SigfoxApiDeviceTypeInformation.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            SigfoxApiDeviceTypeInformation _t = response.getBody();
            o.setId(_t.getId());

            // Create the associated callback
            String body = "[";
            List<SigfoxApiCallbackInformation> callbacks = o.getCallback().getAllCallbackAsList();
            for (SigfoxApiCallbackInformation callback : callbacks ) {
                if (body.length() > 1) body += ",";
                body += callback.toPublication();
            }
            body+="]";

            System.out.println(body);
            try {
                    ResponseEntity<String> response2 =
                            restTemplate.exchange(
                                    this.connectionString(
                                            "devicetypes/" + _t.getId() + "/callbacks/new",
                                            null
                                    ),
                                    HttpMethod.POST,
                                    this.generateRequestHeaders(true, body),
                                    String.class);
                    if (response2.getStatusCode() == HttpStatus.OK) {
                        String _s = response2.getBody();
                        System.out.println(_s);
                    } else {
                        log.error("Callback creation not possible, do nothing");
                    }
            } catch (HttpClientErrorException e) {
                    System.err.println(e);
                    log.error("Callback creation not possible, removing devicetype");
                    // delete the created devicetype
                    ResponseEntity<String> response3 =
                            restTemplate.exchange(
                                    this.connectionString(
                                            "devicetypes/delete",
                                            null
                                    ),
                                    HttpMethod.POST,
                                    this.generateRequestHeaders(true, o.toDelete()),
                                    String.class);
                    return null;
            }

        } else {
            log.error("Device type creation not possible");
            return null;
        }
        return o;

    }


}