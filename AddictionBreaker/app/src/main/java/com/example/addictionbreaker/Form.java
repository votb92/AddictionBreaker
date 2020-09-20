package com.example.addictionbreaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

public class Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        final EditText name = findViewById(R.id.userName);
        final EditText age = findViewById(R.id.age);
        final Spinner addiction = findViewById(R.id.addictionSpinner);

        final Button startButton = findViewById(R.id.confirm_button);
        final Gson gson = new Gson();
        final SharedPreferences myPrefs = this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);

        startButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Log.i("test", name.getText().toString());
                if(name.getText().toString().isEmpty() || age.getText().toString().isEmpty()){
                    AlertDialog a = new AlertDialog.Builder(startButton.getContext()).create();
                    a.setTitle("Missing Fields");
                    a.setMessage("Please enter your name and/or age");
                    a.show();
                }
                else {
                    //Save user info
                    User user = new User(name.getText().toString(),addiction.getSelectedItem().toString(),Integer.parseInt(age.getText().toString()));
                    String info = gson.toJson(user);
                    myPrefs.edit().putString("userInfo", info).commit();

                    Intent intent = new Intent(Form.this, ConsumptionInfo.class);
                    startActivity(intent);
                }
        }

        });


    }
}
