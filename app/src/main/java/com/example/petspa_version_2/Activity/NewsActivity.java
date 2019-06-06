package com.example.petspa_version_2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.petspa_version_2.Fragment.ListNewsFragment;
import com.example.petspa_version_2.R;
/**
 * @author LongDong(06/06/2019)
 * */
public class NewsActivity extends AppCompatActivity {
    ListNewsFragment listNewsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listNewsFragment = new ListNewsFragment();
        loadFragment(listNewsFragment);
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
