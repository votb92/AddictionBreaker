package com.example.addictionbreaker.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.addictionbreaker.R;
import com.example.addictionbreaker.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myDb = new DatabaseHelper(this);
        Cursor res = myDb.getAllData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(res.getCount() != 0){
            Intent login = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(login);
        }else {
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
}
