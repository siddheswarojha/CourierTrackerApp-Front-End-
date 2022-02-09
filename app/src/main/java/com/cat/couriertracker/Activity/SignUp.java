package com.cat.couriertracker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cat.couriertracker.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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




        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etNameSignUp.getText().toString().trim();
                String password = etPasswordSignUp.getText().toString().trim();
                String email = etEmailSignUp.getText().toString().trim();


                createAccount(name,password,email);

            }
        });

        tvGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this,Login.class);
                startActivity(i);
            }
        });

    }

    private void createAccount(String name, String password, String email){
//    {
        RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);
        String url = "https://courier-application-tracker.herokuapp.com/api/v1/cat/auth/userSignUp";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseFromServer",response);



                if(response.equals("Registered"))
                {
                    Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(SignUp.this,Login.class);
                    startActivity(i);
                }
                else if(response.equals("Email Address already in use"))
                {
                    Toast.makeText(SignUp.this, "Email Address already in use", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SignUp.this, "Please Try Again Later", Toast.LENGTH_SHORT).show();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("errorFound", error.toString());
            }
        }) {

//        @Override
//        public Map<String, String> getHeaders() throws AuthFailureError{
//        HashMap<String, String> headers = new HashMap<String, String>();
//        headers.put("Content-Type", "application/json");
//        headers.put( "charset", "utf-8");
//        return headers;
//    }

            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name",name);
                params.put("password",password);
                params.put("emailAddress",email);
                Log.d("paramsValue", String.valueOf(params));
                return params;

            }


        };

            requestQueue.add(stringRequest);
    }
}