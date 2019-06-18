package com.example.petspa_version_2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.petspa_version_2.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        txtMessage = findViewById(R.id.txtMessage);
        //check login - Preferences are email, token or something to authorize...
        String email = getSharedPreferences("Email", Context.MODE_PRIVATE).getString("Email", "");
        if(!email.isEmpty() && !email.equals(" ") && email != null){
            //user logged - goto home
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            //remove back button
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        //else go to login page
    }

    public void clickToLogin(View view) {
        //progress to get authentication token, email, or something to authorize...
        String authentication;
        //do login here
        String email = edtEmail.getText().toString();
        if(email != null && !email.equals("")){
            authentication = "something from authentication progress";
            //Get authentication token from login to store at SharedPreferences (they don't want to login again until logout)
            SharedPreferences.Editor editor = getSharedPreferences("Email", MODE_PRIVATE).edit();
            editor.putString("Email", authentication);
            //add profile user
            editor.putString("username", "Nguyen Van A");
            editor.putString("gender", "nam");
            editor.putString("mail", "ledocon@gmail.com");
            editor.putString("phone", "0965923447");
            editor.putString("birthDay", "11/7/1998");
            editor.putString("password", "123");
            editor.apply();
            //Login success
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            //remove back button
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else{
            //login fail
            txtMessage.setText("Your password or email invalid!");
        }
        //end login
    }

    public void clickToResetPassword(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    public void clickToSingup(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}
