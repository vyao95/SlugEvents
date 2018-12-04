package com.example.cmps121.slugevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.database.Cursor;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.bumptech.glide.Glide;

public class CustomizeProfileScreen extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;

    DatabaseReference databaseRef;
    DatabaseReference profileRef;
    StorageReference storageRef;
    StorageReference uploadRef;
    StorageReference imageRef;
    FirebaseAuth auth;
    FirebaseUser u;

    String selectedImagePath;
    Uri selectedImageUri;
    ImageView profileImage;
    EditText name;
    EditText bio;
    Button editPicBtn;
    Button saveBtn;
    Button cancelBtn;

    private static final String TAG = "CustomizeProfileScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_profile_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();
        u = auth.getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference();
        storageRef = FirebaseStorage.getInstance().getReference();

        profileImage = (ImageView) findViewById(R.id.profileImage);
        name = (EditText) findViewById(R.id.name);
        bio =  (EditText) findViewById(R.id.bio);
        editPicBtn = (Button) findViewById(R.id.editPicBtn);

        editPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfilePic();
                saveProfile();
            }
        });

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomizeProfileScreen.this, Home.class));
            }
        });
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void saveProfile() {
        profileRef = databaseRef.child("profiles/users/" + u.getUid());
        String nameText = name.getText().toString();
        String bioText = bio.getText().toString();

        if (!TextUtils.isEmpty(nameText)) {
            Profile p = new Profile(nameText, bioText);
            profileRef.child("data").setValue(p);
            Intent i = new Intent(CustomizeProfileScreen.this, Home.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "You have not filled out a required field", Toast.LENGTH_LONG).show();
        }
    }

    private void getProfile() {
        profileRef = databaseRef.child("profiles/users/" + u.getUid() + "/data");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Profile p = dataSnapshot.getValue(Profile.class);
               name.setText(p.getName());
               bio.setText(p.getBio());
               String n = p.getName();
               System.out.println("Name: " + n);
               System.out.println("Bio: " + p.getBio());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        imageRef = storageRef.child("images/users/" + u.getUid() + "/profilePic.jpeg");
        Glide.with(this)
                .load(imageRef)
                .into(profileImage);
    }
    private void saveProfilePic() {
        uploadRef = storageRef.child("images/users/" + u.getUid() + "/profilePic.jpeg");
        uploadRef.putFile(selectedImageUri).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Failed to upload file");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(), "Upload Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                profileImage.setImageURI(selectedImageUri);
            }
        }
    }

}
