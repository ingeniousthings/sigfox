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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Summary
 *
 * Parse a sigfox incoming callback structure to extract and qualify the data
 * Later this object will be used to be map to a specific object. This allows to have
 * a common capture point whatever is the message type source.
 * The objective is to simplify the way we implement the callback in sigfox.
 * ----------------------------------------------------------------------------------
 * Support :
 *  - DATA POST
 *  - SERVICE POST
 *  - ERROR POST
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

    private boolean _seq = false;
    private int seq;

    private boolean _lat = false;
    private int lat;

    private boolean _lng = false;
    private int lng;


    // -------------------------------------------------------
    // Radio & network elements

    private boolean _duplicate = false;
    private boolean duplicate;

    private boolean _signal = false;
    private double signal;

    private boolean _station = false;
    private String station;

    private boolean _avgSignal = false;
    private double avgSignal;

    private boolean _rssi = false;
    private double rssi;

    // -------------------------------------------------------
    // Data specific messages
    private boolean _data = false;
    private String data;

    private boolean _ack = false;
    private boolean ack;

    // -------------------------------------------------------
    // Service specific messages
    private boolean _temp = false;
    private double  temp;

    private boolean _batt = false;
    private double batt;

    private boolean _infoCode = false;
    private int infoCode;

    private boolean _infoMessage = false;
    private String infoMessage;

    private boolean _downlinkAck = false;
    private String downlinkAck;

    private boolean _downlinkOverusage = false;
    private boolean downlinkOverusage;

    // -------------------------------------------------------
    // Error specific messages
    private boolean _info = false;
    private String info;

    private boolean _severity = false;
    private String severity;

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
        } else {
            log.error("Received invalid device id ("+device+")");
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

    public double getSignal() {
        return signal;
    }
    public void setSignal(double signal) {
        this.signal = signal;
        this._signal = true;
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

    public int getSeq() {
        return seq;
    }
    public void setSeq(int seq) {
        this.seq = seq;
        this._seq = true;
    }

    public double getAvgSignal() {
        return avgSignal;
    }
    public void setAvgSignal(double avgSignal) {
        this.avgSignal = avgSignal;
        this._avgSignal = true;
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

    public double getTemp() {
        return temp;
    }
    public void setTemp(double temp) {
        this.temp = temp;
        this._temp = true;
    }

    public double getBatt() {
        return batt;
    }
    public void setBatt(double batt) {
        this.batt = batt;
        this._batt = true;
    }

    public int getInfoCode() {
        return infoCode;
    }
    public void setInfoCode(int infoCode) {
        this.infoCode = infoCode;
        this._infoCode = true;
    }

    public String getInfoMessage() {
        return infoMessage;
    }
    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
        this._infoMessage = true;
    }

    public String getDownlinkAck() {
        return downlinkAck;
    }
    public void setDownlinkAck(String downlinkAck) {
        this.downlinkAck = downlinkAck;
        this._downlinkAck = true;
    }

    public boolean isDownlinkOverusage() {
        return downlinkOverusage;
    }
    public void setDownlinkOverusage(boolean downlinkOverusage) {
        this.downlinkOverusage = downlinkOverusage;
        this._downlinkOverusage = true;
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
        this._info = true;
    }

    public String getSeverity() {
        return severity;
    }
    public void setSeverity(String severity) {
        this.severity = severity;
        this._severity = true;
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
        ret += (_seq)?(", seq=" + seq):"";
        ret += (_data)?(", data='" + data + '\''):"";
        ret += (_duplicate)?(", duplicate=" + duplicate):"";
        ret += (_station)?(", station='" + station + '\''):"";
        ret += (_signal)?(", signal=" + signal):"";
        ret += (_avgSignal)?(", avgSignal=" + avgSignal):"";
        ret += (_rssi)?(", rssi=" + rssi):"";
        ret += (_lat)?(", lat=" + lat):"";
        ret += (_lng)?(", lng=" + lng):"";
        ret += (_ack)?(", ack=" + ack):"";
        ret += (_temp)?(", temp=" + temp):"";
        ret += (_batt)?(", batt=" + batt):"";
        ret += (_infoCode)?(", infoCode=" + infoCode):"";
        ret += (_infoMessage)?(", infoMessage=" + infoMessage):"";
        ret += (_downlinkAck)?(", downlinkAck=" + downlinkAck):"";
        ret += (_downlinkOverusage)?(", downlinkOverusage=" + downlinkOverusage):"";
        ret += (_info)?(", info=" +info):"";
        ret += (_severity)?(", severity=" + severity):"";
        ret += '}';
        return ret;

    }
}
