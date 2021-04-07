package com.example.newdaily;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {

    private TextView txtview1, txtview2, txtview3, txtview4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        txtview1 = findViewById(R.id.txtview1);
        txtview2 = findViewById(R.id.txtview2);
        txtview3 = findViewById(R.id.txtview3);
        txtview4 = findViewById(R.id.txtview4);



    }
}