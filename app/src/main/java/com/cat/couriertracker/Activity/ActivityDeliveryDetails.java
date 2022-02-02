package com.cat.couriertracker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cat.couriertracker.R;
import org.json.JSONObject;

public class ActivityDeliveryDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);


        Intent i = getIntent();
        String orderId = i.getStringExtra("orderId");

        getTrackingDetailsForOrderId(orderId);
    }



        private void getTrackingDetailsForOrderId(String orderId)
    {
        String url = "https://courier-application-tracker.herokuapp.com/api/v1/cat/getTrackingDetail/"+orderId;

        Log.d("urlSidd",url);

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityDeliveryDetails.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                Log.d("responseSidd",response.toString());

                try {





                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ActivityDeliveryDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);


    }
    }
