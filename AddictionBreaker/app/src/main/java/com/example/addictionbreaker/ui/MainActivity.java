package com.example.addictionbreaker.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.addictionbreaker.R;
import com.example.addictionbreaker.data.DatabaseHelper;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        myDb = new DatabaseHelper(this);
        Cursor res = myDb.getAllData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Language_Change = findViewById(R.id.lang);
        Language_Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });
        if (res.getCount() != 0) {
            Intent login = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(login);
        } else {
            Button startButton = findViewById(R.id.start_button);
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, FormActivity.class);
                    startActivity(intent);
                }

            });
        }
    }

    private void showChangeLanguageDialog() {
        final String[] langItems = {"English", "Español"};
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setSingleChoiceItems(langItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int lang_choice) {

                if (lang_choice == 0) {
                    setLocale("en");
                    recreate();
                }
                else if (lang_choice == 1) {
                    setLocale("es");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog mDialog = b.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }
}
