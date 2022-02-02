package com.cat.couriertracker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cat.couriertracker.MainActivity;
import com.cat.couriertracker.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchPackageActivity extends AppCompatActivity {

    Button btnSearchOrderDetails;
    EditText etOrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_package);

        btnSearchOrderDetails=findViewById(R.id.btnSearchOrderDetails);
        etOrderId=findViewById(R.id.etOrderId);


        btnSearchOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderId = etOrderId.getText().toString();
                Intent i = new Intent(SearchPackageActivity.this,ActivityDeliveryDetails.class);
                i.putExtra("orderId",orderId);
                startActivity(i);
            }
        });
    }

}