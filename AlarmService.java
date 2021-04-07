package com.example.newdaily;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class AlarmService extends Service {


    public static NotificationCompat.Builder notificationBuilder;
    public static Intent[] alarmArray = { FinalAct.firstAlarm, FinalAct.secondAlarm, FinalAct.thirdAlarm, FinalAct.fourthAlarm };
    int temp = 0;

    public AlarmService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());

        Log.i ("Service status", "Running");

        if(FinalAct.ServiceStopper == false) {//if service is launched less than once
            startAlarms(0, hours.inputTime);//BROADCAST STARTED HERE
        }else{
            startAlarms(FinalAct.newTime, hours.inputTime);//BROADCAST STARTED HERE
            Log.i ("Service status", "Restarted and updated");
        }

        //Notification builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent fullScreenIntent = alarmArray[FinalAct.currentAlarm];
            PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
                    fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            notificationBuilder = new NotificationCompat.Builder(this, MainActivity.NOTIFICATION_CHANNEL_ID)
                    .setFullScreenIntent(fullScreenPendingIntent, true);
        }else{
            notificationBuilder = new NotificationCompat.Builder(this, MainActivity.NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.drawable.jojo_icon_background)
                    .setContentTitle("Daily Planner Alarm")
                    .setContentText("alarm set for old APIs")
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground()
    {
        String NOTIFICATION_CHANNEL_ID = "example.permanence";
        String channelName = "Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_HIGH);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MAX)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void startAlarms(int newTime ,int input){//
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReciever.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        assert alarm != null;

        switch(FinalAct.currentAlarm){
            case 0:
                temp = FinalAct.firstTime;
                break;
            case 1:
                temp = FinalAct.secondTime;
                break;
            case 2:
                temp = FinalAct.thirdTime;
                break;
            case 3:
                temp = FinalAct.fourthTime;
                break;
        }
        if(newTime < 1) {

            alarm.setExact(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + temp, alarmIntent);
            FinalAct.timeHolder = (int) (System.currentTimeMillis() + temp );
            Time time;
            time = new Time(System.currentTimeMillis() + temp);
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");

            //TOAST MESSAGE DISPLAYED HERE
            String text = "First alarm set for " + formatter.format(time);
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();
        }else{
            FinalAct.newTime = (int) (System.currentTimeMillis() + temp);
            alarm.setExact(AlarmManager.RTC_WAKEUP,
                    FinalAct.newTime - FinalAct.timeHolder, alarmIntent);
            Time time;
            time = new Time(System.currentTimeMillis() + temp);
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");

            //TOAST MESSAGE DISPLAYED HERE
            String text = "Next alarm set for " + formatter.format(time);
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public static void fullScreenIntent() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Notification notification = notificationBuilder.setOngoing(true)
                    .setContentTitle("App is running in background")
                    .setPriority(NotificationManager.IMPORTANCE_MAX)
                    .setCategory(Notification.CATEGORY_ALARM)
                    .setAutoCancel(true)
                    .build();
        }else{

            Notification notification = notificationBuilder.setOngoing(true)
                    .setCategory(Notification.CATEGORY_ALARM)
                    .setAutoCancel(true)
                    .build();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(FinalAct.ServiceStopper == false){
            FinalAct.newTime = (int) (System.currentTimeMillis() + temp );
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("restartservice");
            broadcastIntent.setClass(this, Restarter.class);
            this.sendBroadcast(broadcastIntent);
        }
    }
}