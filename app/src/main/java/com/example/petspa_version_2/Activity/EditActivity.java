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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petspa_version_2.Global.GlobalValue;
import com.example.petspa_version_2.Model.Booking;
import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    DrawerLayout menuLayoutDrawer;
    NavigationView editProfileMenu;
    EditText editFullName,editPhone,editGender,editEmail,editBirthDay;
    Button btnDone,btnBack,btnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        menuLayoutDrawer = findViewById(R.id.menuLayoutDrawer);
        editProfileMenu = findViewById(R.id.editProfileMenu);
        editFullName = findViewById(R.id.editFullname);
        editPhone = findViewById(R.id.editPhone);
        editGender = findViewById(R.id.editGender);
        editEmail = findViewById(R.id.editEmail);
        editBirthDay = findViewById(R.id.editBirthDay);
        btnDone = findViewById(R.id.btnDone);
        btnBack = findViewById(R.id.btnBack);
        btnMenu = findViewById(R.id.btnMenu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences("Email", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("mail", "");
        String fullName = sharedPreferences.getString("username", "");
        String gender = sharedPreferences.getString("gender", "");
        String phone = sharedPreferences.getString("phone", "");
        String birthDay = sharedPreferences.getString("birthDay", "");
        editFullName.setText(fullName);
        editPhone.setText(phone);
        editEmail.setText(email);
        editGender.setText(gender);
        editBirthDay.setText(birthDay);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = editFullName.getText().toString();
                String phone = editPhone.getText().toString();
                String gender = editGender.getText().toString();
                String email = editEmail.getText().toString();
                String birthDay = editBirthDay.getText().toString();
                SharedPreferences sharedPreferences =  getSharedPreferences("Email", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("username", fullName);
                editor.putString("gender", gender);
                editor.putString("mail", email);
                editor.putString("phone", phone);
                editor.putString("birthDay", birthDay);
                editor.apply();
                Intent intent = new Intent(EditActivity.this, ProfileActivity.class);
                Toast.makeText(EditActivity.this, "Edit Profile Successfully", Toast.LENGTH_SHORT).show();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
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

        editProfileMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                /**
                 * event click Home item
                 * */
                if (item.getItemId() == R.id.item_home) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                    Intent intent = new Intent(EditActivity.this, LoginActivity.class);
                    //Remove back button
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                menuLayoutDrawer.closeDrawers();
                return true;
            }
        });
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
}
