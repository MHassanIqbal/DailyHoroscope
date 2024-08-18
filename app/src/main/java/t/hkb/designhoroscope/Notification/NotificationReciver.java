package t.hkb.designhoroscope.Notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import t.hkb.designhoroscope.Activities.Main2Activity;
import t.hkb.designhoroscope.R;
import t.hkb.designhoroscope.database.model.Table;

public class NotificationReciver extends BroadcastReceiver {
    Context c;
    public static final String CHANNEL_ID = "hkb.horoscope";
    public static final String CHANNEL_NAME = "HKB";
    public NotificationManager manager;
    NotificationChannel notificationChannel = null;
//    NotificationHelper notificationHelper;

    @Override
    public void onReceive(Context context, Intent intent) {
        intent.getExtras();
        String star = intent.getStringExtra("STAR");
        String datesRanges = intent.getStringExtra("starDatesRanges");
        String alarmPosition = intent.getStringExtra("alarmPosition");
//        Bundle bundle = intent.getExtras();
//            String star = bundle.getString("STAR");
//          String datesRanges = bundle.getString("starDatesRanges");
//          int alarmPosition = bundle.getInt("alarmPosition");
//          List<Table> reminderList = bundle.getParcelable("reminderLlist");


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel(context,star,datesRanges);
        }else{
            Notification(context,star,datesRanges);

        }

    }

    private void NotificationChannel(Context context,String star,String datesRanges) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                    .setContentTitle(star)
                    .setContentText("checkout your today's horoscope")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setAutoCancel(true);
            Intent notificationIntent = new Intent(context,Main2Activity.class);
            notificationIntent.putExtra("message",star);
            notificationIntent.putExtra("starDatesRanges",datesRanges);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);

            // Add as notification
            manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);
            manager.notify(0 ,builder.build());

        }
    }

    private void Notification(Context context,String star,String datesRanges) {

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(star)
                        .setContentText("checkout your today's horoscope")
                        .setAutoCancel(true);
        Intent notificationIntent = new Intent(context,Main2Activity.class);
        notificationIntent.putExtra("message",star);
        notificationIntent.putExtra("starDatesRanges",datesRanges);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

//    public void cancelAlaram(int i,Context context) {
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,i,new Intent(context,NotificationReciver.class),0);
//        alarmManager.cancel(pendingIntent);
//    }

}
