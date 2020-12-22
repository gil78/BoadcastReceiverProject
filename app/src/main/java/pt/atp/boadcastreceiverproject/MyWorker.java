package pt.atp.boadcastreceiverproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker{

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
 
        createNotification(getApplicationContext());
        return Result.success();
    }

    private void createNotification(Context context){
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Notification_1")
                .setSmallIcon(R.drawable.add_icon)
                .setContentTitle("Projeto Android")
                .setContentText("Tire uma selfie agora")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true); //Remove a notificação quando o utilizador toca nela

        NotificationManager notificationManager =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Notification_1";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Notification channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }

}
