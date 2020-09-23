package com.example.addictionbreaker;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

public class AdictionList extends AppCompatActivity {

    String s1[], s2[];
    int images[] = {R.drawable.add, R.drawable.add2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiction_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdictionList.this, Form.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        com.jovanovic.stefan.myrecyclerview.MyAdapter MyAdapter = new MyAdapter(ct: this, s1, s2, images);
    }
}