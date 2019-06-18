package com.example.petspa_version_2.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.example.petspa_version_2.Fragment.ListBookingFragment;
import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;

public class ListBookingActivity extends AppCompatActivity {
    private ListBookingFragment fragment;
    private DrawerLayout menuLayoutDrawer = null;
    private NavigationView menuBookingList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_booking);

        menuLayoutDrawer = findViewById(R.id.menuLayoutDrawer);
        menuBookingList = findViewById(R.id.menuBookingList);

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

    public void clickToBack(View view) {
        finish();
    }

    public void clickToOpenMenu(View view) {
        menuLayoutDrawer.openDrawer(Gravity.RIGHT);
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
