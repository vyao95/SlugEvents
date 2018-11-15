package com.example.cmps121.slugevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.AuthCredential;
import com.example.cmps121.slugevents.MainScreen;
import com.example.cmps121.slugevents.R;

public class RegisterScreen extends AppCompatActivity implements OnClickListener {

    private FirebaseAuth mAuth;
    private Button signUpBtn;
    private EditText etEmail;
    private EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        mAuth = FirebaseAuth.getInstance();

        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);

        signUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            registerProcess(email, password);
        }
    }

    private void registerProcess(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            startActivity(new Intent(RegisterScreen.this, LoginScreen.class));
                        }
                    }
                });
    }
}

