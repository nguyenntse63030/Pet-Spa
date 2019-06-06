package com.example.petspa_version_2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.example.petspa_version_2.Fragment.ListNewsFragment;
import com.example.petspa_version_2.R;
/**
 * @author LongDong(06/06/2019)
 * */
public class NewsActivity extends AppCompatActivity {
    DrawerLayout menuLayout;
    Button btnMenu;
    ListNewsFragment listNewsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        menuLayout = findViewById(R.id.menuLayout);
        btnMenu = findViewById(R.id.btnMenu);

        listNewsFragment = new ListNewsFragment();
        loadFragment(listNewsFragment);
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
