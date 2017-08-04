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
package ingeniousthings.sigfox.api.elements;

import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;


/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a Device List
 *
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *    data : [ {
 *      "id" : "0B59",
 *      "name" : "Test 0B59",
 *      "type" : "4d3091a05ee16b3cc86699ab",        // Link to the device Type id
 *      "last" : 0,                                 // Last message sent ( EPOC in S)
 *      "averageSignal": 8.457,                     // based on last 25 messages
 *      "averageSnr": 8.457,                        //   ...
 *      "averageRssi": -125.967,                    //   ...
 *      "state": 0,
 *      "lat" : 43.45,
 *      "lng" : 1.54,
 *      "computedLocation": {                       // null when the device is not positionned by network
 *          "lat" : 43.45,
 *          "lng" : 6.54,
 *          "radius": 500
 *      },
 *      "activationTime": 1404096340556,
 *      "pac": "545CB3B17AC98BA4",
 *      "tokenType": "CONTRACT",
 *      "contractId": "7896541254789654aedfba4c",
 *      "tokenEnd": 1449010800000,
 *      "preventRenewal": false
 *     },{
 *        SigfoxApiDeviceInformation
 *     }
 *   ],
 *   paging : {
 *       next : "url for the next page"
 *   }
 *
 * @author Paul Pinault
 */
public class SigfoxApiDeviceInformationList {

    public class DeviceListPaging {
        protected String next;

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "DeviceListPaging{" +
                    "next='" + next + '\'' +
                    '}';
        }
    }

    protected SigfoxApiDeviceInformation[] data;
    protected DeviceListPaging paging;

    // ------------------------------------------------------
    // Getter & Setters

    public SigfoxApiDeviceInformation[] getData() {
        return data;
    }

    public void setData(SigfoxApiDeviceInformation[] data) {
        this.data = data;
    }

    public ingeniousthings.sigfox.api.elements.SigfoxApiDeviceInformationList.DeviceListPaging getPaging() {
        return paging;
    }

    public void setPaging(ingeniousthings.sigfox.api.elements.SigfoxApiDeviceInformationList.DeviceListPaging paging) {
        this.paging = paging;
    }


    // ------------------------------------------------------
    // Serialization

    @Override
    public String toString() {
        return "SigfoxApiDeviceInformationList{" +
                "data=" + Arrays.toString(data) +
                ", paging=" + paging +
                '}';
    }
}

