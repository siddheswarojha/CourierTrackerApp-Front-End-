package com.cat.couriertracker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.cat.couriertracker.R;

public class SplashScreenActivity extends AppCompatActivity {

    private Thread th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        th = new Thread();
        try {
            th.sleep(300);
            Intent i = new Intent(SplashScreenActivity.this,SearchPackageActivity.class);
            startActivity(i);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}