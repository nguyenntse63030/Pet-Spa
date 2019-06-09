package com.example.petspa_version_2.Fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petspa_version_2.Adapter.ListTopNewsFragmentAdapter;
import com.example.petspa_version_2.Model.News;
import com.example.petspa_version_2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListTopNewsFragment extends Fragment {
    private List<News> listNews = new ArrayList<>();
    private ListTopNewsFragmentAdapter adapter;
    private RecyclerView recyclerView;

    public ListTopNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_top_news, container, false);

        recyclerView = view.findViewById(R.id.recyclerListTopNews);
        adapter = new ListTopNewsFragmentAdapter(getContext(), listNews);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), listNews.size()));
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void inputData(){
        listNews.add(new News("Bảo Long",
                R.drawable.album11));

        listNews.add(new News("Trung Nguyên",
                R.drawable.album10));

        listNews.add(new News("Hữu Lễ",
                R.drawable.album9));

        listNews.add(new News("Hoàng Nhân",
                  R.drawable.album8));

        listNews.add(new News("Duy Thắng",
                 R.drawable.album7));

        listNews.add(new News("Bình Minh",
                 R.drawable.album6));

        listNews.add(new News("Đức Toàn",
                 R.drawable.album5));

        listNews.add(new News("Bá Nam",
                R.drawable.album4));
    }
}
