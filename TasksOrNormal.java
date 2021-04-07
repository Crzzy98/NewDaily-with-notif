package com.example.newdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;



public class TasksOrNormal extends AppCompatActivity implements View.OnClickListener{

    protected static boolean passed = false;//THIS VALUE EXISTS TO BE PASSED TO THE LAST NORMAL.FINALACT CLASSSES TO INIT ALARMS
    Button input, proceed;
    private RadioButton alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_normal);

        alarms = (RadioButton) findViewById(R.id.alarms);
        input = (Button) findViewById(R.id.input);
        proceed = (Button) findViewById(R.id.proceed);
        proceed.setOnClickListener(this);
        input.setOnClickListener(this);
    }

            @Override
            public void onClick(View v) {

        switch(v.getId()){
                    case(R.id.input):
                        startActivity(new Intent(TasksOrNormal.this, HowManyTasks.class));
                        if(alarms.isChecked() == true ){
                            passed = true;
                        }
                        break;
                    case(R.id.proceed):
                        startActivity(new Intent(TasksOrNormal.this, Normal.class));
                        if(alarms.isChecked() == true ){
                            passed = true;
                        break;

                }
            }
            }

}
