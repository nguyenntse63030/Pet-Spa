package com.example.petspa_version_2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.petspa_version_2.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PetInformationActivity extends AppCompatActivity {

    private EditText txtPetName;
    private EditText txtPetBirthDay;
    private EditText txtType;
    private EditText txtBreed;
    private EditText txtColor;
    private EditText txtSex;
    private EditText txtWeight;
    private Button btnUpdate;
    private CircleImageView imageView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_information);

        txtPetName = findViewById(R.id.txtPetName);
        txtPetBirthDay = findViewById(R.id.txtPetBirthDay);
        txtType = findViewById(R.id.txtType);
        txtBreed = findViewById(R.id.txtBreed);
        txtColor = findViewById(R.id.txtColor);
        txtSex = findViewById(R.id.txtSex);
        txtWeight = findViewById(R.id.txtWeight);
        btnUpdate = findViewById(R.id.btnUpdatePet);
        imageView = findViewById(R.id.profile_image);
        context = this;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });

        boolean havePet = getSharedPreferences("pet", Context.MODE_PRIVATE).getBoolean("pet", false );
        if(havePet){
            SharedPreferences sharedPreferences = getSharedPreferences("pet", Context.MODE_PRIVATE);
            txtPetName.setText(sharedPreferences.getString("petname", "asdasdasd"));
            txtPetBirthDay.setText(sharedPreferences.getString("petbirthday", ""));
            txtType.setText(sharedPreferences.getString("pettype", ""));
            txtBreed.setText(sharedPreferences.getString("petbreed", ""));
            txtColor.setText(sharedPreferences.getString("petcolor", ""));
            txtSex.setText(sharedPreferences.getString("petsex", ""));
            txtWeight.setText(sharedPreferences.getString("petweight", ""));
        }else{
                btnUpdate.setText("Thêm");
                final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                Intent intent = new Intent(PetInformationActivity.this, HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                break;
                        }
                    }
                };
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                builder.setMessage("Bạn chưa có thú cưng nào trong danh sách. Thêm thông tin thú cưng?"  +"").setPositiveButton("Ok", dialogClickListener)
                        .setNegativeButton("Để sau", dialogClickListener).show();
        }
    }

    public void clickToEditPetInformation(View view) {

                SharedPreferences.Editor editor = getSharedPreferences("pet", MODE_PRIVATE).edit();
                editor.putString("petname", txtPetName.getText().toString());
                editor.putString("petbirthday", txtPetBirthDay.getText().toString());
                editor.putString("pettype", txtType.getText().toString());
                editor.putString("petbreed", txtBreed.getText().toString());
                editor.putString("petcolor", txtColor.getText().toString());
                editor.putString("petsex", txtSex.getText().toString());
                editor.putString("petweight", txtWeight.getText().toString());
                editor.putBoolean("pet", true);
                editor.apply();
                final DialogInterface.OnClickListener dg = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setMessage("Cập nhật thông tin thú cưng thành công."  +"").setPositiveButton("Ok", dg).show();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(PetInformationActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(PetInformationActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(PetInformationActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
    }
}
