package com.example.petspa_version_2.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petspa_version_2.Activity.BookingActivity;
import com.example.petspa_version_2.Adapter.ListServicePetFragmentAdapter;
import com.example.petspa_version_2.Listener.List_Service_Item_Listener;
import com.example.petspa_version_2.Model.ServicePet;
import com.example.petspa_version_2.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author LongDong(04/06/2019)
 * */
public class ListServicePetFragment extends Fragment implements List_Service_Item_Listener {
    List<ServicePet> listServicePet = new ArrayList<>();
    ListServicePetFragmentAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_service_pet, container, false);

        recyclerView = view.findViewById(R.id.recyclerListServicePet);
        adapter = new ListServicePetFragmentAdapter(listServicePet, getContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void openBookingScreen(ServicePet service) {
        Intent intent = new Intent(getContext(), BookingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        intent.putExtra("service", (Serializable) service);

        startActivity(intent);
    }

    private void inputData(){
        listServicePet.add(new ServicePet("Cắt móng",
                "Vệ sinh hoạt động cho chó mèo cúng hello đặng hữu lễ, lê bảo long",
                "Yêu được thì bỏ được, Nhớ được thì quên được, Bắt đầu được thì kết thúc được. Nhưng mà sao nói được chẳng làm được?",
                R.drawable.pet1, 1400));

        listServicePet.add(new ServicePet("Tỉa lông",
                "Vệ sinh hoạt động cho chó mèo cúng hello đặng hữu lễ, lê bảo long",
                "Bạn sẽ không bao giờ biết được mình mạnh mẽ như thế nào cho đến khi trở lên mạnh mẽ là sự lựa chọn duy nhất mà bạn có.",
                R.drawable.pet2, 1500));

        listServicePet.add(new ServicePet("Tắm Rửa",
                "Vệ sinh hoạt động cho chó mèo cúng hello đặng hữu lễ, lê bảo long",
                "Đừng chạy theo những thứ không thuộc về mình.",
                R.drawable.pet3, 1600));

        listServicePet.add(new ServicePet("Chữa bệnh",
                "Vệ sinh hoạt động cho chó mèo cúng hello đặng hữu lễ, lê bảo long",
                "Đừng đứng đợi những thứmà mình có thể nắm giữ ở ngay trước mặt.",
                R.drawable.pet4, 1700));

        listServicePet.add(new ServicePet("Trông hộ",
                "Vệ sinh hoạt động cho chó mèo cúng hello đặng hữu lễ, lê bảo long",
                "Vấp ngã phải biết tự đứng dậy.",
                R.drawable.pet5, 1800));

        listServicePet.add(new ServicePet("Trung Nguyên",
                "Vệ sinh hoạt động cho chó mèo cúng hello đặng hữu lễ, lê bảo long",
                "Đừng chờ đợi những thứ không bao giờ đến...",
                R.drawable.pet6, 1200));

        listServicePet.add(new ServicePet("Hữu Lễ",
                "Vệ sinh hoạt động cho chó mèo cúng hello đặng hữu lễ, lê bảo long",
                "Những cô gái hay cười chính là những cô gái xinh đẹp nhất,",
                R.drawable.pet7, 1300));

        listServicePet.add(new ServicePet("Duy Thắng",
                "Vệ sinh hoạt động cho chó mèo cúng hello đặng hữu lễ, lê bảo long",
                "Khi bạn thất bại, bạn vẫn phải quay trở lại và tiếp tục bước đi.",
                R.drawable.pet8, 1500));

        listServicePet.add(new ServicePet("Hoàng Nhân",
                "Vệ sinh hoạt động cho chó mèo cúng hello đặng hữu lễ, lê bảo long",
                "Sẽ chẳng ý nghĩa gì nếu bạn cứ ngồi đó.",
                R.drawable.pet4, 1100));
    }
}
