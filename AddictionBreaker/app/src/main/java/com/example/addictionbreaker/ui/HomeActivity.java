package com.example.addictionbreaker.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.addictionbreaker.R;
import com.example.addictionbreaker.data.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button yourProfileButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment homeFragment = new HomeFragment();
        final Fragment linksFragment = new LinksFragment();
        final Fragment settingsFragment = new SettingsFragment();
        final Fragment journeyFragment = new JourneyFragment();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,homeFragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch(item.getItemId()){
                    case R.id.home:
                    default:
                        fragment = homeFragment;
                        break;
                    case R.id.links:
                        fragment = linksFragment;
                        break;
                    case R.id.settings:
                        fragment = settingsFragment;
                        break;
                    case R.id.journey:
                        fragment = journeyFragment;
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                return true;
            }
        });



    }
}