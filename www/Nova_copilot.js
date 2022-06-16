var exec = require('cordova/exec');
var cordova = require('cordova')

var PLUGIN_ID = "Nova_copilot";
// TODO: check of links below
//https://github.com/amorane/cordova-plugin-firebase-dynamiclinks/blob/master/www/FirebaseDynamicLinks.js
//https://github.com/TruewindIT/cordova-plugin-firebase/blob/master/www/firebase.js

module.exports = {
    coolMethod: function(arg0, success, error) {
        exec(success, error, PLUGIN_ID, 'coolMethod', [arg0]);
    },
    isSDKSetup: function(arg0, success, error) {
        exec(success, error, PLUGIN_ID, 'isSDKSetup', [arg0]);
    },
    setUp: function (sdkKey, driver_id, success, error) {
        function  onSuccessCallback(message) {
            success(message);
        }
    
        exec(
            onSuccessCallback,
            error,
            PLUGIN_ID,
            'setUp',
            [sdkKey, driver_id]);
    },
    startManualDrive: function (arg0, success, error) {
        exec(success, error, PLUGIN_ID, 'startManualDrive', [arg0]);
    },
    stopManualDrive: function (arg0, success, error) {
        exec(success, error, PLUGIN_ID, 'stopManualDrive', [arg0]);
    },
    ListenDriveEvents: function (success, error) {
        exec(success, error, PLUGIN_ID, 'ListenDriveEvents', []);
    },
    stopListenDriveEvents: function (success, error) {
        exec(success, error, PLUGIN_ID, 'stopListenDriveEvents', []);
    },
    install: function () {
        if (!window.plugins) {
          window.plugins = {};
      
        }
        window.plugins.Nova_copilot = new Nova_copilot();
          
          
        window.plugins.Nova_copilot.ListenDriveEvents(function (message) {
          if(message !== undefined) {
              cordova.fireWindowEvent("driver_events", {message: message})
          } else {
              cordova.fireWindowEvent("driver_events", {message: "No events"})
          }
      
        })
          
          
        return window.plugins.Nova_copilot;
      }
}
 
// cordova.addConstructor(Nova_copilot.install);

