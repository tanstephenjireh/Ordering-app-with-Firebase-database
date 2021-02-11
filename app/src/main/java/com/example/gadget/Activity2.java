package com.example.gadget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Activity2 extends AppCompatActivity {
    Button next;
    private Button customercode;
    private TextView code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        next = (Button) findViewById(R.id.next);

        customercode = findViewById(R.id.customercode);
        code = findViewById(R.id.code);

        customercode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                code.setText(getRandomString(4));
            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTypecode();
            }
        });

    }

    public static String getRandomString(int i) {
        final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder();
        while (i > 0) {
            Random rand = new Random();
            result.append(characters.charAt(rand.nextInt(characters.length())));
            i--;

        }
        return result.toString();
    }
    public void openTypecode(){
        Intent intent = new Intent(this,Typecode.class);
        startActivity(intent);
    }


}





