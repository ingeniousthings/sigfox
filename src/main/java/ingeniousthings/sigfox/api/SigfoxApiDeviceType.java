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
import org.springframework.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;

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
            log.info(devicetype.toString());
            return devicetype;
    }



}