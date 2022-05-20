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

/**
 * Broadcast receiver which receives Zendrive SDK callbacks and passes it to the ZendriveManager.
 */
public class ZendriveSdkBroadcastReceiver extends ZendriveBroadcastReceiver {

    @Override
    public void onDriveStart(Context context, DriveStartInfo startInfo) {
        Log.d(Constants.LOG_TAG_DEBUG, "CallBack From SDK: Drive Start");

    }

    @Override
    public void onDriveResume(Context context, DriveResumeInfo driveResumeInfo) {
        Log.d(Constants.LOG_TAG_DEBUG, "CallBack From SDK: Drive Resumed");

    }

    @Override
    public void onDriveEnd(Context context, EstimatedDriveInfo estimatedDriveInfo) {
        Log.d(Constants.LOG_TAG_DEBUG, "CallBack From SDK: Drive End");

    }

    @Override
    public void onDriveAnalyzed(Context context, AnalyzedDriveInfo analyzedDriveInfo) {
        Log.d(Constants.LOG_TAG_DEBUG, "CallBack From SDK: Drive Analyzed");

    }

    @Override
    public void onAccident(Context context, AccidentInfo accidentInfo) {
        Log.d(Constants.LOG_TAG_DEBUG, "CallBack From SDK: Accident Detected");

    }

    public void onPotentialAccident(Context context, AccidentInfo accidentInfo) {
        Log.d(Constants.LOG_TAG_DEBUG, "CallBack From SDK: Potential Accident Detected");

    }

    @Override
    public void onZendriveSettingsConfigChanged(Context context, boolean errorsFound,
                                                boolean warningsFound) {
        Log.d(Constants.LOG_TAG_DEBUG,
                "CallBack From SDK: ZendriveSettingsChanged: " +
                        errorsFound + ": " + warningsFound);
    }
}
