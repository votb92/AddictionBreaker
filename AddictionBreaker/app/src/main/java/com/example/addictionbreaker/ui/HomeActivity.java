package com.example.addictionbreaker.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.addictionbreaker.R;
import com.example.addictionbreaker.data.DatabaseHelper;

public class HomeActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button yourProfileButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myDb = new DatabaseHelper(this);
        yourProfileButton = (Button)findViewById(R.id.yourProfileButton);
        viewAll();
    }

    public void viewAll(){
        yourProfileButton.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    Cursor res = myDb.getAllData();
                    if(res.getCount() == 0){
                        showMessage("Error", "Nothing was found");
                        return;
                    }
                    StringBuffer information = new StringBuffer();
                    while (res.moveToNext()){
                        information.append("ID: " + res.getString(0) +"\n");
                        information.append("NAME: " + res.getString(1) +"\n");
                        information.append("AGE: " + res.getString(2) +"\n");
                        information.append("ADDICTION: " + res.getString(3) +"\n");
                        information.append("FREQUENCY: " + res.getString(4) +"\n");
                        information.append("COST: " + res.getString(5) +"\n");
                    }
                    showMessage("Data", information.toString());
                }
            }
        );
    }
    public void showMessage(String Title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }
}