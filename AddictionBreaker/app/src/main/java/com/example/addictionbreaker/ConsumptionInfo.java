package com.example.addictionbreaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

public class ConsumptionInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_info);

        final EditText averageConsumption = findViewById(R.id.averageConsumption);
        final EditText averageCost = findViewById(R.id.averageCost);
        Button letsGo = findViewById(R.id.letsGo);

        //retrieve user info
        final SharedPreferences myPrefs = this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        final Gson gson = new Gson();
        String info = myPrefs.getString("userInfo", null);
        final User user = gson.fromJson(info , User.class);

        letsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setConsumption(Integer.parseInt(averageConsumption.getText().toString()));
                user.setCostOfAddiction(Integer.parseInt(averageCost.getText().toString()));
                String newInfo = gson.toJson(user);
                myPrefs.edit().putString("userInfo", newInfo).commit();

                Intent intent = new Intent(ConsumptionInfo.this, HomeActivity.class);
                startActivity(intent);
            }
        });


    }
}
