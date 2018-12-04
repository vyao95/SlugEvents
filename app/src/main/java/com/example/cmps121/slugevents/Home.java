package com.example.cmps121.slugevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Button customizeProfileBtn = (Button) findViewById(R.id.customizeProfileBtn);
        customizeProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(Home.this, CustomizeProfileScreen.class));
            }
        });

        Button createEventBtn = (Button) findViewById(R.id.createEventBtn);
        createEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(Home.this, CreateEventScreen.class));
            }
        });

        Button discoverEventsBtn = (Button) findViewById(R.id.discoverEventsBtn);
        discoverEventsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(Home.this, DiscoverEventsScreen.class));
            }
        });

    }

}
