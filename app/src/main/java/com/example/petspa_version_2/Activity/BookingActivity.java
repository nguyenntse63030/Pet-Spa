package com.example.petspa_version_2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petspa_version_2.Model.Booking;
import com.example.petspa_version_2.Model.ServicePet;
import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.DataFormatException;

public class BookingActivity extends AppCompatActivity {
    private ServicePet servicePet;
    DrawerLayout drawerLayout = null;
    NavigationView navigationView = null;

    private  Spinner spinnerTopLeft, spinnerTopRight, spinnerBottomLeft, spinnerBottomRight;
    private TextView txtTitle, txtDescription, txtServicePetContent, txtPrice;
    ImageView imageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        drawerLayout = findViewById(R.id.menuLayoutDrawer);
        navigationView = findViewById(R.id.bookingMenu);

        txtDescription = findViewById(R.id.txtDescription);
        txtTitle = findViewById(R.id.txtTitle);
        txtServicePetContent = findViewById(R.id.txtServicePetContent);
        txtPrice = findViewById(R.id.txtPrice);
        imageService = findViewById(R.id.imageService);

        Intent intent = BookingActivity.this.getIntent();
        servicePet = (ServicePet) intent.getSerializableExtra("service");

        txtPrice.setText(servicePet.getServicePrice() + " VND");
        txtTitle.setText(servicePet.getServiceTitle());
        txtDescription.setText(servicePet.getServiceDescription());
        txtServicePetContent.setText(servicePet.getServiceContent());
        imageService.setImageResource(servicePet.getServiceImage());

        spinnerTopLeft = findViewById(R.id.spinnerTopLeft);

        spinnerTopRight = findViewById(R.id.spinnerTopRight);

        spinnerBottomLeft = findViewById(R.id.spinnerBottomLeft);

        spinnerBottomRight = findViewById(R.id.spinnerBottomRight);

        spinnerBottomRight.setEnabled(false);

        String[] stlItems = new String[]{"08", "09", "10", "11", "13", "14", "15", "16", "17"};
        String[] strItems = new String[]{"00", "30"};

        String[] sbrItems = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "11", "10", "12"};

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date); // Now use today date.

        ArrayList<String> sblItems = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            c.add(Calendar.DATE, 1);
            sblItems.add("" + c.getTime().getDate());
        }

            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.spinner_item, stlItems);
        spinnerTopLeft.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.spinner_item, strItems);
        spinnerTopRight.setAdapter(adapter2);


        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, R.layout.spinner_item, sblItems);
        spinnerBottomLeft.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, R.layout.spinner_item, sbrItems);
        spinnerBottomRight.setAdapter(adapter4);
        setCurrentDate(spinnerBottomLeft, spinnerBottomRight);
    }

    private void setCurrentDate(Spinner spinnerDay, Spinner spinnerMonth) {
        Date currentDate = new Date();
        int day = currentDate.getDate();
        int month = currentDate.getMonth();
        //spinnerDay.setSelection(day - 1);
        spinnerMonth.setSelection(month);
    }

    public void clickGoToListService(View view) {
        finish();
    }

    public void openMenu(View view) {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_home) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.item_news) {
                    Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }

                if (item.getItemId() == R.id.item_user_profile) {
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

                if (item.getItemId() == R.id.item_user_logout) {
                    //Clear authentication key in SharedPreferences
                    SharedPreferences.Editor editor = getSharedPreferences("Email", MODE_PRIVATE).edit();
                    editor.putString("Email", " ");
                    editor.apply();

                    //Back to login page
                    Intent intent = new Intent(BookingActivity.this, LoginActivity.class);
                    //Remove back button
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void clickToBook(View view) {
        Intent intent = BookingActivity.this.getIntent();
        servicePet = (ServicePet) intent.getSerializableExtra("service");

        String hour = spinnerTopLeft.getSelectedItem().toString();
        String minute = spinnerTopRight.getSelectedItem().toString();

        String day = spinnerBottomLeft.getSelectedItem().toString();
        String month = spinnerBottomRight.getSelectedItem().toString();

        String dateBook = "";
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            dateBook = df.format(new Date()).toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        String service = txtTitle.getText().toString();
        String price = txtPrice.getText().toString();
        int imageServiceBooking = servicePet.getServiceImage();

        SharedPreferences pref = getSharedPreferences("listBooking", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        Gson gson = new Gson();
        String json = pref.getString("DATA_BOOKING", "");
        Type type = new TypeToken<ArrayList<Booking>>(){}.getType();

        ArrayList<Booking> listBooking =  gson.fromJson(json, type);

        if(listBooking == null){
            listBooking = new ArrayList<>();
            listBooking.add(new Booking(dateBook, day, month, hour, minute, service, price, imageServiceBooking));

            Gson gson2 = new Gson();
            String json2 = gson2.toJson(listBooking);
            editor.putString("DATA_BOOKING", json2);
            editor.commit();

        }else {
            listBooking.add(new Booking(dateBook, day, month, hour, minute, service, price, imageServiceBooking));

            Gson gson2 = new Gson();
            String json2 = gson2.toJson(listBooking);
            editor.putString("DATA_BOOKING", json2);
            editor.commit();
        }

        Intent intent1 = new Intent(this, ListServicePetActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);
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
