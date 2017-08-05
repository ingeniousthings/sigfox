# Sigfox

[![Build Status](https://travis-ci.org/ingeniousthings/sigfox.svg?branch=master)](https://travis-ci.org/ingeniousthings/sigfox)

This lib ease Sigfox concepts manipulation.

# ------------------------------------------------------------------------------------------
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

# ------------------------------------------------------------------------------------------
# Devicetype list & details

SigfoxApiDeviceType d = new SigfoxApiDeviceType("your_api_login","your_api_password");
// return a structure with all the DeviceType information
d.getSigfoxAllDeviceType();  

// return detailed information for a given device type, including callback list
d.getSigfoxDeviceTypeById("your_device_type_is");	


# ------------------------------------------------------------------------------------------
# Contract

Get the list of contract & validContract. From this you can also obtain the groupId needed to create a deviceType

SigfoxApiContract contracts = new SigfoxApiContract("your_api_login","your_api_password");
        // Get a list of the contract actually valid
        contracts.getSigfoxValidContract();
        // Get a specific contract  
        contracts.getSigfoxContract("your_contract_id");
        // Get the groupId associated to a contract
        String group = contracts.getSigfoxGroupFromContract("your_contract_id");

# ------------------------------------------------------------------------------------------
# Devices

Get information from a specific device
SigfoxApiDevice devices = new SigfoxApiDevice("your_api_login","your_api_password");
        // Get information about one specific deviceId
        SigfoxApiDeviceInformation device = devices.getSigfoxDevice("DEVICEID");
        // Get all the devices attached to a specified device type - paging managed.
        List<SigfoxApiDeviceInformation> liste = devices.getSigfoxDevicesForDeviceType("your_device_type");

        // get for a given device the information + the last metrics (message sent)
        SigfoxApiDeviceInformation device = devices.getSigfoxDevice("Your_device_id",true);

        // Get for a given device the history of the consumption, day by day for a given year
        SigfoxApiConsumptionInformationList consumptions = devices.getSigfoxDeviceConsumption("Your_device_id",Year_you_want);


# ------------------------------------------------------------------------------------------
# Messages

Get the Message history for a device. The solution manage paging to provide in one array all the messages.
Note : the Api provide much less information than the callback interface. 
SigfoxApiMessage messagesApi = new SigfoxApiMessage("your_api_login","your_api_password");
     List<SigfoxApiMessageInformation> messages = messagesApi.getSigfoxMessagesForDevice("your_device_id",since_this_epoc_date);



