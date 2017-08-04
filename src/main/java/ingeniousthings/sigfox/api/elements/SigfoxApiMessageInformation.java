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


/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a Message sent by a device.
 *
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *          "device" : "002C",
 *          "time" : 1343321977,
 *          "data" : "3235353843fc",
 *          "snr" : "38.2",
 *          "computedLocation": {
 *              "lat" : 43.45,
 *              "lng" : 6.54,
 *              "radius": 500
 *          },
 *          "linkQuality" : "GOOD",
 *          "downlinkAnswerStatus" : {
 *              "data" : "1511000a00007894"
 *          }
 *  }
 * @author Paul Pinault
 */
public class SigfoxApiMessageInformation {

    public static final String LINKQUALITY_LIMIT = "LIMIT";
    public static final String LINKQUALITY_AVERAGE = "AVERAGE";
    public static final String LINKQUALITY_GOOD = "GOOD";
    public static final String LINKQUALITY_EXCELLENT = "EXCELLENT";

    public class MessageLocation {
        double lat;
        double lng;
        int radius;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        @Override
        public String toString() {
            return "MessageLocation{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    ", radius=" + radius +
                    '}';
        }
    }
    public class DownlinkMessage {
        protected String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "DownlinkMessage{" +
                    "data='" + data + '\'' +
                    '}';
        }
    }

    protected String    device;
    protected long      time;
    protected String    data;
    protected double    snr;
    protected MessageLocation   computedLocation;
    protected String    linkQuality;
    protected DownlinkMessage   downlinkAnswerStatus;


    // ------------------------------------------------------------------
    // Advanced function




    // ------------------------------------------------------------------
    // Generated Getter & Setters

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getSnr() {
        return snr;
    }

    public void setSnr(double snr) {
        this.snr = snr;
    }

    public ingeniousthings.sigfox.api.elements.SigfoxApiMessageInformation.MessageLocation getComputedLocation() {
        return computedLocation;
    }

    public void setComputedLocation(ingeniousthings.sigfox.api.elements.SigfoxApiMessageInformation.MessageLocation computedLocation) {
        this.computedLocation = computedLocation;
    }

    public String getLinkQuality() {
        return linkQuality;
    }

    public void setLinkQuality(String linkQuality) {
        this.linkQuality = linkQuality;
    }

    public ingeniousthings.sigfox.api.elements.SigfoxApiMessageInformation.DownlinkMessage getDownlinkAnswerStatus() {
        return downlinkAnswerStatus;
    }

    public void setDownlinkAnswerStatus(ingeniousthings.sigfox.api.elements.SigfoxApiMessageInformation.DownlinkMessage downlinkAnswerStatus) {
        this.downlinkAnswerStatus = downlinkAnswerStatus;
    }


    // ------------------------------------------------------
    // Serialization


    @Override
    public String toString() {
        String str = "SigfoxApiMessageInformation{" +
                "device='" + device + '\'' +
                ", time=" + time +
                ", data='" + data + '\'' +
                ", snr=" + snr +
                ", linkQuality='" + linkQuality + '\'';

        if ( computedLocation != null ) {
            str += ", computedLocation=" + computedLocation;
        }
        if ( downlinkAnswerStatus != null ) {
            str += ", downlinkAnswerStatus=" + downlinkAnswerStatus;
        }

        str += '}';
        return str;
    }
}


