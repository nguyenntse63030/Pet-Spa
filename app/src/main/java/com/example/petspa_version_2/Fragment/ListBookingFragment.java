package com.example.petspa_version_2.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petspa_version_2.Activity.BookingDetail;
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
    private RecyclerView recyclerView;
    private ListBookingFragmentAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_booking, container, false);
        recyclerView = view.findViewById(R.id.listBookingRecyclerView);
        if(listBooking != null){
            if(!listBooking.isEmpty()){
                adapter = new ListBookingFragmentAdapter(listBooking, this);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
                recyclerView.setAdapter(adapter);
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

    private ArrayList<Booking> sortList(ArrayList<Booking> listBooking){
        ArrayList<Booking> result = new ArrayList<>();
        for (int i = listBooking.size() - 1; i >= 0 ; i--) {
            result.add(listBooking.get(i));
        }
        return result;
    }
    @Override
    public void onClickBookingItem(ServicePet servicePet) {
        Intent intent = new Intent(getContext(), BookingDetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("bookingDetail", (Serializable) servicePet);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
