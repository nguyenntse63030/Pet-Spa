package com.example.petspa_version_2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.petspa_version_2.Fragment.ListTopNewsFragment;
import com.example.petspa_version_2.Fragment.ServiceCardViewFragment;
import com.example.petspa_version_2.Listener.Service_Card_View_Fragment_Listener;
import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;

/**
 * @author LongDong(04/06/2019)
 * */
public class HomeActivity extends AppCompatActivity implements Service_Card_View_Fragment_Listener {
    DrawerLayout menuLayoutDrawer;
    NavigationView menuHome;
    Button btnMenu;
    ServiceCardViewFragment serviceCardViewFragment;
    ListTopNewsFragment listTopNewsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menuLayoutDrawer = findViewById(R.id.menuLayoutDrawer);
        btnMenu = findViewById(R.id.btnMenu);
        menuHome = findViewById(R.id.menuHome);
        serviceCardViewFragment = new ServiceCardViewFragment();
        loadFragment(serviceCardViewFragment);
        listTopNewsFragment = new ListTopNewsFragment();
        loadFragmentForListTopNews(listTopNewsFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLayoutDrawer.openDrawer(Gravity.RIGHT);
            }
        });

        menuHome.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.item_news){
                    Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }

                if(item.getItemId() == R.id.item_user_profile){
                    Intent intent = new Intent(getApplicationContext(), ListServicePetActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                menuLayoutDrawer.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment instanceof ServiceCardViewFragment){
            ServiceCardViewFragment serviceCardViewFragment = (ServiceCardViewFragment) fragment;
            serviceCardViewFragment.setmCallback((Service_Card_View_Fragment_Listener) this);
        }
    }

    @Override
    public void openServicePetList(String cardServiceName) {

        Intent intent = new Intent(this, ListServicePetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.cardServiceLayout, fragment);
        fragmentTransaction.commit();
    }
    private void loadFragmentForListTopNews(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fm.beginTransaction();
        fragmentTransaction.replace(R.id.newsLayout, fragment);
        fragmentTransaction.commit();
    }
}
