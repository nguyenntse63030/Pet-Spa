package com.example.petspa_version_2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.petspa_version_2.Fragment.ListNewsFragment;
import com.example.petspa_version_2.Listener.List_News_Listener;
import com.example.petspa_version_2.Model.News;
import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;

/**
 * @author LongDong(06/06/2019)
 * */
public class NewsActivity extends AppCompatActivity implements List_News_Listener {
    DrawerLayout menuLayoutDrawer;
    NavigationView menuNews;
    Button btnMenu, btnBack;
    ListNewsFragment listNewsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        menuLayoutDrawer = findViewById(R.id.menuLayoutDrawer);
        btnMenu = findViewById(R.id.btnMenu);
        menuNews = findViewById(R.id.menuNews);

        btnBack = findViewById(R.id.btnBack);

        listNewsFragment = new ListNewsFragment();
        loadFragment(listNewsFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
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

        menuNews.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                /**
                 * event click Home item
                 * */
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

                if(item.getItemId() == R.id.item_user_logout){
                    //Clear authentication key in SharedPreferences
                    SharedPreferences.Editor editor = getSharedPreferences("Email", MODE_PRIVATE).edit();
                    editor.putString("Email", " ");
                    editor.apply();

                    //Back to login page
                    Intent intent = new Intent(NewsActivity.this, LoginActivity.class);
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

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment instanceof ListNewsFragment){
            ListNewsFragment listNewsFragment = (ListNewsFragment) fragment;
            listNewsFragment.setmCallBack((List_News_Listener) this);
        }
    }

    @Override
    public void onClickItemNews(News news) {
        Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);

        String newsTitle = news.getNewsTitle();
        String newsContentDetail = news.getNewsContentDetail();
        int imageNews = news.getNewsImage();
        String dateOfNews = news.getDateOfNews();
        
        intent.putExtra("newsTitle", newsTitle);
        intent.putExtra("newsContentDetail", newsContentDetail);
        intent.putExtra("imageNews", imageNews);
        intent.putExtra("dateOfNews", dateOfNews);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.listNewsLayout, fragment);
        fragmentTransaction.commit();
    }
}
