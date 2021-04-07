package com.example.newdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class FourthAlarm extends AppCompatActivity {
    private Button stopAlarm;
    private MediaPlayer mediaPlayer;
    private Intent serviceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_alarm);
        WakeLocker.acquire(this);
        serviceIntent = new Intent(this,AlarmService.class);
        stopService(serviceIntent);//SERVICE STOPPED
        FinalAct.currentAlarm ++;

       /* nextAct = findViewById(R.id.NextAct);
        nextAct.setText(EnterTasks.t4);*/


        stopAlarm = findViewById(R.id.AlarmStop);
        mediaPlayer = MediaPlayer.create(this, R.raw.swiftly);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        stopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPlayer();    }

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
