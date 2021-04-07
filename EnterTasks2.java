package com.example.newdaily;

import android.content.Intent;
import android.os.Bundle;
 import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class EnterTasks2 extends AppCompatActivity {

    private TextView titleText;
    private EditText input;
    private Button nextBtn,done;
    private int iterator;
    static Object[] tasks;
    private int doneBtnCounter;
    private int tasksToBeEntered = HowManyTasks.taskAmount;
    public static ArrayList<String> tasksToComplete = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_tasks2);

        input = (EditText) findViewById(R.id.EnterTasks);
        titleText = (TextView) findViewById(R.id.tasksFirstTitle);
        nextBtn = (Button) findViewById(R.id.NextBtn);
        done = (Button) findViewById(R.id.done);

        if (savedInstanceState != null) {
            titleText.setText("Enter Next Task");
        } else {
            if (iterator < 1) {
                titleText.setText("Enter The Task You Wish To Complete First");
            }
    }
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().length() >= 1) {
                    if (iterator < HowManyTasks.taskAmount) {//changes title text according to amount of activities
                        String temp = input.getText().toString();
                        tasksToComplete.add(temp);
                        tasksToBeEntered = HowManyTasks.taskAmount - ++iterator;
                        input.setText("");
                        if(HowManyTasks.taskAmount != iterator) {
                            titleText.setText("Enter Next Task");
                        }else{
                            titleText.setText("MAXIMUM NUMBER OF TASKS PLEASE PRESS DONE");
                        }
                    }else{
                        titleText.setText("MAXIMUM NUMBER OF TASKS PLEASE PRESS DONE");
                    }
                }else if(iterator >= HowManyTasks.taskAmount ){
                    titleText.setText("Press DONE to submit tasks");
                }else{
                    titleText.setText("Please enter an activity or task you wish to complete");
                }
            }
        });
        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (iterator != HowManyTasks.taskAmount) {
                    if(input.getText().length() >= 1){
                        titleText.setText( "Press NEXT to confirm your entry");
                    }else {
                        titleText.setText("Please enter ( " + tasksToBeEntered + " ) more tasks");
                    }
                    doneBtnCounter = 0;
                } else {
                    if (doneBtnCounter < 1) {
                        titleText.setText("Press DONE again to confirm your entry");
                        doneBtnCounter++;
                    }else {
                        startActivity(new Intent(EnterTasks2.this, hours.class));
                    }
                }
            }
        });


    }
    @Override//STATE SAVED HERE, called before activity is paused/destroyed
    public void onSaveInstanceState(Bundle saved) {
        saved.putInt("iterator", iterator++);
        saved.putInt("doneBtnCounter", doneBtnCounter);
        super.onSaveInstanceState(saved);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        iterator = savedInstanceState.getInt("iterator");
        doneBtnCounter = savedInstanceState.getInt("doneBtnCounter");
    }
}
