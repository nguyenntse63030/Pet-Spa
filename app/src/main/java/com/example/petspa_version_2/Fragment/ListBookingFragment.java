package com.example.petspa_version_2.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petspa_version_2.Activity.BookingDetail;
import com.example.petspa_version_2.Activity.HomeActivity;
import com.example.petspa_version_2.Activity.MapsActivity;
import com.example.petspa_version_2.Adapter.ListBookingFragmentAdapter;
import com.example.petspa_version_2.Listener.Booking_list_listener;
import com.example.petspa_version_2.Model.Booking;
import com.example.petspa_version_2.Model.ServicePet;
import com.example.petspa_version_2.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListBookingFragment extends Fragment implements Booking_list_listener {
    private List<Booking> listBooking = new ArrayList<>();
    private List<Booking> listOldBooking = new ArrayList<>();
    private RecyclerView recyclerView, listOldBookingRecyclerView;
    private ListBookingFragmentAdapter adapter, adapter2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputData();
        inputData2();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_booking, container, false);
        recyclerView = view.findViewById(R.id.listBookingRecyclerView);
        listOldBookingRecyclerView = view.findViewById(R.id.listOldBookingRecyclerView);

        if(listBooking != null){
            if(!listBooking.isEmpty()){
                adapter = new ListBookingFragmentAdapter(listBooking, this);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
                recyclerView.setAdapter(adapter);
            }
        }

        if(listOldBooking != null){
            if(!listOldBooking.isEmpty()){
                adapter2 = new ListBookingFragmentAdapter(listOldBooking, this);
                listOldBookingRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
                listOldBookingRecyclerView.setAdapter(adapter2);
            }
        }
        return view;
    }

    private void inputData(){
        SharedPreferences pref = getActivity().getSharedPreferences("listBooking", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = pref.getString("DATA_BOOKING", "");
        Type type = new TypeToken<ArrayList<Booking>>(){}.getType();

        listBooking = gson.fromJson(json, type);

        if(listBooking != null){
            if(!listBooking.isEmpty()){
                listBooking = sortList((ArrayList<Booking>) listBooking);
            }
        }


    }

    private void inputData2(){
        listOldBooking.add(new Booking("29/06/2019", "30", "06" ,"08",
                "00", "Tạo mẫu tóc" ,"64,000 đ", 2131165392,
                "2019", "Đôi khi thú cưng của bạn cần một sự khác biệt.", "Những nhà tạo mẫu bên chúng tôi sẽ " +
                "cung cấp những mẫu tóc bắt kịp xu hướng cho thú cưng của bạn, " +
                "đảm bảo không bị rối và làm hài thú cưng của bạn trở nên tuyệt vời hơn, " +
                "được miễn phí một lần chụp hình tạo mẫu chuyên nghiệp." ,"true"));


        listOldBooking.add(new Booking("28/06/2019", "29", "06" ,"08",
                "00", "Sơn móng chân" ,"50,000 đ", 2131165395,
                "2019", "Tại sao lại không, giúp cho thú cưng của bạn xinh đẹp hơn mà.", "Bạn muốn thú cưng của bạn trông xinh xắn hơn với một bộ móng chân khác biệt," +
                " chúng tôi cung cấp dịch vụ sơn móng chân và tỉa móng," +
                " biến những đôi chân tinh nghịch của thú cưng của bạn trông xinh xắn hơn." ,"true"));


        listOldBooking.add(new Booking("12/06/2019", "13", "06" ,"08",
                "00", "Sơn móng chân" ,"50,000 đ", 2131165395,
                "2019", "Tại sao lại không, giúp cho thú cưng của bạn xinh đẹp hơn mà.", "Bạn muốn thú cưng của bạn trông xinh xắn hơn với một bộ móng chân khác biệt," +
                " chúng tôi cung cấp dịch vụ sơn móng chân và tỉa móng," +
                " biến những đôi chân tinh nghịch của thú cưng của bạn trông xinh xắn hơn." ,"true"));
    }
    private ArrayList<Booking> sortList(ArrayList<Booking> listBooking){
        ArrayList<Booking> result = new ArrayList<>();
        for (int i = listBooking.size() - 1; i >= 0 ; i--) {
            result.add(listBooking.get(i));
        }
        return result;
    }
    @Override
    public void onClickBookingItem(ServicePet servicePet, String day, String month,
                                   String year, String hour, String minute, String oldBooking, int bookID) {
        Intent intent = new Intent(getContext(), BookingDetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("bookingDetail", (Serializable) servicePet);

        intent.putExtra("day", day);
        intent.putExtra("month", month);
        intent.putExtra("year", year);
        intent.putExtra("hour", hour);
        intent.putExtra("minute", minute);
        intent.putExtra("oldBooking", oldBooking);
        intent.putExtra("bookID", bookID);

        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
