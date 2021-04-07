package com.example.newdaily;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import static com.example.newdaily.AlarmService.fullScreenIntent;

public class AlarmReciever extends BroadcastReceiver{
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        //Create full screen intent here
        fullScreenIntent();

        }

}

