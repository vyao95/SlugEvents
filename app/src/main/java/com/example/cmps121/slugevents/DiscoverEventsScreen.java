package com.example.cmps121.slugevents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.Query;

public class DiscoverEventsScreen extends AppCompatActivity {
    DatabaseReference databaseEvents;
    FirebaseAuth auth;
    FirebaseUser u;
    List<String> eventData;
    FirebaseListAdapter<Event> adapter;
    ListView listView;
    Button RSVPbtn;
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_events_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RSVPbtn = (Button) findViewById(R.id.RSVPbtn);
        RSVPbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","b@b.com" , null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "EVENT RSVP");
                intent.putExtra(Intent.EXTRA_TEXT, "Hi I would like to attend your Event.");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });


        listView = (ListView) findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("events");
        databaseEvents = FirebaseDatabase.getInstance().getReference("events");
        auth = FirebaseAuth.getInstance();
        u = auth.getCurrentUser();

        FirebaseListOptions<Event> options = new FirebaseListOptions.Builder<Event>()
                .setLayout(R.layout.events)//Note: The guide doesn't mention this method, without it an exception is thrown that the layout has to be set.
                .setQuery(query, Event.class)
                .build();

        adapter = new FirebaseListAdapter<Event>(options) {
            @Override
            protected void populateView(View view, Event model, int position) {
                String nameText = model.getName();
                String timeText = model.getTime();
                String locationText = model.getLocation();
                String dateText = model.getDate();
                String emailText = model.getEmail();
                String tagText = model.getTag();
               // String RSVPText = model.getRSVP();

                TextView name = (TextView) view.findViewById(R.id.name);
                TextView time = (TextView) view.findViewById(R.id.time);
                TextView date = (TextView) view.findViewById(R.id.date);
                TextView location = (TextView) view.findViewById(R.id.location);
                TextView email = (TextView) view.findViewById(R.id.email);
                TextView tag = (TextView) view.findViewById(R.id.tag);
             //   TextView rsvp = (TextView) view.findViewById(R.id.RSVP);

                name.setText(nameText);
                time.setText(timeText);
                date.setText(dateText);
                location.setText(locationText);
                email.setText(emailText);
                tag.setText(tagText);
              //  rsvp.setText(RSVPText);
            }
        };
        listView.setAdapter(adapter);
    }


}