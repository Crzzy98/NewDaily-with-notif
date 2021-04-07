package com.example.newdaily;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class EnterTasks extends AppCompatActivity {

    public TextView task1, task2, task3, task4;
    static String t1, t2, t3, t4;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_tasks);
//task field initialization
        task1 = (TextView) findViewById(R.id.task1);
        task2 = (TextView) findViewById(R.id.task2);
        task3 = (TextView) findViewById(R.id.task3);
        task4 = (TextView) findViewById(R.id.task4);
        addClickListener();

    }

    public void addClickListener() {

        //ENTER BUTTON INIT.
        btn = (Button) findViewById(R.id.calculate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "Daily Plan Created";

                //ASSIGNMENT OF A CHARSEQUENCE TO A STRING OBJ CAN BE ACHIEVED BY CONCATENATING A BLANK STRING SHOWn BELOW*****************
                t1 = "" + task1.getText();
                t2 = "" + task2.getText();
                t3 = "" + task3.getText();
                t4 = "" + task4.getText();

                t1 = t1.trim();
                t2 = t2.trim();
                t3 = t3.trim();
                t4 = t4.trim();


                startActivity(new Intent(EnterTasks.this, hours.class));

            }
        });
    }


}
