var exec = require('cordova/exec');
var cordova = require('cordova')

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
    function  onSuccessCallback(message) {
        success(message);
    }

    exec(
        onSuccessCallback,
        error,
        PLUGIN_ID,
        'setUp',
        [sdkKey, driver_id]);
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

Nova_copilot.prototype.stopListenDriveEvents = function (success, error) {
    exec(success, error, PLUGIN_ID, 'stopListenDriveEvents', []);
}








Nova_copilot.install = function () {
  if (!window.plugins) {
    window.plugins = {};

  }
  window.plugins.Nova_copilot = new Nova_copilot();

  window.plugins.Nova_copilot.ListenDriveEvents(function (message) {
      alert(message);

    if(message) {
        cordova.fireWindowEvent("driver_events", {message: message})
    } else {
        cordova.fireWindowEvent("driver_events", {message: "No events"})
    }

  })


  return window.plugins.Nova_copilot;
};
    
cordova.addConstructor(Nova_copilot.install);

