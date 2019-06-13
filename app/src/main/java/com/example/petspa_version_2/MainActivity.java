package com.example.petspa_version_2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petspa_version_2.Activity.LoadingActivity;

public class MainActivity extends AppCompatActivity {

    private static int LOADING_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set ProgressBar color
        ProgressBar spinner = findViewById(R.id.spinner);
        spinner.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FFFFFF"),android.graphics.PorterDuff.Mode.MULTIPLY);

        //Loading time
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
                //remove back button
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }, LOADING_TIME_OUT);

    }
}
