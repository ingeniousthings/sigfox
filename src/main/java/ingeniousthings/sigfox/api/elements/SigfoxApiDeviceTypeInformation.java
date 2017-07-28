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

package ingeniousthings.sigfox.api.elements;

import java.io.IOException;
import java.lang.Override;
import java.lang.String;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a device type. this object can handle a
 * link to associated callbacks.
 *
 * This class is also used to compose a Device Type from a Json external data with the objective
 * to be deployed automatically in the backend. Json data have same format but includes the callbackentries
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *      "id" : "4d3091a05ee16b3cc86699ab",
 *      "name" : "Sigfox test device",
 *      "group" : "4d39a4c9e03e6b3c430e2188",
 *      "description" : "Little things in the black boxes",
 *      "keepAlive" : 7200,
 *      "payloadType" : "None",
 *      "contract" : "523b1d10d777d3f5ae038a02"
 *  }
 *
 * @author Paul Pinault
 */
public class SigfoxApiDeviceTypeInformation {

    // -----------------------------------------
    // Internals
    // -----------------------------------------

    private String id;
    private String name;
    private String group;
    private String description;
    private int    keepAlive;
    private String payloadType;
    private String contract;
    private String contractId;          // because the sigfox Api change the name of the same variables...
    private int    downlinkMode;        // 0 : direct 1 : through callback
    private String downlinkDataString;
    private String alertEmail;

    private SigfoxApiCallbackList  callback;


    // -----------------------------------------
    // Object initialization from Json data
    // -----------------------------------------

    public static SigfoxApiDeviceTypeInformation createFromJson( String _json ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        SigfoxApiDeviceTypeInformation devicetype = mapper.readValue(_json, SigfoxApiDeviceTypeInformation.class);

        return devicetype;

    }

    // -----------------------------------------
    // String dump for deviceType creation with API
    // -----------------------------------------
    public String toPublication() {
        return ("{" +
                    " 'name' : '" + name + '\'' +
                    ", 'group' : '" + group + '\'' +
                    ", 'description' : '" + description + '\'' +
                    ", 'keepAlive' : " + keepAlive +
                    ", 'payloadType' : '" + payloadType + '\'' +
                    ", 'contract' : '" + contract + '\'' +
                    ", 'downlinkMode' : " + downlinkMode +
                    ", 'downlinkDataString' : '" + downlinkDataString + '\'' +
                    ", 'alertEmail' : '" + alertEmail + '\'' +
                    ", 'contractId' : '" + contractId + '\'' +
                "}").replace('\'','"');
    }

    public String toDelete() {
        return ("{" +
                " 'id' : '" + id + '\'' +
                "}").replace('\'','"');
    }

    // -----------------------------------------
    // Custom Getters & Setters
    // -----------------------------------------

    public void setContract(String contract) {
        this.contract = contract;
        this.contractId = contract;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
        this.contract = contractId;
    }

    // -----------------------------------------
    // Generated Getters & Setters
    // -----------------------------------------


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(String payloadType) {
        this.payloadType = payloadType;
    }

    public String getContract() {
        return contract;
    }

    public SigfoxApiCallbackList getCallback() {
        return callback;
    }

    public void setCallback(SigfoxApiCallbackList callback) {
        this.callback = callback;
    }

    public int getDownlinkMode() {
        return downlinkMode;
    }

    public void setDownlinkMode(int downlinkMode) {
        this.downlinkMode = downlinkMode;
    }

    public String getDownlinkDataString() {
        return downlinkDataString;
    }

    public void setDownlinkDataString(String downlinkDataString) {
        this.downlinkDataString = downlinkDataString;
    }

    public String getAlertEmail() {
        return alertEmail;
    }

    public void setAlertEmail(String alertEmail) {
        this.alertEmail = alertEmail;
    }

    public String getContractId() {
        return contractId;
    }

    @Override
    public String toString() {
        return "SigfoxApiDeviceTypeInformation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", description='" + description + '\'' +
                ", keepAlive=" + keepAlive +
                ", payloadType='" + payloadType + '\'' +
                ", contract='" + contract + '\'' +
                ", contractId='" + contractId + '\'' +
                ", downlinkMode=" + downlinkMode +
                ", downlinkDataString='" + downlinkDataString + '\'' +
                ", alertEmail='" + alertEmail + '\'' +
                ", callback=" + callback +
                '}';
    }

}