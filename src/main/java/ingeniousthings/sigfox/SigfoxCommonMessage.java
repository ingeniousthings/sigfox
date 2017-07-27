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

package ingeniousthings.sigfox;
import java.util.regex.*;

/**
 * Summary
 *
 * Parse a sigfox incoming callback structure to extract and qualify the data
 * Later this object will be used to be map to a specifi object. This allows to have
 * a common capture point whatever is the message type source.
 * The objective is to simplify the way we implement the callback in sigfox.
 * ----------------------------------------------------------------------------------
 * Support :
 *  - DATA POST
 *
 *
 * @author Paul Pinault
 */
public class SigfoxCommonMessage {

    // --------------------------------------------------------
    // Common mandatory elements

    private boolean _device = false;
    private String device;

    private boolean _time = false;
    private long time;

    private boolean _type = false;
    private String type;

    // -------------------------------------------------------
    // Other common but not especially mandatory

    private boolean _seqNumber = false;
    private int seqNumber;

    private boolean _lat = false;
    private int lat;

    private boolean _lng = false;
    private int lng;


    // -------------------------------------------------------
    // Radio & network elements

    private boolean _duplicate = false;
    private boolean duplicate;

    private boolean _snr = false;
    private double snr;

    private boolean _station = false;
    private String station;

    private boolean _avgSnr = false;
    private double avgSnr;

    private boolean _rssi = false;
    private double rssi;

    // -------------------------------------------------------
    // Data specific messages
    private boolean _data = false;
    private String data;

    private boolean _ack = false;
    private boolean ack;


    // ==================================================================================
    // Getter & Setters
    // ----------------------------------------------------------------------------------
    //
    // ==================================================================================

    public String getDevice() {
        return device;
    }
    public void setDevice(String device) {
        if ( device.matches("^[0-9A-F]+$") ) {
            this.device = device;
            this._device = true;
        }
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
        this._type = true;
    }


    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
        this._time = true;
    }

    public boolean isDuplicate() {
        return duplicate;
    }
    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
        this._duplicate = true;
    }

    public double getSnr() {
        return snr;
    }
    public void setSnr(double snr) {
        this.snr = snr;
        this._snr = true;
    }

    public String getStation() {
        return station;
    }
    public void setStation(String station) {
        this.station = station;
        this._station = true;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
        this._data=true;
    }

    public int getLat() {
        return lat;
    }
    public void setLat(int lat) {
        this.lat = lat;
        this._lat = true;
    }

    public int getLng() {
        return lng;
    }
    public void setLng(int lng) {
        this.lng = lng;
        this._lng = true;
    }

    public int getSeqNumber() {
        return seqNumber;
    }
    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
        this._seqNumber = true;
    }

    public double getAvgSnr() {
        return avgSnr;
    }
    public void setAvgSnr(double avgSnr) {
        this.avgSnr = avgSnr;
        this._avgSnr = true;
    }

    public boolean isAck() {
        return ack;
    }
    public void setAck(boolean ack) {
        this.ack = ack;
        this._ack = true;
    }

    public double getRssi() {
        return rssi;
    }
    public void setRssi(double rssi) {
        this.rssi = rssi;
        this._rssi = true;
    }

    // ==================================================================================
    // Serialize
    // ----------------------------------------------------------------------------------
    //
    // ==================================================================================

    @Override
    public String toString() {
        String ret = "SigfoxCommonMessage{";
        ret += (_device)?("device='" + device + '\''):"";
        ret += (_time)?(", time=" + time):"";
        ret += (_type)?(", type='" + type + '\''):"";
        ret += (_duplicate)?(", duplicate=" + duplicate):"";
        ret += (_snr)?(", snr=" + snr):"";
        ret += (_station)?(", station='" + station + '\''):"";
        ret += (_data)?(", data='" + data + '\''):"";
        ret += (_lat)?(", lat=" + lat):"";
        ret += (_lng)?(", lng=" + lng):"";
        ret += (_seqNumber)?(", seqNumber=" + seqNumber):"";
        ret += (_avgSnr)?(", avgSnr=" + avgSnr):"";
        ret += (_ack)?(", ack=" + ack):"";
        ret += (_rssi)?(", rssi=" + rssi):"";
        ret += '}';
        return ret;

    }
}
