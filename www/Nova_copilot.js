var exec = require('cordova/exec');

var PLUGIN_ID = "Nova_copilot";

function Nova_copilot() {
}


Nova_copilot.prototype.coolMethod = function (arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'coolMethod', [arg0]);
}

Nova_copilot.prototype.isSDKSetup = function (arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'isSDKSetup', [arg0]);
}

Nova_copilot.prototype.setUp = function (sdkKey, driver_id, success, error) {
    exec(success, error, PLUGIN_ID, 'setUp', [sdkKey, driver_id]);
}

Nova_copilot.prototype.startManualDrive = function (arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'startManualDrive', [arg0]);
}

Nova_copilot.prototype.stopManualDrive = function (arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'stopManualDrive', [arg0]);
}
Nova_copilot.prototype.ListenDriveEvents = function (success, error) {
    exec(success, error, PLUGIN_ID, 'ListenDriveEvents', []);
}








Nova_copilot.install = function () {
  if (!window.plugins) {
    window.plugins = {};

  }
  cordova.fireDocumentEvent("driver_events")


  window.plugins.Nova_copilot = new Nova_copilot();


  return window.plugins.Nova_copilot;
};
    
cordova.addConstructor(Nova_copilot.install);

