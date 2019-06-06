package com.example.petspa_version_2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.petspa_version_2.Fragment.ListNewsFragment;
import com.example.petspa_version_2.Fragment.ServiceCardViewFragment;
import com.example.petspa_version_2.Goalball.ValueGoalball;
import com.example.petspa_version_2.Listener.Service_Card_View_Fragment_Listener;
import com.example.petspa_version_2.R;
/**
 * @author LongDong(04/06/2019)
 * */
public class HomeActivity extends AppCompatActivity implements Service_Card_View_Fragment_Listener {
    DrawerLayout menuLayout;
    Button btnMenu;
    ServiceCardViewFragment serviceCardViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menuLayout = findViewById(R.id.menuLayout);
        btnMenu = findViewById(R.id.btnMenu);

        serviceCardViewFragment = new ServiceCardViewFragment();
        loadFragment(serviceCardViewFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLayout.openDrawer(Gravity.RIGHT);
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

        if(cardServiceName.equals(ValueGoalball.CARD_VIEW_3)){
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, ListServicePetActivity.class);
            startActivity(intent);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fm.beginTransaction();
        fragmentTransaction.replace(R.id.cardServiceLayout, fragment);
        fragmentTransaction.commit();
    }
}
