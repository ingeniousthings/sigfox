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
import java.util.Arrays;
import java.util.List;

/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a call back list corresponding to a
 * specified DeviceType
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *      "data" : [
 *                  {
 *                      id: "584871ac50057472d9f5d8ca",
 *                      channel: "URL",
 *                      callbackType: 0,
 *                      payloadConfig: "",
 *                      callbackSubtype: 3,
 *                      enabled: true,
 *                      sendDuplicate: true,
 *                      dead: false,
 *                      urlPattern: "http://foo.bar?key=value&key=value",
 *                      httpMethod: "GET",
 *                      downlinkHook: true,
 *                      headers: { }
 *                  }, {
 *                          [SigfoxApiCallbackInformation structure]
 *                  }
 *              ],
 *      "service" : [
 *                         [SigfoxApiCallbackInformation structure]
 *                  ],
 *      "error" :   [
 *                         [SigfoxApiCallbackInformation structure]
 *                  ]
 *  }
 *
 * @author Paul Pinault
 */
public class SigfoxApiCallbackList {

    private SigfoxApiCallbackInformation[] data;
    private SigfoxApiCallbackInformation[] service;
    private SigfoxApiCallbackInformation[] error;

    public SigfoxApiCallbackInformation[] getData() {
        return data;
    }

    public void setData(SigfoxApiCallbackInformation[] data) {
        this.data = data;
    }

    public SigfoxApiCallbackInformation[] getService() {
        return service;
    }

    public void setService(SigfoxApiCallbackInformation[] service) {
        this.service = service;
    }

    public SigfoxApiCallbackInformation[] getError() {
        return error;
    }

    public void setError(SigfoxApiCallbackInformation[] error) {
        this.error = error;
    }

    @Override
    public String toString() {
        String s =  "SigfoxApiCallbackList{" +
                    "data= [";
        List<SigfoxApiCallbackInformation> l = Arrays.asList(data);
        for ( SigfoxApiCallbackInformation o : l ){
            s+="{";
            s+= o.toString();
            s+="},";
        }
        s +=  "],service= [";
        List<SigfoxApiCallbackInformation> l = Arrays.asList(service);
        for ( SigfoxApiCallbackInformation o : l ){
            s+="{";
            s+= o.toString();
            s+="},";
        }
        s +=  "],error= [";
        List<SigfoxApiCallbackInformation> l = Arrays.asList(error);
        for ( SigfoxApiCallbackInformation o : l ){
            s+="{";
            s+= o.toString();
            s+="},";
        }
        s +=  "]}";

        return s;
    }
}