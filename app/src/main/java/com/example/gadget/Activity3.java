package com.example.gadget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    CheckBox checkBox, checkBox1, checkBox2, checkBox3, checkBox4;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        NumberPicker numberPicker1 = findViewById(R.id.numberpicker1);
        textView3 = findViewById(R.id.textView3);

        numberPicker1.setMinValue(1);
        numberPicker1.setMaxValue(14);

        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                textView3.setText("Quantity: " + newVal);
            }
        });

        NumberPicker numberPicker2 = findViewById(R.id.numberpicker2);
        textView4 = findViewById(R.id.textView4);

        numberPicker2.setMinValue(1);
        numberPicker2.setMaxValue(21);

        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                textView4.setText("Quantity: " + newVal);
            }
        });

        NumberPicker numberPicker3 = findViewById(R.id.numberpicker3);
        textView5 = findViewById(R.id.textView5);

        numberPicker3.setMinValue(0);
        numberPicker3.setMaxValue(0);

        numberPicker3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                textView5.setText("Quantity: " + newVal);
            }
        });

        NumberPicker numberPicker4 = findViewById(R.id.numberpicker4);
        textView6 = findViewById(R.id.textView6);

        numberPicker4.setMinValue(1);
        numberPicker4.setMaxValue(12);

        numberPicker4.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                textView6.setText("Quantity: " + newVal);
            }
        });

        NumberPicker numberPicker5 = findViewById(R.id.numberpicker5);
        textView7 = findViewById(R.id.textView7);

        numberPicker5.setMinValue(1);
        numberPicker5.setMaxValue(27);

        numberPicker5.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                textView7.setText("Quantity: " + newVal);
            }
        });

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity4();

            }
        });

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);

    }
    private void openActivity4(){

        Intent intent = new Intent(this, Activity4.class);
        startActivity(intent);
    }
    public void toDo(View v){

        if (v.equals(checkBox))
            Toast.makeText(getApplicationContext(),
                    "Monitor is available",
                    Toast.LENGTH_SHORT).show();

        if (v.equals(checkBox1))
            Toast.makeText(getApplicationContext(),
                    "Keyboard is available",
                    Toast.LENGTH_SHORT).show();

        if (v.equals(checkBox2))
            Toast.makeText(getApplicationContext(),
                    "Printer is unavailable",
                    Toast.LENGTH_SHORT).show();

        if (v.equals(checkBox3))
            Toast.makeText(getApplicationContext(),
                    "Webcam is available",
                    Toast.LENGTH_SHORT).show();

        if (v.equals(checkBox4))
            Toast.makeText(getApplicationContext(),
                    "Headset is available",
                    Toast.LENGTH_SHORT).show();
    }

}