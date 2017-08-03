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
import org.apache.commons.codec.binary.Base64;

/**
 * Summary
 *
 * This class manage the sigfox contracts Component from the SigfoxApi
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
    // Get the list of all the available contract on your account. including the
    // active & non activ contracts.
 /*   public List<SigfoxApiContractInformation> getSigfoxAllContract() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SigfoxApiContractList> response =
                restTemplate.exchange(
                        this.connectionString(
                                "contracts",
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiContractList.class);
        SigfoxApiContractList contracts = response.getBody();

        log.info(contracts.toString());
        return Arrays.asList(contracts.getData());

    }
*/
    // ========================================================================
    // Get a specific contract id information
    public SigfoxApiDeviceInformation getSigfoxDevice(String id) {

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

        log.info("getSigfoxDevice by id ("+id+") : "+device.toString());
        return device;

    }





}
