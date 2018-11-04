package com.example.cmps121.slugevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateEventScreen extends AppCompatActivity {

    DatabaseReference databaseEvents;
    FirebaseAuth auth;
    FirebaseUser u;
    EditText etName, etTime, etLocation, etDate;
    Button exitBtn, createBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_screen);

        createBtn = (Button) findViewById(R.id.createEventBtn);
        exitBtn = (Button) findViewById(R.id.cancelBtn);

        etName = (EditText) findViewById(R.id.inputName);
        etTime = (EditText) findViewById(R.id.inputTime);
        etLocation = (EditText) findViewById(R.id.inputLocation);
        etDate = (EditText) findViewById(R.id.inputDate);


        //Commenting this line all the way down will make it work. Firebase causing issues.
        databaseEvents = FirebaseDatabase.getInstance().getReference("events");
        auth = FirebaseAuth.getInstance();
        u = auth.getCurrentUser();


        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateEventScreen.this, MainScreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });


        createBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addEvent();
            }
        });
    }
    private void addEvent() {

    String name = etName.getText().toString();
    String time = etTime.getText().toString();
    String location = etLocation.getText().toString();
    String date = etDate.getText().toString();
    String email = u.getEmail();


        if(!TextUtils.isEmpty(name)
            && !TextUtils.isEmpty(time)
            && !TextUtils.isEmpty(location)
            && !TextUtils.isEmpty(date)){


        String id = databaseEvents.push().getKey();
        Event event = new Event(id, name, time, location, date, email);
        databaseEvents.child(id).setValue(event);
        Intent i = new Intent(CreateEventScreen.this, MainScreen.class);
        startActivity(i);
    }
    else{
        Toast.makeText(this, "You have not filled out a required field", Toast.LENGTH_LONG).show();
    }
}
}

