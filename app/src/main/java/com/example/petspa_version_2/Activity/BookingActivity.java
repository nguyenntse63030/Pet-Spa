package com.example.petspa_version_2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petspa_version_2.Global.GlobalValue;
import com.example.petspa_version_2.Model.Booking;
import com.example.petspa_version_2.Model.ServicePet;
import com.example.petspa_version_2.R;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookingActivity extends AppCompatActivity {
    private ServicePet servicePet;
    DrawerLayout drawerLayout = null;
    NavigationView navigationView = null;

    private Spinner spinnerTopLeft, spinnerTopRight, spinnerBottomLeft, spinnerBottomRight;
    private TextView txtTitle, txtDescription, txtServicePetContent, txtPrice, txtOldPrice, txtDiscountInBooking;
    private String year = "";
    private HorizontalScrollView timeButtonView;
    private HorizontalScrollView dateButtonView;
    private ArrayList<Button> listTimeButton = new ArrayList<>();
    private ArrayList<Button> listDateButton = new ArrayList<>();
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
        txtOldPrice = findViewById(R.id.txtOldPrice);
        txtPrice = findViewById(R.id.txtPrice);
        txtDiscountInBooking = findViewById(R.id.txtDiscountInBooking);
        imageService = findViewById(R.id.imageService);

        Intent intent = BookingActivity.this.getIntent();
        servicePet = (ServicePet) intent.getSerializableExtra("service");
        if (servicePet.getServiceOldPrice() != null) {
            txtOldPrice.setText(servicePet.getServiceOldPrice() + " đ");
            txtOldPrice.setPaintFlags(txtOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        } else {
            txtOldPrice.setText("");
        }

        txtPrice.setText(servicePet.getServicePrice() + " đ");
        txtTitle.setText(servicePet.getServiceTitle());
        txtDescription.setText(servicePet.getServiceDescription());
        txtServicePetContent.setText(servicePet.getServiceContent());
        imageService.setImageResource(servicePet.getServiceImage());
        txtDiscountInBooking.setText(servicePet.getDiscountPercent());

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
        setCurrentDate(spinnerBottomRight);

        ////////
        loadListTimeButton();
        loadListDateButton();
    }

    private void loadListTimeButton() {
        timeButtonView = findViewById(R.id.timeButtonView);
        String[] times = new String[]{"8h30", "9h30", "10h30", "11h30", "13h30", "14h30", "15h30", "16h30", "17h30", "18h30", "19h30"};

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 0, 0, 0);
        for (String time : times) {
            Button timeButton = new Button(this);
            timeButton.setText(time + "");
            timeButton.setTextSize(18);
            timeButton.setLayoutParams(params);
            timeButton.setBackgroundColor(Color.parseColor("#02c2f9"));
            timeButton.setTextColor(Color.parseColor("#ffffff"));
            timeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedTimeButton = (Button) v;
                    String buttonName = clickedTimeButton.getText().toString();
                    clickedTimeButton.setBackgroundColor(Color.parseColor("#049af7"));
                    resetColorTimeButton(buttonName);
                    // Chỗ này Long code dùm tao lấy thời gian trong button để lưu vô ShareReferences.
                    // Tao không biết code khúc ShareReferences ở dưới của mày.

                }
            });
            listTimeButton.add(timeButton);
            linearLayout.addView(timeButton);
        }
        timeButtonView.addView(linearLayout);
    }

    private void loadListDateButton() {
        dateButtonView = findViewById(R.id.dateButtonView);
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 0, 0, 0);

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date); // Now use today date.

        for (int i = 1; i <= 7; i++) {
            c.add(Calendar.DATE, 1);
            String dateMonth = c.getTime().getDate() + "/" + c.getTime().getMonth();
            Button dateButton = new Button(this);
            dateButton.setText(dateMonth);
            dateButton.setTextSize(18);
            dateButton.setLayoutParams(params);
            dateButton.setBackgroundColor(Color.parseColor("#02c2f9"));
            dateButton.setTextColor(Color.parseColor("#ffffff"));
            dateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedDateButton = (Button) v;
                    String buttonName = clickedDateButton.getText().toString();
                    clickedDateButton.setBackgroundColor(Color.parseColor("#049af7"));
                    resetColorDateButton(buttonName);
                    // Chỗ này Long code dùm tao lấy thời gian trong button để lưu vô ShareReferences.
                    // Tao không biết code khúc ShareReferences ở dưới của mày.

                }
            });
            listDateButton.add(dateButton);
            linearLayout.addView(dateButton);
        }
        dateButtonView.addView(linearLayout);
    }

    private void resetColorTimeButton(String buttonName) {
        for (Button timeButton : listTimeButton) {
            if (!timeButton.getText().toString().equals(buttonName)) {
                timeButton.setBackgroundColor(Color.parseColor("#02c2f9"));
            }
        }
    }

    private void resetColorDateButton(String buttonName) {
        for (Button dateButton : listDateButton) {
            if (!dateButton.getText().toString().equals(buttonName)) {
                dateButton.setBackgroundColor(Color.parseColor("#02c2f9"));
            }
        }
    }

    private void setCurrentDate(Spinner spinnerMonth) {
        Date currentDate = new Date();
        int month = currentDate.getMonth();
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

        spinnerBottomLeft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Date dateCheckSpiner = new Date();
                Calendar calendarCheckSpiner = Calendar.getInstance();
                calendarCheckSpiner.setTime(dateCheckSpiner);
                calendarCheckSpiner.add(Calendar.DATE, 1);

                int dateSelectCheck = Integer.parseInt(spinnerBottomLeft.getSelectedItem().toString());
                String checkSpinerNow = calendarCheckSpiner.getTime().getDate() + "";
                int dateConvertCheck = Integer.parseInt(checkSpinerNow);

                if (dateSelectCheck < dateConvertCheck) {
                    int monthCurrent = calendarCheckSpiner.getTime().getMonth();

                    calendarCheckSpiner.add(Calendar.MONTH, 1);

                    int month = calendarCheckSpiner.getTime().getMonth();

                    spinnerBottomRight.setSelection(month);

                    if (monthCurrent == 11) {
                        year = "" + (Calendar.getInstance().get(Calendar.YEAR) + 1);
                        ;
                    }


                } else {
                    int month = calendarCheckSpiner.getTime().getMonth();
                    spinnerBottomRight.setSelection(month);

                    year = "" + Calendar.getInstance().get(Calendar.YEAR);
                    ;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

                if (item.getItemId() == R.id.item_booking_list) {
                    List<Booking> listBooking = new ArrayList<>();
                    SharedPreferences pref = getSharedPreferences("listBooking", Context.MODE_PRIVATE);

                    Gson gson = new Gson();
                    String json = pref.getString("DATA_BOOKING", "");
                    Type type = new TypeToken<ArrayList<Booking>>() {
                    }.getType();

                    listBooking = gson.fromJson(json, type);

                    if (listBooking == null) {
                        dialogMessEmptyListBooking();
                    } else if (listBooking.isEmpty()) {
                        dialogMessEmptyListBooking();
                    } else {
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

    public void saveOrder() {
        String hour = spinnerTopLeft.getSelectedItem().toString();
        String minute = spinnerTopRight.getSelectedItem().toString();

        String day = spinnerBottomLeft.getSelectedItem().toString();
        String month = spinnerBottomRight.getSelectedItem().toString();

        String dateBook = "";
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            dateBook = df.format(new Date()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String service = txtTitle.getText().toString();
        String price = txtPrice.getText().toString();
        int imageServiceBooking = servicePet.getServiceImage();
        String serviceDescription = txtDescription.getText().toString();
        String serviceContent = txtServicePetContent.getText().toString();

        SharedPreferences pref = getSharedPreferences("listBooking", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        Gson gson = new Gson();
        String json = pref.getString("DATA_BOOKING", "");
        Type type = new TypeToken<ArrayList<Booking>>() {
        }.getType();

        ArrayList<Booking> listBooking = gson.fromJson(json, type);

        if (listBooking == null) {
            listBooking = new ArrayList<>();
            listBooking.add(new Booking(dateBook, day, month, hour, minute, service, price, imageServiceBooking, year, serviceDescription, serviceContent));

            Gson gson2 = new Gson();
            String json2 = gson2.toJson(listBooking);
            editor.putString("DATA_BOOKING", json2);
            editor.commit();

        } else {
            listBooking.add(new Booking(dateBook, day, month, hour, minute, service, price, imageServiceBooking, year, serviceDescription, serviceContent));

            Gson gson2 = new Gson();
            String json2 = gson2.toJson(listBooking);
            editor.putString("DATA_BOOKING", json2);
            editor.commit();
        }
    }

    public void clickToBook(final View view) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        saveOrder();
                        Intent gMaps = new Intent(view.getContext(), MapsActivity.class);
                        startActivity(gMaps);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        saveOrder();
                        Intent intent1 = new Intent(view.getContext(), ListBookingActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        break;
                }
            }
        };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(view.getContext());
        builder.setMessage("Lịch hẹn của bạn đã được đặt thành công. Bạn có muốn chỉ đường đến shop?" + "").setPositiveButton("Ok", dialogClickListener)
                .setNegativeButton("Đã biết đường", dialogClickListener).show();


    }

    private void dialogMessEmptyListBooking() {
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
