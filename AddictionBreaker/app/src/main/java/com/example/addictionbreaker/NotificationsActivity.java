package com.example.addictionbreaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class NotificationsActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private TextView timeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        timeText = findViewById(R.id.time_text);
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, 2, this,
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                false);
        timePickerDialog.show();
    }

    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        String time;
        if (hour >= 0 && hour < 12) {
            time = "Start Time & Date- " + hour + ":" + minute + " AM";
        } else {
            time = "Start Time & Date- " + hour + ":" + minute + " PM";
        }

        timeText.setText(time);
    }
}