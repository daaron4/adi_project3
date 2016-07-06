package com.companyname.john.project3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by David on 5/23/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {

    // Class deals with if phone is running and gets the  notification

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainActivityIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("GA Meet and Hire Reminder");
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        builder.setPriority(Notification.PRIORITY_DEFAULT);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Thank you for attending the GA Meet" +
                " and hire last night. This is friendly reminder to review the graduates from the Android Immersive " +
                "course. We appreciate you taking the time to come and see our work, and we look forward to hearing" +
                " back from you!"));
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        MyUtilities.setTimeToGoOff("goOffAtThisTime", System.currentTimeMillis()+ 64800000L, context);
    }
}
