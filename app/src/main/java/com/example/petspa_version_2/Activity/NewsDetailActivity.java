package com.example.petspa_version_2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petspa_version_2.Model.Booking;
import com.example.petspa_version_2.Model.News;
import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewsDetailActivity extends AppCompatActivity {
    DrawerLayout menuLayoutDrawer;
    NavigationView menuNewsDetail;
    Button btnMenu, btnBack;

    TextView txtDateOfNews, txtNewsDetailContent, txtNewsDetailTitle;
    ImageView imageNewsDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        txtDateOfNews = findViewById(R.id.txtDateOfNews);
        txtNewsDetailContent = findViewById(R.id.txtNewsDetailContent);
        imageNewsDetail = findViewById(R.id.imageNewsDetail);
        txtNewsDetailTitle = findViewById(R.id.txtNewsDetailTitle);

        menuLayoutDrawer = findViewById(R.id.menuLayoutDrawer);
        menuNewsDetail = findViewById(R.id.menuNewsDetail);
        btnBack = findViewById(R.id.btnBack);
        btnMenu = findViewById(R.id.btnMenu);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = NewsDetailActivity.this.getIntent();

        News news = (News) intent.getSerializableExtra("news");

        txtNewsDetailTitle.setText(news.getNewsTitle());
        txtNewsDetailContent.setText(news.getNewsContentDetail());
        txtDateOfNews.setText(news.getDateOfNews());
        imageNewsDetail.setImageResource(news.getNewsImage());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLayoutDrawer.openDrawer(Gravity.RIGHT);
            }
        });

        menuNewsDetail.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.item_home){
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

                if(item.getItemId() == R.id.item_user_profile){
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }

                if(item.getItemId() == R.id.item_news){
                    Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

                if(item.getItemId() == R.id.item_booking_list){
                    List<Booking> listBooking = new ArrayList<>();
                    SharedPreferences pref = getSharedPreferences("listBooking", Context.MODE_PRIVATE);

                    Gson gson = new Gson();
                    String json = pref.getString("DATA_BOOKING", "");
                    Type type = new TypeToken<ArrayList<Booking>>(){}.getType();

                    listBooking = gson.fromJson(json, type);

                    if(listBooking == null){
                        dialogMessEmptyListBooking();
                    }
                    else if(listBooking.isEmpty()){
                        dialogMessEmptyListBooking();
                    }else {
                        Intent intent = new Intent(getApplicationContext(), ListBookingActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                }

                if(item.getItemId() == R.id.item_user_logout){
                    //Clear authentication key in SharedPreferences
                    SharedPreferences.Editor editor = getSharedPreferences("Email", MODE_PRIVATE).edit();
                    editor.putString("Email", " ");
                    editor.apply();

                    //Back to login page
                    Intent intent = new Intent(NewsDetailActivity.this, LoginActivity.class);
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

    private void dialogMessEmptyListBooking(){
        AlertDialog.Builder mess = new AlertDialog.Builder(this);
        mess.setTitle("No list Booking!");

        mess.setMessage("Please book service first")
                .setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog showMess = mess.create();
        showMess.show();
    }
}
