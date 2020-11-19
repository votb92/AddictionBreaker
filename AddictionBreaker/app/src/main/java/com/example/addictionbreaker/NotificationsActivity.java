package com.example.addictionbreaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.addictionbreaker.data.DatabaseHelper;
import com.example.addictionbreaker.ui.HomeActivity;

import java.util.Calendar;
import java.util.Date;

public class NotificationsActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private TextView timeText;
    private Date date = new Date();
    private Calendar calendar = Calendar.getInstance();
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        db = new DatabaseHelper(getApplicationContext());
        //db.onUpgrade(db.getWritableDatabase(), 1, 2);
        int times[] = new int[0];
        createNotificationChannel();

        Button doneButton = findViewById(R.id.done_button);
        timeText = findViewById(R.id.time_text);
        Button notificationsButton = findViewById(R.id.notifications_time_button);
        Button goBackButton = findViewById(R.id.back_button);

        //times = db.getRemindertime();
        //Log.i("hour", String.valueOf(times[0]));
        //Log.i("minute", String.valueOf(times[1]));

        notificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(NotificationsActivity.this, HomeActivity.class);
                startActivity(back);
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent current = new Intent(NotificationsActivity.this, Receiver.class);
                boolean isAlarm = (PendingIntent.getBroadcast(NotificationsActivity.this, 0, current, 0) != null);
                if(isAlarm){
                    new AlertDialog.Builder(NotificationsActivity.this)
                            .setTitle("Change Reminder time?")
                            .setMessage("You have a daily reminder currently set, do you want to change the time?")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    stopAlarm();
                                    createAlarm();
                                    Intent back = new Intent(NotificationsActivity.this, HomeActivity.class);
                                    startActivity(back);
                                }
                            })
                            .setNegativeButton("no", null).show();
                }
                else {
                    createAlarm();
                    Intent back = new Intent(NotificationsActivity.this, HomeActivity.class);
                    startActivity(back);
                }
            }
        });
    }

    private void stopAlarm() {
        Intent intent = new Intent(NotificationsActivity.this, Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationsActivity.this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    private void createAlarm() {
        Intent intent = new Intent(NotificationsActivity.this, Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationsActivity.this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(getApplicationContext(), "Reminder Set", Toast.LENGTH_SHORT).show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, 2, this,
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                false);
        timePickerDialog.show();
    }

    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        db.addReminderTime(hour, minute);
        String time;
        if (hour >= 0 && hour < 12) {
            time = "Notification Time- " + hour + ":" + minute + " AM";
        } else {
            time = "Notification Time- " + hour + ":" + minute + " PM";
        }

        timeText.setText(time);
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "AddictionReminderChannel";
            String description = "Channel for reminder";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("addictionReminder", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}