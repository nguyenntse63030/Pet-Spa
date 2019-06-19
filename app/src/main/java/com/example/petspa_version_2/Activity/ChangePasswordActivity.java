package com.example.petspa_version_2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;

public class ChangePasswordActivity extends AppCompatActivity {
    DrawerLayout menuLayoutDrawer;
    NavigationView changePasswordMenu;
    EditText editPassword,editNewPassword,editRePassword;
    Button btnDone,btnBack,btnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        menuLayoutDrawer = findViewById(R.id.menuLayoutDrawer);
        changePasswordMenu = findViewById(R.id.changePasswordMenu);
        editPassword = findViewById(R.id.editPassword);
        editNewPassword = findViewById(R.id.editNewPassword);
        editRePassword = findViewById(R.id.editRePassword);
        btnDone = findViewById(R.id.btnDone);
        btnBack = findViewById(R.id.btnBack);
        btnMenu = findViewById(R.id.btnMenu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        editPassword.setText("");
        editNewPassword.setText("");
        editRePassword.setText("");
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences =  getSharedPreferences("Email", Context.MODE_PRIVATE);
                String password=sharedPreferences.getString("password","").trim();
                String txtPassword = editPassword.getText().toString().trim();
                if(password.equals(txtPassword)){
                    String newPassword = editNewPassword.getText().toString().trim();
                    String rePassword = editRePassword.getText().toString().trim();
                    if(newPassword.equals(rePassword)){
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("password",newPassword);
                        editor.apply();
                        Intent intent = new Intent(ChangePasswordActivity.this, ProfileActivity.class);
                        Toast.makeText(ChangePasswordActivity.this, "Change Password Successfully", Toast.LENGTH_SHORT).show();
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ChangePasswordActivity.this, "Re-password not match!!!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(ChangePasswordActivity.this, "Old password not match!!!", Toast.LENGTH_SHORT).show();
                }

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

        changePasswordMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
                }if(item.getItemId() == R.id.item_user_logout){
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
                    Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                    //Remove back button
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                menuLayoutDrawer.closeDrawers();
                return true;
            }
        });
    }
}
