package com.example.addictionbreaker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.addictionbreaker.R;
import com.example.addictionbreaker.data.DatabaseHelper;

import java.util.Calendar;

public class ResetProgressActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private TextView resetInfo;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_progress);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        Button resetDate = findViewById(R.id.reset_date_button);
        Button resetTime = findViewById(R.id.reset_time_button);
        resetInfo = findViewById(R.id.reset_info_text);
        Button resetProgress = findViewById(R.id.reset_progress_button);

        resetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        resetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        resetProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resetInfo.getText().toString().contains(":") && resetInfo.getText().toString().contains("/")){
                new AlertDialog.Builder(ResetProgressActivity.this)
                        .setTitle("Reset Progress")
                        .setMessage("Are you sure you want to reset your progress?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (v.getId()){
                                    case R.id.reset_progress_button:
                                        db.updateDay(0);
                                        db.updateStartInfo(String.valueOf(year), String.valueOf(month), String.valueOf(day), String.valueOf(hour), String.valueOf(minute));
                                        Intent intent = new Intent(ResetProgressActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("no", null).show();
                }
                else{
                    AlertDialog a = new AlertDialog.Builder(resetProgress.getContext()).create();
                    a.setTitle("Missing/Blank Fields!");
                    a.setMessage("Make sure you pick your new start time and date");
                    a.show();
                }
            }
        });



    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void showTimePickerDialog(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, 2, this,
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                false);
        timePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;

        if(resetInfo.getText().toString().isEmpty()) {
            String date = "Start Date and Time- " + (month + 1) + "/" + day + "/" + year;
            resetInfo.setText(date);
        }else if(resetInfo.getText().toString().contains("/")){
            String date = "Start Date and Time- " + (month + 1) + "/" + day + "/" + year;
            resetInfo.setText(date);
        }
        else{
            resetInfo.append(" on " + (month + 1) + "/" + day + "/" + year);
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        String fixedMinute = String.valueOf(minute);
        if(minute < 10){
            fixedMinute = "0" + fixedMinute;
        }
        if(resetInfo.getText().toString().isEmpty()) {
            String time = "Start Time & Date- " + hour + ":" + fixedMinute;
            if(hour >= 0 && hour < 12){
                time = time + " AM";
            }else{
                time = time + " PM";
            }
            resetInfo.setText(time);
        }else if(resetInfo.getText().toString().contains(":")){
            String time = "Start Time & Date- " + hour + ":" + fixedMinute;
            if(hour >= 0 && hour < 12){
                time = time + " AM";
            }else{
                time = time + " PM";
            }
            resetInfo.setText(time);
        }
        else {
            String time = " at "  + hour + ":" + fixedMinute;
            if(hour >= 0 && hour < 12){
                time = time + " AM";
            }else{
                time = time + " PM";
            }
            resetInfo.append(time);
        }
    }
}