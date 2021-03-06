package cordova_plugin_Nova_copilot;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import android.content.BroadcastReceiver;
import android.content.Context;
import org.apache.cordova.PluginResult;
import android.util.Log;
import android.content.Intent;
import android.content.IntentFilter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

import com.zendrive.sdk.*;


public class Nova_copilot extends CordovaPlugin {

    private boolean listenEvent = false;

    private CallbackContext zendriveCallbackContext = null;

    private BroadcastReceiver receiver;

    private NotifyJs notifyManager;

    private JSONObject notificationConfigMessages;





    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

     this.zendriveCallbackContext = callbackContext;
     notifyManager = new NotifyJs();

      notifyManager.createNotificationChannel(this.cordova.getActivity().getApplicationContext());


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

        if(action.equals("ListenDriveEvents")){
            this.ListenDriveEvents(callbackContext);

            return true;
        }

         if(action.equals("stopListenDriveEvents")){
               this.stopListenDriveEvents(callbackContext);

               return true;
             }


         if(action.equals("setNotificationConfig")){
               try {
                   notificationConfigMessages = new JSONObject(args.getString(0));
                   callbackContext.success("Notification config set");

               } catch (JSONException e) {

                   callbackContext.error(" Error in setting notification config");
               }

                return true;
         }




        return false;
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




public void ListenDriveEvents(CallbackContext callbackContext) {
     if(receiver == null) {
                                    receiver = new BroadcastReceiver() {
                                               @Override
                                               public void onReceive(Context context, Intent intent) {
                                                   String action = intent.getAction();

                                                    String message = intent.getStringExtra("event");
                                                    formatNotification(context, message);
                                                    PluginResult result = new PluginResult(PluginResult.Status.OK, message);
                                                    result.setKeepCallback(true);
                                                    callbackContext.sendPluginResult(result);


                                                    webView.postMessage("driver_events", message);





                                               }

                                           };
                                   }

                                    IntentFilter filter = new IntentFilter();

                                    filter.addAction("cordova_plugin_Nova_copilot.EVENT_CHANNEL");

                                    webView.getContext().registerReceiver(receiver, filter);


                                    Log.d("PluginResult", "Receiver registered");



}


public static void notify(Context context, String eventName) {
    Intent intent = new Intent("cordova_plugin_Nova_copilot.EVENT_CHANNEL");
    intent.putExtra("event", eventName);
    context.sendBroadcast(intent);

     Log.d("Notify driver Event", eventName);

}



    public void formatNotification(Context context, String eventName ) {

       if(notificationConfigMessages.has(eventName)){
                   try {
                       JSONObject config = this.notificationConfigMessages.getJSONObject(eventName);

                       String title = config.getString("title");
                       String content = config.getString("content");

                       notifyManager.sendNotification(
                               context,
                               title,
                               content
                       );
                   }catch (JSONException e) {

                       notifyManager.sendNotification(
                               context,
                               "Error",
                               "Error on send event notification"
                       );

                   }

               }


    }






 private void stopListenDriveEvents(CallbackContext callbackContext) {

    webView.getContext().unregisterReceiver(this.receiver);

    callbackContext.success("stopListenDriveEvents");

 }



}







