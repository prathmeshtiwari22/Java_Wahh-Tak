package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edPassword, edEmail, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextLTBName);
        edEmail = findViewById(R.id.editTextLTBAddress);
        edPassword = findViewById(R.id.editTextLTBPC);
        edConfirm = findViewById(R.id.editTextLTBCN);
        btn = findViewById(R.id.buttonLTBBook);
        tv = findViewById(R.id.textViewNewUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String Confirm = edConfirm.getText().toString();
                Databse db=new Databse(getApplicationContext(),"healthcare",null,1);
                if (username.length() == 0 || password.length() == 0 || Confirm.length() == 0 || email.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill details", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(Confirm) == 0) {
                        if (isValid(password)) {
                         db.register(username,email,password);
                         Toast.makeText(getApplicationContext(),"Record inserted",Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain 8 characters,having letter,digits and special characters", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password not Same", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }


    public static boolean isValid(String passwordhere) {
        int f1=0,f2=0,f3=0,f4=0;
        if(passwordhere.length() < 8) {
            return false;
        }
        else {
            for(int p=0;p<passwordhere.length();p++) {
                if (Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for(int q=0;q<passwordhere.length();q++) {
                if (Character.isDigit(passwordhere.charAt(q))){
                    f2=1;
                }
            }
            for(int s=0;s<passwordhere.length();s++) {
                char c =passwordhere.charAt(s);
                if(c>33&&c<=46||c==64){
                    f3=1;
                }
                }
            for(int g=0;g<passwordhere.length();g++) {
                if ((passwordhere.charAt(g)=='@')){
                    f4=1;
                }
            }
        if(f1==1 && f2==1 && f3==1 && f4==1){
            return true;
        }
        return false;
        }
    }
}