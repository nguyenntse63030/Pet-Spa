package com.example.petspa_version_2.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.petspa_version_2.Fragment.ListTopNewsFragment;
import com.example.petspa_version_2.Fragment.ServiceCardViewFragment;
import com.example.petspa_version_2.Global.GlobalValue;
import com.example.petspa_version_2.Listener.Service_Card_View_Fragment_Listener;
import com.example.petspa_version_2.Listener.Top_News_Listener;
import com.example.petspa_version_2.Model.Booking;
import com.example.petspa_version_2.Model.News;
import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LongDong(04/06/2019)
 * */
public class HomeActivity extends AppCompatActivity implements Service_Card_View_Fragment_Listener, Top_News_Listener {
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

        //getActionBar().setDisplayHomeAsUpEnabled(false);
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
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
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
                    editor.remove("username");
                    editor.remove("gender");
                    editor.remove("mail");
                    editor.remove("phone");
                    editor.remove("birthDay");
                    editor.remove("password");
                    editor.apply();

                    //Back to login page
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
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
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment instanceof ServiceCardViewFragment){
            ServiceCardViewFragment serviceCardViewFragment = (ServiceCardViewFragment) fragment;
            serviceCardViewFragment.setmCallback((Service_Card_View_Fragment_Listener) this);
        }

        if(fragment instanceof ListTopNewsFragment){
            ListTopNewsFragment listTopNewsFragment = (ListTopNewsFragment) fragment;
            listTopNewsFragment.setmCallBack((Top_News_Listener) this);
        }
    }

    @Override
    public void openTopNewsItem(News news) {
        Intent intent = new Intent(HomeActivity.this, NewsDetailActivity.class);
        intent.putExtra("news", (Serializable) news);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void openServicePetList(String cardServiceName) {

        Intent intent = new Intent(this, ListServicePetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("serviceType", cardServiceName);
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

    private void dialogMessEmptyListBooking(){
        AlertDialog.Builder mess = new AlertDialog.Builder(this);
        mess.setTitle(GlobalValue.MESS_TITLE);

        mess.setMessage(GlobalValue.MES_CONTENT)
                .setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog showMess = mess.create();
        showMess.show();
    }

    public void clickToOpenGGMap(View view) {
        Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}
