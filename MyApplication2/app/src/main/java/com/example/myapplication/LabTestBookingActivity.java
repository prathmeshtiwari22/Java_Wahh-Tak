package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookingActivity extends AppCompatActivity {
    Button btnBooking;
    EditText edname,edAddress,edcontact,edpincode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_booking);
        edname=findViewById(R.id.editTextLTBName);
        edAddress=findViewById(R.id.editTextLTBAddress);
        edcontact=findViewById(R.id.editTextLTBCN);
        edpincode=findViewById(R.id.editTextLTBPC);
        btnBooking=findViewById(R.id.buttonLTBBook);

        Intent intent = getIntent();
        String[] price= intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedpreferences.getString("username","").toString();
                Databse db=new Databse(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edname.getText().toString(),edAddress.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edpincode.getText().toString())
                        ,date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username,"lab");
                Toast.makeText(getApplicationContext(),"Your Booking is done successfully",Toast.LENGTH_LONG);
                startActivity( new Intent(LabTestBookingActivity.this,HomeActivity.class));
            }
        });
    }
}