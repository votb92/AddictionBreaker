package com.example.addictionbreaker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addictionbreaker.R;
import com.example.addictionbreaker.data.DatabaseHelper;
import com.example.addictionbreaker.model.User;
import com.google.gson.Gson;
//a comment

public class ConsumptionInfoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    DatabaseHelper myDb;
    private String string1;
    private String string2;
    private TextView consumption_info_how_frequent  ;
    private TextView consumption_info_cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_info);
        myDb = new DatabaseHelper(this);
        consumption_info_how_frequent = (TextView)findViewById(R.id.consumption_info_how_frequent);
        consumption_info_cost = (TextView)findViewById(R.id.consumption_info_cost);
        final EditText averageConsumption = findViewById(R.id.averageConsumption);
        final EditText averageCost = findViewById(R.id.averageCost);
        Button letsGo = findViewById(R.id.letsGo);

        //retrieve user info
        final SharedPreferences myPrefs = this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        final Gson gson = new Gson();
        String info = myPrefs.getString("userInfo", null);
        final User user = gson.fromJson(info , User.class);
        setStrings(user);

        Resources res = getResources();
        String how_frequent = String.format(res.getString(R.string.consumption_info_how_frequent), string1);
        String cost = String.format(res.getString(R.string.consumption_info_cost),string2);
        consumption_info_how_frequent.setText(how_frequent);
        consumption_info_cost.setText(cost);


        letsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setConsumption(Integer.parseInt(averageConsumption.getText().toString()));
                user.setCostOfAddiction(Integer.parseInt(averageCost.getText().toString()));
                String newInfo = gson.toJson(user);
                myPrefs.edit().putString("userInfo", newInfo).commit();
                boolean isInserted = myDb.insertData(user.getName(), Integer.toString(user.getAge()), user.getAddiction(),Integer.toString(user.getConsumption()),Integer.toString(user.getCostOfAddiction()));
                if(isInserted){
                    Toast.makeText(ConsumptionInfoActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(ConsumptionInfoActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
    private void setStrings(User user) {
        if (user.getAddiction().equals("Cigarettes")){
            string1 = "cigarettes";
            string2 = "a pack of cigarettes";
        }
        else if(user.getAddiction().equals("Alcohol")){
            string1 = "drinks";
            string2 = "a day of drinking";
        }
        else if(user.getAddiction().equals("Vaping/Juuling")){
            string1 = "pod";
            string2 = "a pod";
        }
        else{
            string1 = "cigarettes";
            string2 = "a pack of cigarettes";
        }
    }
}
