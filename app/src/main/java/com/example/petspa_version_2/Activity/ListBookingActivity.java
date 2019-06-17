package com.example.petspa_version_2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.petspa_version_2.Fragment.ListBookingFragment;
import com.example.petspa_version_2.R;

public class ListBookingActivity extends AppCompatActivity {
    private ListBookingFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_booking);

        fragment = new ListBookingFragment();
        loadFragment(fragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.listBookingLayout, fragment);
        fragmentTransaction.commit();
    }
}
