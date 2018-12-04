package com.example.cmps121.slugevents;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
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
    Query query;
    FirebaseAuth auth;
    FirebaseUser u;
    List<String> eventData;
    FirebaseListAdapter<Event> adapter;
    FirebaseListAdapter<Event> newAdapter;
    ListView listView;
    String tag = "all";

    public void setFun(View v){
        tag = "Fun";
        setUpAdapter();
        listView.setAdapter(adapter);
    }

    public void setSocial(View v) {
        tag = "Social";
        setUpAdapter();
        listView.setAdapter(adapter);
    }

    public void setEducation(View v){
        tag = "Education";
        setUpAdapter();
        listView.setAdapter(adapter);
    }

    public void setUpAdapter(){
        if (tag != "all") {
            query = FirebaseDatabase.getInstance().getReference().child("events").orderByChild("tag").equalTo(tag);
        } else {
            query = FirebaseDatabase.getInstance().getReference().child("events");
        }
        /*
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long count = dataSnapshot.getChildrenCount();
                int x = 0;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */
        FirebaseListOptions<Event> options = new FirebaseListOptions.Builder<Event>()
                .setLayout(R.layout.events)//Note: The guide doesn't mention this method, without it an exception is thrown that the layout has to be set.
                .setQuery(query, Event.class)
                .build();

        newAdapter = new FirebaseListAdapter<Event>(options) {
            @Override
            protected void populateView(View view, Event model, int position) {
                final String nameText = model.getName();
                String timeText = model.getTime();
                String timeEndText = model.getTimeEnd();
                final String locationText = model.getLocation();
                final String dateText = model.getDate();
                final String emailText = model.getEmail();
                final String tagText = model.getTag();

                TextView name = (TextView) view.findViewById(R.id.name);
                TextView time = (TextView) view.findViewById(R.id.time);
                TextView timeEnd = (TextView) view.findViewById(R.id.timeEnd);
                TextView date = (TextView) view.findViewById(R.id.date);
                TextView location = (TextView) view.findViewById(R.id.location);
                TextView email = (TextView) view.findViewById(R.id.email);
                TextView tag = (TextView) view.findViewById(R.id.tag);

                name.setText(nameText);
                time.setText(timeText);
                timeEnd.setText(timeEndText);
                date.setText(dateText);
                location.setText(locationText);
                email.setText(emailText);
                tag.setText(tagText);

                Button RSVPbtn = (Button) view.findViewById(R.id.RSVPBtn);
                RSVPbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", emailText , null));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "EVENT RSVP");
                        intent.putExtra(Intent.EXTRA_TEXT, "Hi I would like to attend your Event.");
                        startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                    }
                });
                Button Calbtn = (Button) view.findViewById(R.id.CalBtn);
                Calbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar beginTime = Calendar.getInstance();
                        beginTime.set(2018, 11, 4, 17, 30);
                        Calendar endTime = Calendar.getInstance();
                        endTime.set(2018, 11, 4, 18, 30);
                        Intent intent = new Intent(Intent.ACTION_INSERT)
                                .setData(CalendarContract.Events.CONTENT_URI)
                                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                                .putExtra(CalendarContract.Events.TITLE, nameText)
                                .putExtra(CalendarContract.Events.DESCRIPTION, tagText)
                                .putExtra(CalendarContract.Events.EVENT_LOCATION, locationText)
                                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                                .putExtra(Intent.EXTRA_EMAIL, emailText);
                        startActivity(intent);
                    }
                });
            }
        };
        adapter = newAdapter;
    }
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

        listView = (ListView) findViewById(R.id.listView);

        auth = FirebaseAuth.getInstance();
        u = auth.getCurrentUser();

        setUpAdapter();
        listView.setAdapter(adapter);
    }


}
