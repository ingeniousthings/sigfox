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
public class SigfoxApiContract extends SigfoxApiConnector {

    public SigfoxApiContract(String login, String password) {
        super(login, password);
    }


    // ========================================================================
    // Get the list of all the available contract on your account. including the
    // active & non activ contracts.
    public List<SigfoxApiContractInformation> getSigfoxAllContract() {

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

    // ========================================================================
    // Get the list of all the valid contract on your account. only active
    public List<SigfoxApiContractInformation> getSigfoxValidContract() {


        List<SigfoxApiContractInformation> contracts = getSigfoxAllContract();

        ArrayList<SigfoxApiContractInformation> validContract = new ArrayList<SigfoxApiContractInformation>();
        for (SigfoxApiContractInformation contract : contracts ) {
            if ( contract.getState() == 1 ) {
                validContract.add(contract);
            }
        }

        log.info("SigfoxApiContract.getSigfoxValidContract : "+ validContract.toString());
        return validContract;

    }

    // ========================================================================
    // Get a specific contract id information
    public SigfoxApiContractInformation getSigfoxContract(String cid) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SigfoxApiContractInformation> response =
                restTemplate.exchange(
                        this.connectionString(
                                "contracts/" + cid,
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiContractInformation.class);
        SigfoxApiContractInformation contract = response.getBody();

        log.info("SigfoxApiContract by id ("+cid+") : "+contract.toString());
        return contract;

    }

    // ========================================================================
    // Get the group id attached to a contract
    public String getSigfoxGroupFromContract(String cid) {
        String ret = getSigfoxContract(cid).getGroupId();
        log.info("SigfoxApiContract : groupId : ("+ret+") from contractId ("+cid+")");
        return ret;
    }



}