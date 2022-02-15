package com.cat.couriertracker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cat.couriertracker.R;
import com.shuhart.stepview.StepView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityDeliveryDetails extends AppCompatActivity {

    Button btnGenerateBill,btnCancelParcel;

    TextView tvOrderStatus, tvOrderOrderId,tvOrderName,tvOrderDeliveryLocation,tvOrderDeliveryDate;
    StepView stepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);

        //hooks
        btnGenerateBill = findViewById(R.id.btnGenerateBill);
        btnCancelParcel=findViewById(R.id.btnCancelPackage);
        tvOrderDeliveryDate=findViewById(R.id.tvOrderDeliveryDate);
        tvOrderStatus=findViewById(R.id.tvOrderStatus);
        tvOrderOrderId=findViewById(R.id.tvOrderOrderId);
        tvOrderName=findViewById(R.id.tvOrderName);
        tvOrderDeliveryLocation=findViewById(R.id.tvOrderDeliveryLocation);
        stepView=findViewById(R.id.stepView);



        Intent i = getIntent();
        String orderId = i.getStringExtra("orderId");

        getTrackingDetailsForOrderId(orderId);

        btnGenerateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityDeliveryDetails.this, "Bill", Toast.LENGTH_SHORT).show();
            }
        });


        btnCancelParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelPackage(orderId);
            }
        });




    }

    private void cancelPackage(String orderId)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(ActivityDeliveryDetails.this);
        String url = "https://courier-application-tracker.herokuapp.com/api/v1/cat/cancelDelivery/"+ orderId;

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("responseCancelDelivery", response);

                if(response.equals("Order Canceled")){
                    Toast.makeText(ActivityDeliveryDetails.this, "", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ActivityDeliveryDetails.this,SearchPackageActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(ActivityDeliveryDetails.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("errorFound", error.toString());
            }
        }) {




        };

        requestQueue.add(stringRequest);
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

                    String status = response.getString("status");
                    tvOrderStatus.setText(response.getString("status"));
                    tvOrderDeliveryDate.setText("Delivery Date: "+response.getString("expectedDeliveryDate"));
                    tvOrderOrderId.setText("Order ID: "+ response.getString("orderId"));
                    tvOrderName.setText("Name: "+response.getString("name"));
                    tvOrderDeliveryLocation.setText("Delivery Address: "+response.getString("deliveryLocation"));

                    if(status.equals("Order Placed"))
                    {
                        stepView.go(1,true);
                    }
                    if(status.equals("Dispatched"))
                    {
                        stepView.go(2,true);
                    }
                    if(status.equals("In Transit"))
                    {
                        stepView.go(3,true);
                    }
                    if(status.equals("Delivered"))
                    {
                        stepView.go(4,true);
                    }


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
