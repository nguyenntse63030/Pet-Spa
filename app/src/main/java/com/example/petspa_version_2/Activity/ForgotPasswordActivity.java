package com.example.petspa_version_2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.petspa_version_2.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextView message;
    EditText edtEmailForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        message = findViewById(R.id.txtMessage);
        edtEmailForgot = findViewById(R.id.edtEmailForgot);
    }

    public void clicktoSendEmail(View view) {
        if(!(edtEmailForgot.getText().toString()).equals("") && (edtEmailForgot.getText().toString()) != null ){
            message.setText("An email have been sent to your email!");
        }else{
            message.setText("This email has not been registered in Pet Spa!");
        }
    }

    public void clickToGoLogin(View view) {
        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
