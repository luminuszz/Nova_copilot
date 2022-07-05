package cordova_plugin_Nova_copilot;
import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.R;


public class NotifyJs {

    public NotificationCompat.Builder notificationBuilder;

    public String CHANNEL_ID = "nova_copilot_events";





public void createNotificationChannel(Context context) {
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        CharSequence name = "Nova copilot events";
        String description = "Channels for Nova_copilot";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        channel.setDescription(description);
        notificationManager.createNotificationChannel(channel);
    }

}




    public void sendNotification(Context ctx, String message) {
        notificationBuilder = new NotificationCompat.Builder(ctx, CHANNEL_ID)
                .setSmallIcon(R.drawable.sym_def_app_icon)
                .setContentTitle("Nova copilot")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);


            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ctx);

            notificationManager.notify(5, notificationBuilder.build());


    }







}