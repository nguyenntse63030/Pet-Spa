package com.example.petspa_version_2.Fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petspa_version_2.Adapter.ServiceCardViewFragmentAdapter;
import com.example.petspa_version_2.Goalball.ValueGoalball;
import com.example.petspa_version_2.Listener.Card_View_Listener;
import com.example.petspa_version_2.Listener.Service_Card_View_Fragment_Listener;
import com.example.petspa_version_2.Model.ServiceCardView;
import com.example.petspa_version_2.R;

import java.util.ArrayList;
import java.util.List;
/**
 * @author LongDong(04/06/2019)
 * */
public class ServiceCardViewFragment extends Fragment implements Card_View_Listener {
    private RecyclerView recyclerView;
    private ServiceCardViewFragmentAdapter fragmentAdapter;
    private Service_Card_View_Fragment_Listener mCallback;
    private List<ServiceCardView> listServiceCard = new ArrayList<>();

    public void setmCallback(Service_Card_View_Fragment_Listener mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listServiceCard.add(new ServiceCardView(ValueGoalball.CARD_VIEW_1, R.drawable.album1));
        listServiceCard.add(new ServiceCardView(ValueGoalball.CARD_VIEW_2, R.drawable.album2));
        listServiceCard.add(new ServiceCardView(ValueGoalball.CARD_VIEW_3, R.drawable.album3));
        listServiceCard.add(new ServiceCardView(ValueGoalball.CARD_VIEW_4, R.drawable.album4));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_service_card_view, container, false);

       recyclerView = view.findViewById(R.id.recyclerServiceCardView);
       fragmentAdapter = new ServiceCardViewFragmentAdapter(listServiceCard, getContext(), this);
       recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
       recyclerView.setAdapter(fragmentAdapter);

       return view;
    }

    @Override
    public void openServicePetList(String cardServiceName) {
        mCallback.openServicePetList(cardServiceName);
    }
}
