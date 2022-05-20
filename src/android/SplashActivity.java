package cordova_plugin_Nova_copilot;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.fragment.app.FragmentActivity;

public class SplashActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isUserLoggedIn()) {
            startActivity(new Intent(this, Nova_copilot.class));
        } else {
            startActivity(new Intent(this, Nova_copilot.class));
        }
        finish();
    }

    private boolean isUserLoggedIn(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                this.getApplicationContext());
        String savedDriverId = sharedPreferences.getString(SharedPreferenceManager.DRIVER_ID_KEY, null);
        return savedDriverId!=null && !savedDriverId.equalsIgnoreCase("");
    }
}
