package com.example.newdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.sql.Time;
import java.text.SimpleDateFormat;


public class FirstAlarm extends AppCompatActivity {

    private Button stopAlarm, cancelAlarm;
    private TextView nextAct;
    private MediaPlayer mediaPlayer;
    Vibrator vibrator;
    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_alarm);
        WakeLocker.acquire(this);
        FinalAct.ServiceStopper = true;//iterates so service can stop

        serviceIntent = new Intent(this,AlarmService.class);
        stopService(serviceIntent);//SERVICE STOPPED
        FinalAct.currentAlarm ++;

        nextAct = findViewById(R.id.NextAct);
        nextAct.setText(EnterTasks.t1);
        stopAlarm = findViewById(R.id.AlarmStop);
        cancelAlarm =  findViewById(R.id.cancelAlarm);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(50);//VIBRATOR FUNCTIONALITY

        //Mediaplayer start
        // mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer = MediaPlayer.create(this, R.raw.swiftly);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        stopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer();
                //serviceIntent = new Intent(getApplicationContext().getApplicationContext(),AlarmService.class);
                FinalAct.ServiceStopper = false;//assigned so service funct. restarts
                startService(serviceIntent);
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                String text = "APP_NAME closed, all alarms cancelled " ;
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlayer();
    }
    private void  stopPlayer(){
        mediaPlayer.stop();
        mediaPlayer = null;
       WakeLocker.release();
       Log.i("MEDIA", "Media should have stopped");
   }
    @Override
    public void onBackPressed() {// makes program exit to home screen
        Log.i("CDA", "onBackPressed Called");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
