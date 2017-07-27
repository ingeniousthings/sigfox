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

import java.lang.Override;
import java.lang.String;

/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a coverage redundancy. (number of antennas covereing the area)
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *      "id" : "4d3091a05ee16b3cc86699ab",
 *      "name" : "Sigfox test device",
 *      "group" : "4d39a4c9e03e6b3c430e2188",
 *      "description" : "Little things in the black boxes",
 *      "keepAlive" : 0,
 *      "payloadType" : "Geolocation",
 *      "contract" : "523b1d10d777d3f5ae038a02"
 *  }
 *
 * @author Paul Pinault
 */
public class SigfoxApiDeviceTypeInformation {

    private String id;
    private String name;
    private String group;
    private String description;
    private int    keepAlive;
    private String payloadType;
    private String contract;

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

    public void setContract(String contract) {
        this.contract = contract;
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
                '}';
    }

}