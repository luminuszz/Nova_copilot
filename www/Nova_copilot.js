var exec = require('cordova/exec');

var PLUGIN_ID = "Nova_copilot";


window.coolMethod = function (arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'coolMethod', [arg0]);
}

window.isSDKSetup = function (arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'isSDKSetup', [arg0]);
}

window.setUp = function (arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'setUp', [arg0]);
}
