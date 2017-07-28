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
 *                      id: "584871ac50057472d9f5d8ca",
 *                      channel: "URL" or "BATCH_URL" or "EMAIL",
 *                      callbackType: 0,                                        - 0 = data / 1 = service / 2 = error
 *                      payloadConfig: "",
 *                      callbackSubtype: 3,                                     - 2 = uplink / 3 = bidir / 0 = status / 4 = ack / 5 = repeater
 *                      enabled: true,
 *                      sendDuplicate: true,
 *                      dead: false,                                            - last callback call failed
 *                      urlPattern: "http://foo.bar?key=value&key=value",       - when channel URL or BATCH_URL
 *                      httpMethod: "GET" or "POST",                            - when channel URL or BATCH_URL
 *                      downlinkHook: true,                                     - when channel URL
 *                      headers: { }                                            - when channel URL (@TODO : see what can be the values)
 *                      linePattern: ??                                         - when channel BATCH_URL (@TODO : see what can be the values)
 *                      subject: ??                                             - when channel EMAIL (@TODO ...)
 *                      recipient: ??                                           - when channel EMAIL (@TODO ...)
 *                      message:??                                              - when channel EMAIL (@TODO ...)
 *  }
 * ----------------------------------------------------------------------------------
 * Support
 *  - channel "URL" only actually
 *  - callbacktype 0
 *
 * @author Paul Pinault
 */
public class SigfoxApiCallbackInformation {

    private String id;
    private String channel;
    private int callbacktype;
    private int callbackSubtype;
    private String payloadConfig;
    private boolean enabled;
    private boolean sendDuplicate;
    private boolean dead;
    private String urlPattern;
    private String httpMethod;
    private boolean downlinkHook;

    // ========================================================================
    // ADVANCED GETTER / SETTER
    // ========================================================================

    public boolean isUplink() {
        return (this.callbackSubtype == 2);
    }

    public boolean isBidir() {
        return (this.callbackSubtype == 3);
    }

    // ========================================================================
    // GENERATED GETTER / SETTER
    // ========================================================================

    public boolean isDownlinkHook() {
        return downlinkHook;
    }

    public void setDownlinkHook(boolean downlinkHook) {
        this.downlinkHook = downlinkHook;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getCallbacktype() {
        return callbacktype;
    }

    public void setCallbacktype(int callbacktype) {
        this.callbacktype = callbacktype;
    }

    public String getPayloadConfig() {
        return payloadConfig;
    }

    public void setPayloadConfig(String payloadConfig) {
        this.payloadConfig = payloadConfig;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSendDuplicate() {
        return sendDuplicate;
    }

    public void setSendDuplicate(boolean sendDuplicate) {
        this.sendDuplicate = sendDuplicate;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public int getCallbackSubtype() {
        return callbackSubtype;
    }

    public void setCallbackSubtype(int callbackSubtype) {
        this.callbackSubtype = callbackSubtype;
    }

    @Override
    public String toString() {
        return "SigfoxApiCallbackInformation{" +
                "id='" + id + '\'' +
                ", channel='" + channel + '\'' +
                ", callbacktype=" + callbacktype +
                ", callbackSubtype=" + callbackSubtype +
                ", payloadConfig='" + payloadConfig + '\'' +
                ", enabled=" + enabled +
                ", sendDuplicate=" + sendDuplicate +
                ", dead=" + dead +
                ", urlPattern='" + urlPattern + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", downlinkHook=" + downlinkHook +
                '}';
    }
}
