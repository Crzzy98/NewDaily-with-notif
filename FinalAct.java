package com.example.newdaily;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FinalAct extends AppCompatActivity {

    protected static int firstTime, secondTime, thirdTime, fourthTime;
    public static ArrayList<Integer> holderArray = new ArrayList<>();
    public static int [] arrayOfTimes;
    protected String act1, act2, act3, act4;
    protected TextView v1, v2, v3, v4;
    //int finalHours = Integer.parseInt(hours.text);
    public Day day = new Day(hours.inputTime, HowManyTasks.taskAmount);
    TextView [] views;
    //update VARs
    static boolean ServiceStopper = false;
    static int timeHolder;
    static int newTime;
    protected Intent serviceIntent;
    static int currentAlarm = 0;
    static Intent firstAlarm;
    static Intent secondAlarm;
    static Intent thirdAlarm;
    static Intent fourthAlarm;
    public static Object[] tasks = EnterTasks2.tasksToComplete.toArray();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        serviceIntent = new Intent(FinalAct.this, AlarmService.class);
        firstAlarm = new Intent(this, FirstAlarm.class);
        secondAlarm = new Intent(this, SecondAlarm.class);
        thirdAlarm = new Intent(this, ThirdAlarm.class);
        fourthAlarm = new Intent(this, FourthAlarm.class);
        AlarmService myService = new AlarmService();

        if (savedInstanceState != null) {// Then the application is being reloaded
            timeHolder = savedInstanceState.getInt("timeHolder");
            newTime = savedInstanceState.getInt("newTime");
            ServiceStopper = savedInstanceState.getBoolean("serviceStopper");
            currentAlarm = savedInstanceState.getInt("currentAlarm");
        }

        act1 = "Time you have for " + tasks[0] + " is " + day.getT(1) + " hours and " + day.getT(11) + " minutes";
        act2 = "Time you have for " + tasks[1] + " is " + day.getT(2) + " hours and " + day.getT(22) + " minutes";
        act3 = "Time you have for " + tasks[2] + " is " + day.getT(3) + " hours and " + day.getT(33) + " minutes";
        act4 = "Time you have for " + tasks[3] + " is " + day.getT(4) + " hours and " + day.getT(44) + " minutes";

        v1 = (TextView) findViewById(R.id.v1);
        v2 = (TextView) findViewById(R.id.v2);
        v3 = (TextView) findViewById(R.id.v3);
        v4 = (TextView) findViewById(R.id.v4);

        switch (HowManyTasks.taskAmount){
            case 1:
                v1.setText(act1);
                emptyViews(views = new TextView[]{v2, v3, v4});
            case 2:
                v1.setText(act1);
                v2.setText(act2);
                emptyViews(views = new TextView[]{v3, v4});
            case 3:
                v1.setText(act1);
                v2.setText(act2);
                v3.setText(act3);
                emptyViews(views = new TextView[]{v4});
            case 4:
                v1.setText(act1);
                v2.setText(act2);
                v3.setText(act3);
                v4.setText(act4);
        }


        firstTime = setTime(day.getT(1), day.getT(11));
        secondTime = setTime(day.getT(2), day.getT(22));
        thirdTime = setTime(day.getT(3), day.getT(33));
        fourthTime = setTime(day.getT(4), day.getT(44));

        holderArray.add(firstTime);
        holderArray.add(secondTime);
        holderArray.add(thirdTime);
        holderArray.add(fourthTime);
        arrayOfTimes = new int[holderArray.size()];

        for (int i = 0; i < holderArray.size(); i++) {
            arrayOfTimes[i] = (int) (holderArray.get(i));
        }

       if (TasksOrNormal.passed) {
           if (!isMyServiceRunning(myService.getClass())) {
               startService(serviceIntent);//Service started here
           }else {
               String text = "ALARM SERVICE ALREADY RUNNING ";
               Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
               toast.show();
           }
       }
    }



    protected static int setTime(int hour, int min){
       //int time = ((60 * 1000)*(hour*60)) + (min * (60 * 1000));
        int time = (60*(1000)*(hour*60)) + (min * (60 * 1000));
       return time;
        }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {// makes program exit to home screen
        Log.i("CDA", "onBackPressed Called");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void emptyViews( TextView [] views){
        for(int i = 0; i < views.length; i++ )
            views[i].setText("");
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i ("Service status", "Running");
                return true;
            }
        }
        Log.i ("Service status", "Not running");
        return false;
    }
    @Override//STATE SAVED HERE, called before activity is paused/destroyed
    public void onSaveInstanceState(Bundle saved) {
        saved.putBoolean("serviceStopper", ServiceStopper);
        saved.putInt("newTime", newTime);
        saved.putInt("timeHolder",timeHolder);
        saved.putInt("currentAlarm",currentAlarm);
        super.onSaveInstanceState(saved);
    }

}
