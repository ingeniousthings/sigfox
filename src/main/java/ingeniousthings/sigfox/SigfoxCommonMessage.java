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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.System;
import java.util.regex.*;
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

    protected static final Logger log = LoggerFactory.getLogger(SigfoxCommonMessage.class);

    public static final String TYPE_DATA = "data";
    public static final String TYPE_SERVICE_STATUS = "srv_status";
    public static final String TYPE_SERVICE_ACK = "srv_ack";
    public static final String TYPE_ERROR = "error";

    public enum MessageType {
        DATA,SERVICE_STATUS,SERVICE_ACK,ERROR, UNKNOWN
    };

    // --------------------------------------------------------
    // interal elements
    @JsonIgnore private long messageUid = 0L;
    @JsonIgnore private boolean _messageUid = false;

    // --------------------------------------------------------
    // Common mandatory elements

    @JsonIgnore private boolean _device = false;
    private String device;

    @JsonIgnore private boolean _time = false;
    private long time;

    @JsonIgnore private boolean _type = false;
    private String type;
    @JsonIgnore private MessageType messagetype = MessageType.UNKNOWN;

    // -------------------------------------------------------
    // Other common but not especially mandatory

    @JsonIgnore private boolean _seq = false;
    private int seq;

    @JsonIgnore private boolean _lat = false;
    private int lat;

    @JsonIgnore private boolean _lng = false;
    private int lng;


    // -------------------------------------------------------
    // Radio & network elements

    @JsonIgnore private boolean _duplicate = false;
    private boolean duplicate;

    @JsonIgnore private boolean _signal = false;
    private double signal;

    @JsonIgnore private boolean _station = false;
    private String station;

    @JsonIgnore private boolean _avgSignal = false;
    private double avgSignal;

    @JsonIgnore private boolean _rssi = false;
    private double rssi;

    // -------------------------------------------------------
    // Data specific messages
    @JsonIgnore private boolean _data = false;
    private String data;

    @JsonIgnore private boolean _ack = false;
    private boolean ack;

    // -------------------------------------------------------
    // Service specific messages
    @JsonIgnore private boolean _temp = false;
    private double  temp;

    @JsonIgnore private boolean _batt = false;
    private double batt;

    @JsonIgnore private boolean _infoCode = false;
    private int infoCode;

    @JsonIgnore private boolean _infoMessage = false;
    private String infoMessage;

    @JsonIgnore private boolean _downlinkAck = false;
    private String downlinkAck;

    @JsonIgnore private boolean _downlinkOverusage = false;
    private boolean downlinkOverusage;

    // -------------------------------------------------------
    // Error specific messages
    @JsonIgnore private boolean _info = false;
    private String info;

    @JsonIgnore private boolean _severity = false;
    private String severity;

    // ==================================================================================
    // Advanced functions
    // ----------------------------------------------------------------------------------
    //
    // ==================================================================================

    // -------------------------------------------------------------------------------
    // Verify the data received to be compliant with the expectation
    public boolean isValidMessage() {

        if ( !_device || !_time || !_type ) return false;

        switch ( this.messagetype ) {
            case DATA:
                if ( !_seq || !_data ) return false;
                break;
            case SERVICE_STATUS:
                if ( !_seq || !_temp || !_batt ) return false;
                break;
            case SERVICE_ACK:
                if ( !_infoCode ) return false;
                break;
            case ERROR:
                if ( !_info ) return false;
                break;
            default:
                return false;
        }
        return true;
    }



    // -------------------------------------------------------------------------------
    // Compose a message UID specific to a message (but common to message duplicates
    // Format is 8 bytes (long)
    // | DeviceId ( 4byte reverted ) 31 bits | 2 b MessageType | time (4 bytes) 31 bits
    public void composeMessageUid() {

        long messageType = 0;
        switch ( this.messagetype ) {
            case DATA:
                messageType = 3;
                break;
            case SERVICE_STATUS:
                messageType = 1;
                break;
            case SERVICE_ACK:
                messageType = 2;
                break;
            case ERROR:
                messageType = 0;
                break;
        }
        long intDevice = Long.parseLong(this.device,16);
        long intRevertDevice = 0;
        intRevertDevice |= (intDevice & 1);
        for ( int i = 1 ; i < 32 ; i++) {
            intRevertDevice = intRevertDevice << 1;
            intDevice = intDevice >> 1;
            intRevertDevice |= (intDevice & 1);
        }

        this.messageUid  = ( this.time >> 1 ) & 0x7FFFFFFF;
        this.messageUid |= ( intRevertDevice << 33 );
        this.messageUid |= ( messageType & 3 ) << 31;
        this._messageUid = true;

    }



    // ==================================================================================
    // Getter & Setters
    // ----------------------------------------------------------------------------------
    //
    // ==================================================================================


    public long getMessageUid() {
        return messageUid;
    }

    public void setMessageUid(long messageUid) {
        this.messageUid = messageUid;
    }

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
        if ( this.type.compareToIgnoreCase(TYPE_DATA) == 0 ) {
            this.messagetype = MessageType.DATA;
        } else if ( this.type.compareToIgnoreCase(TYPE_SERVICE_STATUS) == 0) {
            this.messagetype = MessageType.SERVICE_STATUS;
        } else if ( this.type.compareToIgnoreCase(TYPE_SERVICE_ACK) == 0) {
            this.messagetype = MessageType.SERVICE_ACK;
        } else if ( this.type.compareToIgnoreCase(TYPE_ERROR) == 0) {
            this.messagetype = MessageType.ERROR;
        } else  this.messagetype = MessageType.UNKNOWN;
    }

    public ingeniousthings.sigfox.SigfoxCommonMessage.MessageType getMessagetype() {
        return messagetype;
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
        ret += (_messageUid)?(", messageUid=0x" + Long.toHexString(messageUid).toUpperCase()):"";
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
