package com.example.addictionbreaker.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.addictionbreaker.R;
import com.example.addictionbreaker.data.DatabaseHelper;
import com.example.addictionbreaker.model.User;
import com.google.gson.Gson;

import java.util.Calendar;
//a comment

public class ConsumptionInfoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    DatabaseHelper myDb;
    private String drugType;
    private String drugDescription;
    private TextView consumption_info_how_frequent  ;
    private TextView consumption_info_cost;
    private TextView startDateText;
    private boolean isBlank = false;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_info);
        myDb = new DatabaseHelper(getApplicationContext());
        consumption_info_how_frequent = (TextView)findViewById(R.id.consumption_info_how_frequent);
        consumption_info_cost = (TextView)findViewById(R.id.consumption_info_cost);
        startDateText = findViewById(R.id.start_date);
        final EditText averageConsumption = findViewById(R.id.averageConsumption);
        final EditText averageCost = findViewById(R.id.averageCost);
        final Button letsGo = findViewById(R.id.letsGo);
        Button startDate = findViewById(R.id.start_date_button);
        Button startTime = findViewById(R.id.start_time_button);

        //retrieve user info
        final SharedPreferences myPrefs = this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        final Gson gson = new Gson();
        String info = myPrefs.getString("userInfo", null);
        final User user = gson.fromJson(info , User.class);
        setStrings(user);

        Resources res = getResources();
        String how_frequent = String.format(res.getString(R.string.consumption_info_how_frequent), drugType);
        String cost = String.format(res.getString(R.string.consumption_info_cost), drugDescription);
        consumption_info_how_frequent.setText(how_frequent);
        consumption_info_cost.setText(cost);


        letsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDateText.getText().toString().isEmpty();
                if(averageConsumption.getText().toString().isEmpty() || averageCost.getText().toString().isEmpty()){
                    isBlank = alertMessage(letsGo, "Make sure you fill in your consumption info");
                }else if (!startDateText.getText().toString().contains(":") || !startDateText.getText().toString().contains("/") || startDateText.getText().toString().isEmpty()){
                    isBlank = alertMessage(letsGo, "Make sure you pick a start time and date");
                }
                else{
                    isBlank = false;
                }
                if(!isBlank){
                    String newInfo = gson.toJson(user);
                    myPrefs.edit().putString("userInfo", newInfo).commit();
                    boolean isInserted = myDb.insertData(user.getName(), Integer.toString(user.getAge()), user.getAddiction(),Integer.toString(user.getConsumption()),Integer.toString(user.getCostOfAddiction()),
                            Integer.toString(year),Integer.toString(month),Integer.toString(day),Integer.toString(hour),Integer.toString(minute) );
                    if(isInserted){
                        Toast.makeText(ConsumptionInfoActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                    }
                        Intent intent = new Intent(ConsumptionInfoActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
            });

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

    }
    private void setStrings(User user) {
        switch(user.getAddiction()){
            case "Alcohol":
                drugType = "drinks";
                drugDescription = "a day of drinking";
                break;
            case "Vaping/Juuling":
                drugType = "pod";
                drugDescription = "a pod";
                break;
            default :
                drugType = "cigarettes";
                drugDescription = "a pack of cigarettes";
        }
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
        if(startDateText.getText().toString().isEmpty()) {
            String date = "Start Date and Time- " + (month + 1) + "/" + day + "/" + year;
            startDateText.setText(date);
        }else if(startDateText.getText().toString().contains("/")){
            String date = "Start Date and Time- " + (month + 1) + "/" + day + "/" + year;
            startDateText.setText(date);
        }
        else{
            startDateText.append(" on " + (month + 1) + "/" + day + "/" + year);
        }
    }

    private boolean alertMessage(Button letsGo, String message){
        AlertDialog a = new AlertDialog.Builder(letsGo.getContext()).create();
        a.setTitle("Missing/Blank Fields!");
        a.setMessage(message);
        a.show();
        return true;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        if(startDateText.getText().toString().isEmpty()) {
            String time = "Start Time & Date- " + hour + ":" + minute;
            if(hour >= 0 && hour < 12){
                time = time + " AM";
            }else{
                time = time + " PM";
            }
            startDateText.setText(time);
        }else if(startDateText.getText().toString().contains(":")){
            String time = "Start Time & Date- " + hour + ":" + minute;
            if(hour >= 0 && hour < 12){
                time = time + " AM";
            }else{
                time = time + " PM";
            }
            startDateText.setText(time);
        }
        else {
            String time = " at "  + hour + ":" + minute;
            if(hour >= 0 && hour < 12){
                time = time + " AM";
            }else{
                time = time + " PM";
            }
            startDateText.append(time);
        }
    }
}
