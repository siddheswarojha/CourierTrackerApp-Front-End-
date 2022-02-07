package com.cat.couriertracker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cat.couriertracker.R;

public class SignUp extends AppCompatActivity {


    EditText etNameSignUp,etEmailSignUp,etPasswordSignUp;
    Button btnSignUp;
    TextView tvGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmailSignUp = findViewById(R.id.etEmailSignUp);
        etNameSignUp=findViewById(R.id.etNameSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);
        btnSignUp=findViewById(R.id.btnSignUp);
        tvGoToLogin=findViewById(R.id.tvGotoLoginPage);




//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        tvGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this,Login.class);
                startActivity(i);
            }
        });

    }
}