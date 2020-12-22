package pt.atp.boadcastreceiverproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

public class BootReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_TOKEN = 100;
    private static final int MY_BACKGROUND_JOB = 1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.w("BOOT","BOOT");
        Toast.makeText(context,"Project: Boot Completed", Toast.LENGTH_LONG).show();

        //Não esquecer de criar a permissao no manifest
       // createNotification(context);


        //JobScheduler
       /*JobScheduler js = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo job = new JobInfo.Builder(
                MY_BACKGROUND_JOB,
                new ComponentName(context, MyJobScheduler.class))
                .build();
        js.schedule(job);*/

        //JobIntentService
       /** JobIntentService.enqueueWork(context,MyJobScheduler.class,1,new Intent());**/

        WorkRequest myWork = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        WorkManager.getInstance(context).enqueue(myWork);

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
