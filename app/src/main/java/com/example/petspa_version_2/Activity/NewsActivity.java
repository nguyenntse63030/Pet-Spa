package com.example.petspa_version_2.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.petspa_version_2.Fragment.ListNewsFragment;
import com.example.petspa_version_2.Goalball.ValueGoalball;
import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;

/**
 * @author LongDong(06/06/2019)
 * */
public class NewsActivity extends AppCompatActivity {
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
                    Intent intent = new Intent(getApplicationContext(), ListServicePetActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.listNewsLayout, fragment);
        fragmentTransaction.commit();
    }
}
