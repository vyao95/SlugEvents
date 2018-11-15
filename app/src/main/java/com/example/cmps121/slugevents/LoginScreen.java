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

public class LoginScreen extends AppCompatActivity implements OnClickListener{
    private EditText etEmail, etPassword;
    private Button login, register;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mAuth = FirebaseAuth.getInstance();


        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.signUpBtn);
        login = (Button) findViewById(R.id.loginBtn);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.signUpBtn:
                startActivity(new Intent(LoginScreen.this, RegisterScreen.class));
                break;

            case R.id.loginBtn:
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {

                    loginProcess(email, password);

                }
                break;
        }
    }


    private void loginProcess(String email, String password) {

        //AuthCredential credential = GoogleAuthProvider.getCredential(email, password);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            FirebaseUser user = task.getResult().getUser();


                            startActivity(new Intent(LoginScreen.this, MainScreen.class));

                        }


                    }
                });
    }

}