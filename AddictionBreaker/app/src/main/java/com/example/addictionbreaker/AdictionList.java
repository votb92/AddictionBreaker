package com.example.addictionbreaker;

import android.content.Intent;
import android.os.Bundle;

import com.example.addictionbreaker.ui.FormActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class AdictionList extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[];
    int images[] = {R.drawable.add, R.drawable.add2, R.drawable.add3, R.drawable.add4, R.drawable.add5, R.drawable.add6,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiction_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toobar);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdictionList.this, FormActivity.class);
                startActivity(intent);
            }
        });

        s1 = getResources().getStringArray(R.array.addictions);
        recyclerView = findViewById(R.id.recyclerView);

        MyAdapter myAdapter = new MyAdapter(this, s1, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
