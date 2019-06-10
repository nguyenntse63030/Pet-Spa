package com.example.petspa_version_2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.petspa_version_2.R;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        RelativeLayout relativeLayout = findViewById(R.id.mainLayout);

        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{Color.parseColor("#049af7"), Color.parseColor("#02c2f9")});
        relativeLayout.setBackground(gradientDrawable);
    }
}
