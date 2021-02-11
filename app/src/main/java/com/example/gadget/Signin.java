package com.example.gadget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText emailInput;
    EditText passwordInput;
    Button signin;
    TextView registerhere;
    ProgressBar progressbar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        signin = (Button) findViewById(R.id.signin);
        registerhere = findViewById(R.id.registerhere);
        progressbar = findViewById(R.id.progressbar);
        fAuth = FirebaseAuth.getInstance();

            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = emailInput.getText().toString().trim();
                    String password = passwordInput.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        emailInput.setError("Email is Required");
                        return;
                    }
                    if (TextUtils.isEmpty(password)) {
                        passwordInput.setError("Password is Required");
                        return;
                    }
                    if (password.length() < 6) {
                        passwordInput.setError("Password must be greater than 6 characters");
                        return;
                    }

                    progressbar.setVisibility(View.VISIBLE);

                    // authenticate the user

                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Signin.this, "Signed in Successfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), Activity2.class));
                            } else {
                                Toast.makeText(Signin.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                progressbar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            });

            registerhere.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });
        }
    }