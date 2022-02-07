package com.cat.couriertracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cat.couriertracker.Adapters.GetAllTrackingDetails;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnGetTrackingDetails;
    RecyclerView rvMain;
    GetAllTrackingDetails getAllTrackingDetailsAdapter;

    ArrayList<Long> orderId;

    ArrayList<String> name, itemType,startLocation, currentLocation,expectedDeliveryDate,status, deliveryLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnGetTrackingDetails=findViewById(R.id.btnGetTrackingDetails);

        rvMain=findViewById(R.id.rvMain);

        orderId=new ArrayList<>();
        name=new ArrayList<>();
        itemType=new ArrayList<>();
        startLocation=new ArrayList<>();
        currentLocation=new ArrayList<>();
        expectedDeliveryDate=new ArrayList<>();
        status=new ArrayList<>();
        deliveryLocation=new ArrayList<>();


        getAllTrackingDetailsAdapter = new GetAllTrackingDetails(orderId,name,status);


        btnGetTrackingDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getAllTrackingDetail();

            }
        });
    }

    private void getAllTrackingDetail()
    {
        String url = "https://courier-application-tracker.herokuapp.com/api/v1/cat/getTrackingDetails";

        Log.d("urlSidd",url);

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                Log.d("responseSidd",response.toString());

                try {

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject jsonObjectRequest = response.getJSONObject(i);

                        orderId.add(jsonObjectRequest.getLong("orderId"));
                        name.add(jsonObjectRequest.getString("name"));
                        itemType.add(jsonObjectRequest.getString("itemType"));
                        startLocation.add(jsonObjectRequest.getString("startLocation"));
                        currentLocation.add(jsonObjectRequest.getString("currentLocation"));
                        expectedDeliveryDate.add(jsonObjectRequest.getString("expectedDeliveryDate"));
                        status.add(jsonObjectRequest.getString("status"));
                        deliveryLocation.add(jsonObjectRequest.getString("deliveryLocation"));




                    }
                    rvMain.setAdapter(getAllTrackingDetailsAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}