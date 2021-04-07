package com.example.newdaily;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HowManyTasks extends AppCompatActivity {

    private Button enterBtn;
    private TextView howManyTasks;
    private String taskHolder;
    public static int taskAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many_tasks);
//GET TASKS AND LOOP APPLY THEM TO ARRAY AND ITERATE A VAR TO GET AMT FOR LATER USE

        howManyTasks = findViewById(R.id.userTaskAmount);
        enterBtn = findViewById(R.id.hoursEnter);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskHolder = "" + howManyTasks.getText();//getting task amt
                taskAmount = Integer.parseInt(taskHolder);
                startActivity(new Intent(HowManyTasks.this, EnterTasks2.class));
            }
        });
    }
}