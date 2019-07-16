package com.example.petspa_version_2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petspa_version_2.Model.Booking;
import com.example.petspa_version_2.Model.ServicePet;
import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookingDetail extends AppCompatActivity {
    private ServicePet servicePet;
    DrawerLayout menuLayoutDrawer = null;
    NavigationView bookingDetailMenu = null;

    private TextView txtTitle, txtDescription, txtServicePetContent, txtPrice;
    ImageView imageService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);

        menuLayoutDrawer = findViewById(R.id.menuBookingDetailLayoutDrawer);
        bookingDetailMenu = findViewById(R.id.bookingDetailMenu);

        txtDescription = findViewById(R.id.txtDescription);
        txtTitle = findViewById(R.id.txtTitle);
        txtServicePetContent = findViewById(R.id.txtServicePetContent);
        txtPrice = findViewById(R.id.txtPrice);
        imageService = findViewById(R.id.imageService);

        Intent intent = BookingDetail.this.getIntent();
        servicePet = (ServicePet) intent.getSerializableExtra("bookingDetail");

        txtPrice.setText(servicePet.getServicePrice());
        txtTitle.setText(servicePet.getServiceTitle());
        txtDescription.setText(servicePet.getServiceDescription());
        txtServicePetContent.setText(servicePet.getServiceContent());
        imageService.setImageResource(servicePet.getServiceImage());


    }

    @Override
    protected void onStart() {
        super.onStart();

        bookingDetailMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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

                if(item.getItemId() == R.id.item_booking_list){

                    Intent intent = new Intent(getApplicationContext(), ListBookingActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);

                }

                if (item.getItemId() == R.id.item_user_logout) {
                    //Clear authentication key in SharedPreferences
                    SharedPreferences.Editor editor = getSharedPreferences("Email", MODE_PRIVATE).edit();
                    editor.putString("Email", " ");
                    editor.apply();

                    //Back to login page
                    Intent intent = new Intent(BookingDetail.this, LoginActivity.class);
                    //Remove back button
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                menuLayoutDrawer.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void clickToBack(View view) {
        finish();
    }

    public void clickToOpenMenu(View view) {
        menuLayoutDrawer.openDrawer(Gravity.RIGHT);
    }

    public void clickToRating(View view) {
        Intent intent = new Intent(getApplicationContext(), ListBookingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    public void clickToGGM(View view){
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}
