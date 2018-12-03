package com.example.cmps121.slugevents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Event  {
    String id;
    String name;
    String time;
    String location;
    String date;
    String email;
    String tag;
    Button RSVPbtn;
   // String RSVP;

   // @Override
   // protected void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_discover_events_screen);
    //    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);

     //   RSVPbtn = (Button) findViewById(R.id.RSVPbtn);
     //   RSVPbtn.setOnClickListener(new View.OnClickListener() {
     //       @Override
     //       public void onClick(View view) {
     //           Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
      //                  "mailto", getEmail(), null));
      //          intent.putExtra(Intent.EXTRA_SUBJECT, "EVENT RSVP");
       //         intent.putExtra(Intent.EXTRA_TEXT, "Hi I would like to attend your Event.");
       //         startActivity(Intent.createChooser(intent, "Choose an Email client :"));
      //      }
     //   });
    //}


    public Event() {

    }
    public Event(String id, String name, String time, String location, String date, String email, String tag) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.location = location;
        this.date = date;
        this.email = email;
        this.tag = tag;
       // this.RSVP = RSVP;
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() { return location; }

    public String getDate() {
        return date;
    }

    public String getEmail() {return email;}

    public String getTag() {return tag;}
  //  public String getRSVP() {return RSVP;}
}


// public String getRSVP() {return RSVP;}

//  public String getRSVP() {return RSVP;}
//code for button couldnt get button to show up in screen

//<Button style="@style/Widget.AppCompat.Button.Colored"
 //       android:id="@+id/button"
   //     android:layout_width="match_parent"
     //   android:layout_height="wrap_content"
       // android:text="RSVP"
        //app:backgroundTint="@color/wallet_bright_foreground_holo_light"/>