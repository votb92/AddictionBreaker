package com.example.addictionbreaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        final EditText name = findViewById(R.id.userName);
        final EditText age = findViewById(R.id.age);

        final Button startButton = findViewById(R.id.confirm_button);
        startButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty() || age.getText().toString().isEmpty()){
                    AlertDialog a = new AlertDialog.Builder(startButton.getContext()).create();
                    a.setTitle("Missing Fields");
                    a.setMessage("Please enter your name and/or age");
                    a.show();
                }
                else {
                    Intent intent = new Intent(Form.this, ConsumptionInfo.class);
                    startActivity(intent);
                }
        }

        });


    }
}
