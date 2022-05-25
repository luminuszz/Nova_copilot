package cordova_plugin_Nova_copilot;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

import com.zendrive.sdk.*;


public class Nova_copilot extends CordovaPlugin {

  //  static final String ZENDRIVE_SDK_KEY = "1moSkgjnEDSQbqjTaA5VyJRd6SWbvdX0";

  //  static  final String driver_id = "vkBoeu93reee4GRP7FygfFu10Oy2";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }


        if (action.equals("isSDKSetup")) {
          this.isSDKSetup(callbackContext);
            return true;
        }

        if (action.equals("setUp")) {
            String sdkKey = args.getString(0);
            String driver_id = args.getString(1);

            this.setUp(sdkKey, driver_id, callbackContext);
            return true;
        }


        if(action.equals("startManualDrive")){
            this.startManualDrive(callbackContext);
            return true;
        }


        if(action.equals("stopManualDrive")){
             this.stopManualDrive(callbackContext);
             return true;
        }



        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }


    public void isSDKSetup(CallbackContext callbackContext) {
        Context context = this.cordova.getActivity().getApplicationContext();

        boolean response = Zendrive.isSDKSetup(context);

        callbackContext.success(response ? "True": "False");


    }


    public void setUp(String ZENDRIVE_SDK_KEY, String driver_id, final CallbackContext callbackContext){
        Context context = this.cordova.getActivity().getApplicationContext();

        ZendriveConfiguration zendriveConfiguration = new ZendriveConfiguration(ZENDRIVE_SDK_KEY, driver_id);
        Zendrive.setup(
                context,
                zendriveConfiguration,
                 ZendriveSdkBroadcastReceiver.class,
                 ZendriveSdkNotificationProvider.class,
                new ZendriveOperationCallback() {
                    @Override
                    public void onCompletion(ZendriveOperationResult result) {
                        if (result.isSuccess()) {
                            callbackContext.success("zendrive is running...!");

                        }else {
                            callbackContext.error("zendrive is not running...!");
                        }
                    }
                }
        );
    }


    public void startManualDrive(final CallbackContext callbackContext) {
       Context context = this.cordova.getActivity().getApplicationContext();

        Zendrive.startDrive(context, Constants.TRIP_TRACKING_ID,
            new ZendriveOperationCallback() {
                @Override
                public void onCompletion(ZendriveOperationResult result) {
                    if (result.isSuccess()) {
                      callbackContext.success("startDrive success");

                     }
                    else {
                     callbackContext.error("startDrive error");
                     }
                }
            }
        );

    }


    public void stopManualDrive(final CallbackContext callbackContext) {
      Context context = this.cordova.getActivity().getApplicationContext();

      Zendrive.stopManualDrive(context,
          new ZendriveOperationCallback() {
              @Override
              public void onCompletion(ZendriveOperationResult result) {
                  if (result.isSuccess()) {

                    callbackContext.success("stopManualDrive success");

                    }
                  else {
                   callbackContext.success("stopManualDrive error");

                   }
              }
          }
      );



    }

}
