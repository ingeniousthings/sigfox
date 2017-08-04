/*
 *
 *  Copyright 2017 Ingeniousthings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
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
import java.util.List;

import org.apache.commons.codec.binary.Base64;

/**
 * Summary
 *
 * This class manage the sigfox Device Component from the SigfoxApi
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
 * ----------------------------------------------------------------------------------
 *
 * @author Paul Pinault
 */
public class SigfoxApiDevice extends SigfoxApiConnector {

    public SigfoxApiDevice(String login, String password) {
        super(login, password);
    }

    // ========================================================================
    // Get the list of all the available devices for a given deviceType
    public List<SigfoxApiDeviceInformation> getSigfoxDevicesForDeviceType(String dtid) {

        RestTemplate restTemplate = new RestTemplate();
        String url = null;
        SigfoxApiDeviceInformationList devices = null;
        ArrayList<SigfoxApiDeviceInformation> ret = new ArrayList<SigfoxApiDeviceInformation>();

        do {
            if ( url == null ) {
                url = "devicetypes/" + dtid + "/devices";
            } else {
                if ( devices == null ) return null;
                url = devices.getPaging().getNext();
                url = url.substring(SigfoxApiConnector.API_PROTOCOL.length() + SigfoxApiConnector.API_BACKEND_URL.length());
            }
            ResponseEntity<SigfoxApiDeviceInformationList> response =
                    restTemplate.exchange(
                            this.connectionString(
                                    url,
                                    null
                            ),
                            HttpMethod.GET,
                            this.generateRequestHeaders(),
                            SigfoxApiDeviceInformationList.class);
            devices = response.getBody();
            log.info(devices.toString());
            for ( int i =0 ; i < devices.getData().length ; i++ ) {
                ret.add(devices.getData()[i]);
            }

        } while ( devices.getPaging().getNext() != null );

        return ret;
    }

    // ========================================================================
    // Get a specific device id information with the associated message metrics or not
    public SigfoxApiDeviceInformation getSigfoxDevice(String id) {
        return getSigfoxDevice(id,false);
    }
    public SigfoxApiDeviceInformation getSigfoxDevice(String id, boolean metric) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SigfoxApiDeviceInformation> response =
                restTemplate.exchange(
                        this.connectionString(
                                "devices/" + id,
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiDeviceInformation.class);
        SigfoxApiDeviceInformation device = response.getBody();

        if ( metric ) {
            ResponseEntity<SigfoxApiMessageMetric> response2 =
                    restTemplate.exchange(
                            this.connectionString(
                                    "devices/" + id+ "/messages/metric",
                                    null
                            ),
                            HttpMethod.GET,
                            this.generateRequestHeaders(),
                            SigfoxApiMessageMetric.class);
            device.setMetric(response2.getBody());
        }

        log.info("getSigfoxDevice by id ("+id+") : "+device.toString());
        return device;

    }

    // ========================================================================
    // Get a specific device id information




}