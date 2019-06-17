package com.example.petspa_version_2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Field;
import java.util.Date;

public class BookingActivity extends AppCompatActivity {

    DrawerLayout drawerLayout = null;
    NavigationView navigationView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        drawerLayout = findViewById(R.id.menuLayoutDrawer);
        navigationView = findViewById(R.id.bookingMenu);

        Spinner spinnerTopLeft = findViewById(R.id.spinnerTopLeft);

        Spinner spinnerTopRight = findViewById(R.id.spinnerTopRight);

        Spinner spinnerBottomLeft = findViewById(R.id.spinnerBottomLeft);

        Spinner spinnerBottomRight = findViewById(R.id.spinnerBottomRight);


        String[] stlItems = new String[]{"08", "09", "10", "11", "13", "14", "15", "16", "17"};
        String[] strItems = new String[]{"00", "30"};

        String[] sbrItems = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "11", "10", "12"};
        String[] sblItems = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "11", "10", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
                "25", "26", "27", "28", "29", "30", "31"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.spinner_item, stlItems);
        spinnerTopLeft.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.spinner_item, strItems);
        spinnerTopRight.setAdapter(adapter2);


        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, R.layout.spinner_item, sblItems);
        spinnerBottomLeft.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, R.layout.spinner_item, sbrItems);
        spinnerBottomRight.setAdapter(adapter4);
        setCurrentDate(spinnerBottomLeft, spinnerBottomRight);
    }

    private void setCurrentDate(Spinner spinnerDay, Spinner spinnerMonth) {
        Date currentDate = new Date();
        int day = currentDate.getDate();
        int month = currentDate.getMonth();
        spinnerDay.setSelection(day - 1);
        spinnerMonth.setSelection(month);
    }

    public void clickGoToListService(View view) {
        finish();
    }

    public void openMenu(View view) {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_home) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.item_news) {
                    Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }

                if (item.getItemId() == R.id.item_user_profile) {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }

                if (item.getItemId() == R.id.item_user_logout) {
                    //Clear authentication key in SharedPreferences
                    SharedPreferences.Editor editor = getSharedPreferences("Email", MODE_PRIVATE).edit();
                    editor.putString("Email", " ");
                    editor.apply();

                    //Back to login page
                    Intent intent = new Intent(BookingActivity.this, LoginActivity.class);
                    //Remove back button
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
