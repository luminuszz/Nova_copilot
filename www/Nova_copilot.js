var exec = require('cordova/exec');

var PLUGIN_ID = "Nova_copilot";


var Nova_copilot =  {}


Nova_copilot.coolMethod = function (arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'coolMethod', [arg0]);
}

Nova_copilot.isSDKSetup = function (arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'isSDKSetup', [arg0]);
}

Nova_copilot.setUp = function (arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'setUp', [arg0]);
}



module.exports = Nova_copilot
