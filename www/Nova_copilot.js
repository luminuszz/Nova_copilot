var exec = require('cordova.exec');

var PLUGIN_ID = 'Nova_copilot';
module.exports = {

  coolMethod: function(arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'coolMethod', [arg0]);
  },

  isSDKSetup: function(arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'isSDKSetup', [arg0]);
  },

  setUp: function(sdkKey, driver_id, success, error) {
    function onSuccessCallback(message) {
      success(message);
    }

    exec(
      onSuccessCallback,
      error,
      PLUGIN_ID,
      'setUp',
      [sdkKey, driver_id]);
  },

  startManualDrive: function(arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'startManualDrive', [arg0]);
  },

  stopManualDrive: function(arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'stopManualDrive', [arg0]);
  },
  ListenDriveEvents: function(success, error) {
    exec(success, error, PLUGIN_ID, 'ListenDriveEvents', []);
  },

  stopListenDriveEvents: function(success, error) {
    exec(success, error, PLUGIN_ID, 'stopListenDriveEvents', []);
  }


}








