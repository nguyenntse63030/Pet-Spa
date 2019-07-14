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

import com.example.petspa_version_2.Fragment.ListHorizontalServiceFragment;
import com.example.petspa_version_2.Fragment.ListTopNewsFragment;
import com.example.petspa_version_2.Fragment.ServiceCardViewFragment;
import com.example.petspa_version_2.Global.GlobalValue;
import com.example.petspa_version_2.Listener.Service_Card_View_Fragment_Listener;
import com.example.petspa_version_2.Listener.Top_News_Listener;
import com.example.petspa_version_2.Model.Booking;
import com.example.petspa_version_2.Model.News;
import com.example.petspa_version_2.Model.ServicePet;
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
//    ServiceCardViewFragment serviceCardViewFragment;
    ListTopNewsFragment listTopNewsFragment;
    ListHorizontalServiceFragment listHorizontalServiceFragment;
    List<ServicePet> listServicePet = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menuLayoutDrawer = findViewById(R.id.menuLayoutDrawer);
        btnMenu = findViewById(R.id.btnMenu);
        menuHome = findViewById(R.id.menuHome);
//        serviceCardViewFragment = new ServiceCardViewFragment();
//        loadFragment(serviceCardViewFragment);
        listTopNewsFragment = new ListTopNewsFragment();
        loadFragmentForListTopNews(listTopNewsFragment);
        listHorizontalServiceFragment = new ListHorizontalServiceFragment();
        loadFragmentForHorizontalService(listHorizontalServiceFragment);

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

//    private void loadFragment(Fragment fragment){
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.cardServiceLayout, fragment);
//        fragmentTransaction.commit();
//    }
    private void loadFragmentForListTopNews(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fm.beginTransaction();
        fragmentTransaction.replace(R.id.newsLayout, fragment);
        fragmentTransaction.commit();
    }
    private void loadFragmentForHorizontalService(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fm.beginTransaction();
        fragmentTransaction.replace(R.id.khuyenmaiServiceLayout, fragment);
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
    private void inputData(){
        listServicePet.add(new ServicePet("Vệ sinh tổng thể (2h đến 3h)",
                "Cắt tỉa lông, tắm gội, sấy khố, tạo mẫu",
                "Dịch vụ vệ sinh tổng thể của chúng tôi sẽ cung cấp đầy đủ những thứ mà thú cưng và bạn cần, bao gồm tỉa lông, cắt móng chân, " +
                        "tỉa lông cân, vệ sinh tai, 2 lần tắm gội bằng xà bông chuyên dùng cho chó và mèo, sấy khô và giữ ấm, tạo mẫu cho tóc và đuôi.",
                R.drawable.down_shower, "300,000", 4));

        listServicePet.add(new ServicePet("Gỡ rối lông (15' đến 30')",
                "Lông rối thì không có gì phải lo.",
                "Đối với những thú cưng nghịch ngợm, thích chạy nhảy và đôi khi lông của chúng bị rối và khô," +
                        " chúng tôi cung cấp dịch vụ gỡ rối lông và làm mượt lông làm cho thú cưng của bạn dễ chịu " +
                        "và bạn cũng bớt lo lắng khi đi chơi cùng thú cưng của mình.",
                R.drawable.service_go_roi_long, "50,000", (float)(4.5)));

        listServicePet.add(new ServicePet("Loại bỏ vết ố ở mắt (15' đến 30')",
                "Vết ố ở mắt có làm thú cưng của bạn khó chịu",
                "Thú cưng của bạn đôi khi bị bẩn ở khóe mắt và bạn không thể làm sạch được, đến với dịch vụ của chúng tôi, " +
                        "chúng tôi sử dụng dung dịch vệ sinh chuyên dụng để làm sạch khóe mắt cho thú cưng của bạn, đảm bảo vô hại với thú cưng.",
                R.drawable.service_ve_sinh_mat, "50,000", (float)(3.3)));

        listServicePet.add(new ServicePet("Làm sạch hơi thở (15' đến 20')",
                "Một hơi thở sạch sẽ chẳng phải tốt hơn sao",
                "Thú cưng của bạn có một hơi thở làm bạn thấy khó chịu do thức ăn, chúng tôi sẽ giải quyết vấn đề này giúp bạn không qua 30'.",
                R.drawable.ve_sinh_mieng, "50,000", (float)(5)));

        listServicePet.add(new ServicePet("Tắm và sấy khô (1h30 đến 2h)",
                "Không cần quá mất thời gian về việc vệ sinh, đã có Petspa.",
                "Dịch vụ tắm và sấy khô bên chúng tôi bao gồm loại bỏ các bụi bẩn và lông bị rối, tắm rửa qua 2 lần dầu gọi," +
                        " sấy khô và giữ ấm cho thú cưng của bạn, đặc biệt còn được sử dụng nước hoa mùi hương sang trọng dễ chịu.",
                R.drawable.tamsay, "200,000", (float)(4.1)));
    }

    private void inputData2(){
        listServicePet.add(new ServicePet("Tẩy lông và hồi phục màu lông (15' đến 30')",
                "Bạn muốn phục hồi lại màu lông của thú cưng",
                "Thú cưng của bạn đôi khi ham chơi và lỡ làm bẩn những chỗ như lông cổ," +
                        " lông đuôi mà cỡ nào bạn cũng không thể loại bỏ được những bụi bẩn ấy," +
                        " hãy để chúng tôi làm điều này giúp bạn, dịch vụ hồi phục và " +
                        "tẩy trắng lông của chúng tôi sẽ giải quyết được khó khăn của bạn.",
                R.drawable.spa_long, "100,000", (float)(3.9)));

        listServicePet.add(new ServicePet("Tạo mẫu tóc (30' đến 1h)",
                "Đôi khi thú cưng của bạn cần một sự khác biệt.",
                "Những nhà tạo mẫu bên chúng tôi sẽ cung cấp những mẫu tóc bắt kịp xu hướng cho thú cưng của bạn," +
                        " đảm bảo không bị rối và làm hài thú cưng của bạn trở nên tuyệt vời hơn," +
                        " được miễn phí một lần chụp hình tạo mẫu chuyên nghiệp.",
                R.drawable.spa_hair, "80,000", (float)(4.9)));

        listServicePet.add(new ServicePet("Sơn móng chân (20' đến 30')",
                "Tại sao lại không, giúp cho thú cưng của bạn xinh đẹp hơn mà.",
                "Bạn muốn thú cưng của bạn trông xinh xắn hơn với một bộ móng chân khác biệt, chúng tôi cung cấp dịch vụ sơn móng chân " +
                        "và tỉa móng, biến những đôi chân tinh nghịch của thú cưng của bạn trông xinh xắn hơn.",
                R.drawable.spa_nail, "50,000", (float)(5)));

        listServicePet.add(new ServicePet("Massage thư giãn và đắp mặt nạ (15' đến 30')",
                "Thú cưng của bạn cũng cần massage mà.",
                "Thú cưng của bạn sẽ được sử dụng máy massage chuyên dùng cho thú cưng," +
                        " giúp thú cưng của bạn có những giây phút thoải mái nhất.",
                R.drawable.spa_massage, "50,000", (float)(3.7)));

        listServicePet.add(new ServicePet("Điều trị giảm béo (20' đến 30')",
                "Béo ư, hãy đến với Petspa",
                "Lại là vấn đề về cân nặng, thú cưng của bạn quá ham ăn, ăn quá nhiều " +
                        "và phát phì vì điều này, trở nên lười biếng, ít vận động." +
                        " Hãy đến với dịch vụ của chúng tôi, chúng tôi cung cấp các phương pháp điều trị giảm béo cho thú cưng của bạn.",
                R.drawable.spa_excercise, "50,000", (float)(4.6)));
    }

    private void inputData3(){
        listServicePet.add(new ServicePet("Giữ thú cưng theo giờ",
                "Bạn bận ư, hãy để chúng tôi chăm sóc thú cưng của bạn.",
                "Bạn phải đi làm xa, đi chơi với gia đình, bạn bè và không thể mang theo thú cưng của bạn." +
                        " Đừng lo, chúng tôi cung cấp dịch vụ giữ và chăm sóc thú cưng 24/7," +
                        " với dịch vụ đồ ăn và nước uống kèm theo 3 bữa/ngày đảm bảo thú cưng của bạn luôn cảm thấy vui vẻ." +
                        " Vào mọi thời điểm, bạn cần là chúng tôi sẽ có mặt. (Vui lòng liên hệ theo hotline 0903 xxx xxx để đặt lịch và được báo giá)",
                R.drawable.pet_take_care, "Liên hệ để được báo giá", (float)(4.0)));
    }

    private void inputData4(){
        listServicePet.add(new ServicePet("Vệ sinh tổng thể: (2h đến 3h)",
                "Cắt tỉa lông, tắm gội, sấy khố, tạo mẫu",
                "Dịch vụ vệ sinh tổng thể của chúng tôi sẽ cung cấp đầy đủ những thứ mà thú cưng và bạn cần, bao gồm tỉa lông, cắt móng chân, " +
                        "tỉa lông cân, vệ sinh tai, 2 lần tắm gội bằng xà bông chuyên dùng cho chó và mèo, sấy khô và giữ ấm, tạo mẫu cho tóc và đuôi.",
                R.drawable.down_shower, "240,000", (float)(3.0)));

        listServicePet.add(new ServicePet("Tạo mẫu tóc (30' đến 1h)",
                "Đôi khi thú cưng của bạn cần một sự khác biệt.",
                "Những nhà tạo mẫu bên chúng tôi sẽ cung cấp những mẫu tóc bắt kịp xu hướng cho thú cưng của bạn," +
                        " đảm bảo không bị rối và làm hài thú cưng của bạn trở nên tuyệt vời hơn," +
                        " được miễn phí một lần chụp hình tạo mẫu chuyên nghiệp.",
                R.drawable.spa_hair, "64,000", (float)(3.9)));

        listServicePet.add(new ServicePet("Loại bỏ vết ố ở mắt: (15' đến 30')",
                "Vết ố ở mắt có làm thú cưng của bạn khó chịu",
                "Thú cưng của bạn đôi khi bị bẩn ở khóe mắt và bạn không thể làm sạch được, đến với dịch vụ của chúng tôi, " +
                        "chúng tôi sử dụng dung dịch vệ sinh chuyên dụng để làm sạch khóe mắt cho thú cưng của bạn, đảm bảo vô hại với thú cưng.",
                R.drawable.service_ve_sinh_mat, "40,000", (float)(4.6)));

        listServicePet.add(new ServicePet("Điều trị giảm béo (20' đến 30')",
                "Béo ư, hãy đến với Petspa",
                "Lại là vấn đề về cân nặng, thú cưng của bạn quá ham ăn, ăn quá nhiều " +
                        "và phát phì vì điều này, trở nên lười biếng, ít vận động." +
                        " Hãy đến với dịch vụ của chúng tôi, chúng tôi cung cấp các phương pháp điều trị giảm béo cho thú cưng của bạn.",
                R.drawable.spa_excercise, "40,000", (float)(4.5)));

        listServicePet.add(new ServicePet("Tắm và sấy khô: (1h30 đến 2h)",
                "Không cần quá mất thời gian về việc vệ sinh, đã có Petspa.",
                "Dịch vụ tắm và sấy khô bên chúng tôi bao gồm loại bỏ các bụi bẩn và lông bị rối, tắm rửa qua 2 lần dầu gọi," +
                        " sấy khô và giữ ấm cho thú cưng của bạn, đặc biệt còn được sử dụng nước hoa mùi hương sang trọng dễ chịu.",
                R.drawable.tamsay, "160,000", (float)(4.7)));
    }
}
