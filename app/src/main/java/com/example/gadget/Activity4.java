package com.example.gadget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Activity4 extends AppCompatActivity {
    //Initialize Variables
    CalendarView calendar;
    TextView deliverydate;
    Button confirmorder;
    String st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        calendar = (CalendarView) findViewById(R.id.calendar1);
        deliverydate = (TextView) findViewById(R.id.textView2);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1+1) + "/" + i2 + "/" + i;
                deliverydate.setText(date);
            }
        });
        //Assign Variable
        confirmorder = findViewById(R.id.confirmorder);
        deliverydate = findViewById(R.id.deliverydate);
        //Perform onClick Event
        confirmorder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Activity4.this,Activity5.class);
                st = deliverydate.getText().toString();
                i.putExtra("Value",st);
                startActivity(i);
                finish();
            }
        });
    }
}