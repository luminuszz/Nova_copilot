package cordova_plugin_Nova_copilot;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.zendrive.sdk.AccidentInfo;
import com.zendrive.sdk.AnalyzedDriveInfo;
import com.zendrive.sdk.DriveResumeInfo;
import com.zendrive.sdk.DriveStartInfo;
import com.zendrive.sdk.EstimatedDriveInfo;
import com.zendrive.sdk.ZendriveBroadcastReceiver;
import com.zendrive.sdk.ZendriveAccidentConfidence;
import android.content.Intent;

import org.json.JSONObject;

public class ZendriveSdkBroadcastReceiver extends ZendriveBroadcastReceiver {

    @Override
    public void onDriveStart(Context context, DriveStartInfo startInfo) {

         Nova_copilot.notify(context, "onDriveStart");

    }

    @Override
    public void onDriveResume(Context context, DriveResumeInfo driveResumeInfo) {


         Nova_copilot.notify(context,  "onDriveResume");

    }

    @Override
    public void onDriveEnd(Context context, EstimatedDriveInfo estimatedDriveInfo) {


          Nova_copilot.notify(context,  "onDriveEnd");

    }

    @Override
    public void onDriveAnalyzed(Context context, AnalyzedDriveInfo analyzedDriveInfo) {

        Nova_copilot.notify(context, "onDriveAnalyzed");

    }

    @Override
    public void onAccident(Context context, AccidentInfo accidentInfo) {
        Nova_copilot.notify(context, "onAccident");

    }

    public void onPotentialAccident(Context context, AccidentInfo accidentInfo) {
          Nova_copilot.notify(context, "onPotentialAccident" );

    }

    @Override
    public void onZendriveSettingsConfigChanged(Context context, boolean errorsFound,
                                                boolean warningsFound) {

         Nova_copilot.notify(context, "onZendriveSettingsConfigChanged" );
    }
}
