var exec = require('cordova/exec');

var cordova = require('cordova')

var PLUGIN_ID = "Nova_copilot";

var currentEvent = ""

function Nova_copilot() {
}





Nova_copilot.prototype.isSDKSetup = function(arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'isSDKSetup', [arg0]);
}

Nova_copilot.prototype.setUp = function(sdkKey, driver_id, success, error) {
    function onSuccessCallback(message) {
        success(message);
    }
    exec(
        onSuccessCallback,
        error,
        PLUGIN_ID,
        'setUp',
        [sdkKey, driver_id]);
}

Nova_copilot.prototype.startManualDrive = function(arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'startManualDrive', [arg0]);
}

Nova_copilot.prototype.stopManualDrive = function(arg0, success, error) {
    exec(success, error, PLUGIN_ID, 'stopManualDrive', [arg0]);
}

Nova_copilot.prototype.ListenDriveEvents = function(success, error) {
    exec(success, error, PLUGIN_ID, 'ListenDriveEvents', []);
}

Nova_copilot.prototype.stopListenDriveEvents = function(success, error) {
    exec(success, error, PLUGIN_ID, 'stopListenDriveEvents', []);
}

Nova_copilot.prototype.setNotificationConfig = function(config, success, error) {

        var defaultConfig = {
            onDriveStart:  {
                title: "Drive started",
                content: "You already started a drive",
            },
            onDriveResume:  {
                title: "Drive started",
                content: "You already started a drive",
            },
            onDriveEnd:  {
                title: "Drive started",
                content: "You already stop a drive",
            },

            onDriveAnalyzed: {
                    title: 'Viagem analisada',
                    content: 'Sua viagem está sendo analisada',
            },
            onAccident: {
                title: 'Acidente detectado',
                content: 'Acidente detectado',
            },
            onZendriveSettingsConfigChanged: {
                title: 'Mudança de configuração',
                content: 'Acidente detectado',
            }
        }

    exec(success, error, PLUGIN_ID, 'setNotificationConfig', [JSON.stringify(defaultConfig)]);

}

Nova_copilot.install = function() {
    if (!window.plugins) {
        window.plugins = {};

    }
    window.plugins.Nova_copilot = new Nova_copilot();


    window.plugins.Nova_copilot.ListenDriveEvents(
        function(message) {
        if (message !== undefined) {
            cordova.fireWindowEvent("driver_events", { message: message })

            currentEvent = message



        } else {
            cordova.fireWindowEvent("driver_events", { message: "No events" })
        }

    })


    return window.plugins.Nova_copilot;
};

Nova_copilot.getEvent = function() {
    return currentEvent

}



cordova.addConstructor(Nova_copilot.install);