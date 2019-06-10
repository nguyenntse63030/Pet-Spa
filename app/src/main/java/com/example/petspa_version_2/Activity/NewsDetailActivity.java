package com.example.petspa_version_2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petspa_version_2.Model.News;
import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;

public class NewsDetailActivity extends AppCompatActivity {
    DrawerLayout menuLayoutDrawer;
    NavigationView menuNewsDetail;
    Button btnMenu, btnBack;

    TextView txtDateOfNews, txtNewsDetailContent, txtNewsDetailTitle;
    ImageView imageNewsDetail;
//    private News newsDetail;

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

        String newsTitle = intent.getStringExtra("newsTitle");
        String newsContentDetail = intent.getStringExtra("newsContentDetail");
        int imageNews = intent.getIntExtra("imageNews", 0);
        String dateOfNews = intent.getStringExtra("dateOfNews");

        txtDateOfNews.setText(dateOfNews);
        txtNewsDetailContent.setText(newsContentDetail);
        imageNewsDetail.setImageResource(imageNews);
        txtNewsDetailTitle.setText(newsTitle);

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
                    Intent intent = new Intent(getApplicationContext(), ListServicePetActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }

                if(item.getItemId() == R.id.item_news){
                    Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
}
