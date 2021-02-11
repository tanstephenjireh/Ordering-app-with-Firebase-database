package com.example.gadget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.proto.TargetGlobal;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button register;
    //Assign Variables
    EditText nameInput;
    EditText contactInput;
    EditText homeInput;
    EditText emailInput;
    EditText passwordInput;
    TextView signin;
    ProgressBar progressbar;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin = (TextView) findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivitySignIn();

            }
        });


        nameInput = findViewById(R.id.nameInput);
        contactInput = findViewById(R.id.contactInput);
        homeInput = findViewById(R.id.homeInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);


        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        progressbar = findViewById(R.id.progressbar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Signin.class));
            finish();
        }
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                final String fullname = nameInput.getText().toString().trim();
                final String phone = contactInput.getText().toString().trim();
                final String homeaddress = homeInput.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    emailInput.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    passwordInput.setError("Password is Required");
                    return;
                }
                if(password.length() < 6){
                    passwordInput.setError("Password must be greater than 6 characters");
                    return;
                }
                if(phone.length() <11){
                    contactInput.setError("Must be 11 digits");
                    return;
                }

                progressbar.setVisibility(View.VISIBLE);

                // register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "User Created", Toast.LENGTH_LONG).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("Users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Name",fullname);
                            user.put("Phone",phone);
                            user.put("Home Address",homeaddress);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for"+ userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),Signin.class));

                        }else {
                            Toast.makeText(MainActivity.this, "Error! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

    }
    private void openActivitySignIn(){
        Intent intent = new Intent(this, Signin.class);
        startActivity(intent);
    }
}