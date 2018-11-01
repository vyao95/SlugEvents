package com.example.cmps121.slugevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button customizeProfileBtn = (Button) findViewById(R.id.customizeProfileBtn);
        customizeProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainScreen.this, CustomizeProfileScreen.class));
            }
        });

        Button createEventBtn = (Button) findViewById(R.id.createEventBtn);
        createEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainScreen.this, CreateEventScreen.class));
            }
        });

        Button discoverEventsBtn = (Button) findViewById(R.id.discoverEventsBtn);
        discoverEventsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainScreen.this, DiscoverEventsScreen.class));
            }
        });

    }

}
