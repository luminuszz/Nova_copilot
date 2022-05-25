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

Nova_copilot.prototype.setUp = function (arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'setUp', [arg0]);
}

Nova_copilot.prototype.startManualDrive = function (arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'startManualDrive', [arg0]);
}




Nova_copilot.install = function () {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.Nova_copilot = new Nova_copilot();
    
  return window.plugins.Nova_copilot;
};
    
cordova.addConstructor(Nova_copilot.install);

