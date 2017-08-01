# Sigfox

[![Build Status](https://travis-ci.org/ingeniousthings/sigfox.svg?branch=master)](https://travis-ci.org/ingeniousthings/sigfox)

This lib ease Sigfox concepts manipulation.

# DeviceType & associated callback creation
You can define a json structure containing your device type & callback definition.
the lib will publish this structure to the sigfox API
ex : 
SigfoxApiDeviceTypeInformation dt = SigfoxApiDeviceTypeInformation.createFromJson(("" +
                    "{ 'name' : 'test_device_type'," +
                    "  'group' : 'your_group_id_here', " +
                    "  'description' : 'Test creation deviceType'," +
                    "  'keepAlive' : 0," +
                    "  'payloadType' : 'None'," +
                    "  'contractId' : 'your_contract_id_here'," +
                    "  'downlinkMode' : 1," +
                    "  'downlinkDataString' : ''," +
                    "  'alertEmail' : '', " +
                    "  'callback' : { " +
                    "  'data' : [ { " +

                    "        'channel' : 'URL'," +
                    "        'callbackType' : 0," +
                    "        'callbackSubtype' : 3," +
                    "        'url' : 'your_callback_url_here'," +
                    "        'httpMethod' : 'POST'," +
                    "        'enabled' : true," +
                    "        'sendDuplicate' : true," +
                    "        'sendSni' : true," +
                    "        'payloadConfig' : ''," +
                    "        'bodyTemplate' : '{ @device@ : @{device}@, @rssi@ : @{rssi}@ }'," +
                    "        'headers' : '{ §time§ : §{time}§ }', " +
                    "        'contentType' : 'application/json' " +
                    "       },{" +
                    "        'channel' : 'URL'," +
                    "        'callbackType' : 0," +
                    "        'callbackSubtype' : 3," +
                    "        'url' : 'your_callback_url_here'," +
                    "        'httpMethod' : 'GET'," +
                    "        'enabled' : true," +
                    "        'sendDuplicate' : true," +
                    "        'sendSni' : true, " +
                    "        'downlinkHook' : true " +
                    "       }]" +
                    "}" +
                    "}").replace('\'', '"'));

Then you can call the ingenious lib to create the devicetype & callbacks:

SigfoxApiDeviceType d = new SigfoxApiDeviceType("your_api_login","your_api_password");
d.publishSigfoxDeviceType(dt);

# devicetype list & details

SigfoxApiDeviceType d = new SigfoxApiDeviceType("your_api_login","your_api_password");
// return a structure with all the DeviceType information
d.getSigfoxAllDeviceType();  

// return detailed information for a given device type, including callback list
d.getSigfoxDeviceTypeById("your_device_type_is");	