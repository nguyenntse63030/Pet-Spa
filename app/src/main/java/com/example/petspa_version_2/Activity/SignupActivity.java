package com.example.petspa_version_2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.petspa_version_2.R;

public class SignupActivity extends AppCompatActivity {

    TextView message;
    EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        message = findViewById(R.id.txtMessage);
        edtEmail = findViewById(R.id.edtEmail);
    }

    public void clicktoSignUp(View view) {
        if(!(edtEmail.getText().toString()).equals("") && (edtEmail.getText().toString()) != null ){
            message.setText("Register successfully!");
        }else{
            message.setText("Please enter your email address!");
        }
    }

    public void clickToGoLogin(View view) {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
