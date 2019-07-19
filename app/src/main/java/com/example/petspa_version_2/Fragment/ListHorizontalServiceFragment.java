package com.example.petspa_version_2.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.petspa_version_2.Activity.BookingActivity;
import com.example.petspa_version_2.Activity.ListServicePetActivity;
import com.example.petspa_version_2.Adapter.ListHorizontalServiceFragmentAdapter;
import com.example.petspa_version_2.Global.GlobalValue;
import com.example.petspa_version_2.Listener.List_Service_Card_Item_Listener;
import com.example.petspa_version_2.Model.ServicePet;
import com.example.petspa_version_2.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListHorizontalServiceFragment extends Fragment implements List_Service_Card_Item_Listener {
    private List<ServicePet> listServicePet1 = new ArrayList<>();
    private List<ServicePet> listServicePet2 = new ArrayList<>();
    private List<ServicePet> listServicePet3 = new ArrayList<>();
    private List<ServicePet> listServicePet4 = new ArrayList<>();
    private ListHorizontalServiceFragmentAdapter adapterKhuyenmai;
    private ListHorizontalServiceFragmentAdapter adapterVeSinh;
    private ListHorizontalServiceFragmentAdapter adapterSpa;
    private ListHorizontalServiceFragmentAdapter adapterSitter;
    private RecyclerView recyclerListService;
    private RecyclerView recyclerListServiceVesinh;
    private RecyclerView recyclerListServiceSpa;
    private RecyclerView recyclerListServiceSitter;
    private Button btnSpa, btnKhuyenMai, btnVeSinh, btnSitter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_horizontal, container, false);

        btnKhuyenMai = view.findViewById(R.id.btnKhuyenMai);
        btnVeSinh = view.findViewById(R.id.btnVeSinh);
        btnSpa = view.findViewById(R.id.btnSpa);
        inputData4();
        inputData();
        inputData2();
        recyclerListService = view.findViewById(R.id.recyclerListService);
        adapterKhuyenmai = new ListHorizontalServiceFragmentAdapter(listServicePet1, (List_Service_Card_Item_Listener) this);
        recyclerListService.setLayoutManager(new GridLayoutManager(getContext(), listServicePet1.size()));
        recyclerListService.setAdapter(adapterKhuyenmai);

        recyclerListServiceVesinh = view.findViewById(R.id.recyclerListServiceVesinh);
        adapterVeSinh = new ListHorizontalServiceFragmentAdapter(listServicePet2, (List_Service_Card_Item_Listener) this);
        recyclerListServiceVesinh.setLayoutManager(new GridLayoutManager(getContext(), listServicePet2.size()));
        recyclerListServiceVesinh.setAdapter(adapterVeSinh);

        recyclerListServiceSpa = view.findViewById(R.id.recyclerListServiceSpa);
        adapterSpa = new ListHorizontalServiceFragmentAdapter(listServicePet3, (List_Service_Card_Item_Listener) this);
        recyclerListServiceSpa.setLayoutManager(new GridLayoutManager(getContext(), listServicePet3.size()));
        recyclerListServiceSpa.setAdapter(adapterSpa);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        btnKhuyenMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListServicePetActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("serviceType", GlobalValue.CARD_VIEW_4);
                startActivity(intent);

                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        btnVeSinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListServicePetActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("serviceType", GlobalValue.CARD_VIEW_1);
                startActivity(intent);

                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        btnSpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListServicePetActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("serviceType", GlobalValue.CARD_VIEW_2);
                startActivity(intent);

                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }

    private void inputData() {
        listServicePet2.add(new ServicePet("Vệ sinh tổng thể",
                "Cắt tỉa lông, tắm gội, sấy khố, tạo mẫu",
                "Dịch vụ vệ sinh tổng thể của chúng tôi sẽ cung cấp đầy đủ những thứ mà thú cưng và bạn cần, bao gồm tỉa lông, cắt móng chân, " +
                        "tỉa lông cân, vệ sinh tai, 2 lần tắm gội bằng xà bông chuyên dùng cho chó và mèo, sấy khô và giữ ấm, tạo mẫu cho tóc và đuôi.",
                R.drawable.down_shower, "240,000", 4, "300,000", "-20%"));

        listServicePet2.add(new ServicePet("Gỡ rối lông",
                "Lông rối thì không có gì phải lo.",
                "Đối với những thú cưng nghịch ngợm, thích chạy nhảy và đôi khi lông của chúng bị rối và khô," +
                        " chúng tôi cung cấp dịch vụ gỡ rối lông và làm mượt lông làm cho thú cưng của bạn dễ chịu " +
                        "và bạn cũng bớt lo lắng khi đi chơi cùng thú cưng của mình.",
                R.drawable.service_go_roi_long, "50,000", (float) (4.5)));

        listServicePet2.add(new ServicePet("Loại bỏ vết ố ở mắt",
                "Vết ố ở mắt có làm thú cưng của bạn khó chịu",
                "Thú cưng của bạn đôi khi bị bẩn ở khóe mắt và bạn không thể làm sạch được, đến với dịch vụ của chúng tôi, " +
                        "chúng tôi sử dụng dung dịch vệ sinh chuyên dụng để làm sạch khóe mắt cho thú cưng của bạn, đảm bảo vô hại với thú cưng.",
                R.drawable.service_ve_sinh_mat, "50,000", (float) (3.3)));

        listServicePet2.add(new ServicePet("Làm sạch hơi thở",
                "Một hơi thở sạch sẽ chẳng phải tốt hơn sao",
                "Thú cưng của bạn có một hơi thở làm bạn thấy khó chịu do thức ăn, chúng tôi sẽ giải quyết vấn đề này giúp bạn không qua 30'.",
                R.drawable.ve_sinh_mieng, "50,000", (float) (5)));

        listServicePet2.add(new ServicePet("Tắm và sấy khô",
                "Không cần quá mất thời gian về việc vệ sinh, đã có Petspa.",
                "Dịch vụ tắm và sấy khô bên chúng tôi bao gồm loại bỏ các bụi bẩn và lông bị rối, tắm rửa qua 2 lần dầu gọi," +
                        " sấy khô và giữ ấm cho thú cưng của bạn, đặc biệt còn được sử dụng nước hoa mùi hương sang trọng dễ chịu.",
                R.drawable.tamsay, "160,000", (float) (4.7), "200,000", "-20%"));
    }

    private void inputData2() {
        listServicePet3.add(new ServicePet("Tẩy lông và hồi phục màu lông",
                "Bạn muốn phục hồi lại màu lông của thú cưng",
                "Thú cưng của bạn đôi khi ham chơi và lỡ làm bẩn những chỗ như lông cổ," +
                        " lông đuôi mà cỡ nào bạn cũng không thể loại bỏ được những bụi bẩn ấy," +
                        " hãy để chúng tôi làm điều này giúp bạn, dịch vụ hồi phục và " +
                        "tẩy trắng lông của chúng tôi sẽ giải quyết được khó khăn của bạn.",
                R.drawable.spa_long, "100,000", (float) (3.9)));

        listServicePet3.add(new ServicePet("Tạo mẫu tóc",
                "Đôi khi thú cưng của bạn cần một sự khác biệt.",
                "Những nhà tạo mẫu bên chúng tôi sẽ cung cấp những mẫu tóc bắt kịp xu hướng cho thú cưng của bạn," +
                        " đảm bảo không bị rối và làm hài thú cưng của bạn trở nên tuyệt vời hơn," +
                        " được miễn phí một lần chụp hình tạo mẫu chuyên nghiệp.",
                R.drawable.spa_hair, "64,000", (float) (4.9), "80,000", "-20%"));

        listServicePet3.add(new ServicePet("Sơn móng chân",
                "Tại sao lại không, giúp cho thú cưng của bạn xinh đẹp hơn mà.",
                "Bạn muốn thú cưng của bạn trông xinh xắn hơn với một bộ móng chân khác biệt, chúng tôi cung cấp dịch vụ sơn móng chân " +
                        "và tỉa móng, biến những đôi chân tinh nghịch của thú cưng của bạn trông xinh xắn hơn.",
                R.drawable.spa_nail, "50,000", (float) (5)));

        listServicePet3.add(new ServicePet("Massage thư giãn và đắp mặt nạ",
                "Thú cưng của bạn cũng cần massage mà.",
                "Thú cưng của bạn sẽ được sử dụng máy massage chuyên dùng cho thú cưng," +
                        " giúp thú cưng của bạn có những giây phút thoải mái nhất.",
                R.drawable.spa_massage, "50,000", (float) (3.7)));

        listServicePet3.add(new ServicePet("Điều trị giảm béo",
                "Béo ư, hãy đến với Petspa",
                "Lại là vấn đề về cân nặng, thú cưng của bạn quá ham ăn, ăn quá nhiều " +
                        "và phát phì vì điều này, trở nên lười biếng, ít vận động." +
                        " Hãy đến với dịch vụ của chúng tôi, chúng tôi cung cấp các phương pháp điều trị giảm béo cho thú cưng của bạn.",
                R.drawable.spa_excercise, "40,000", (float) (4.5), "60,000", "-33%"));
    }


    private void inputData4() {
        listServicePet1.add(new ServicePet("Loại bỏ vết ố ở mắt",
                "Vết ố ở mắt có làm thú cưng của bạn khó chịu",
                "Thú cưng của bạn đôi khi bị bẩn ở khóe mắt và bạn không thể làm sạch được, đến với dịch vụ của chúng tôi, " +
                        "chúng tôi sử dụng dung dịch vệ sinh chuyên dụng để làm sạch khóe mắt cho thú cưng của bạn, đảm bảo vô hại với thú cưng.",
                R.drawable.service_ve_sinh_mat, "40,200", (float) (4.6), "60,000", "-33%"));

        listServicePet1.add(new ServicePet("Điều trị giảm béo",
                "Béo ư, hãy đến với Petspa",
                "Lại là vấn đề về cân nặng, thú cưng của bạn quá ham ăn, ăn quá nhiều " +
                        "và phát phì vì điều này, trở nên lười biếng, ít vận động." +
                        " Hãy đến với dịch vụ của chúng tôi, chúng tôi cung cấp các phương pháp điều trị giảm béo cho thú cưng của bạn.",
                R.drawable.spa_excercise, "40,200", (float) (4.5), "60,000", "-33%"));
        listServicePet1.add(new ServicePet("Vệ sinh tổng thể",
                "Cắt tỉa lông, tắm gội, sấy khố, tạo mẫu",
                "Dịch vụ vệ sinh tổng thể của chúng tôi sẽ cung cấp đầy đủ những thứ mà thú cưng và bạn cần, bao gồm tỉa lông, cắt móng chân, " +
                        "tỉa lông cân, vệ sinh tai, 2 lần tắm gội bằng xà bông chuyên dùng cho chó và mèo, sấy khô và giữ ấm, tạo mẫu cho tóc và đuôi.",
                R.drawable.down_shower, "240,000", 4, "300,000", "-20%"));

        listServicePet1.add(new ServicePet("Tạo mẫu tóc",
                "Đôi khi thú cưng của bạn cần một sự khác biệt.",
                "Những nhà tạo mẫu bên chúng tôi sẽ cung cấp những mẫu tóc bắt kịp xu hướng cho thú cưng của bạn," +
                        " đảm bảo không bị rối và làm hài thú cưng của bạn trở nên tuyệt vời hơn," +
                        " được miễn phí một lần chụp hình tạo mẫu chuyên nghiệp.",
                R.drawable.spa_hair, "64,000", (float) (3.9), "80,000", "-20%"));


        listServicePet1.add(new ServicePet("Tắm và sấy khô",
                "Không cần quá mất thời gian về việc vệ sinh, đã có Petspa.",
                "Dịch vụ tắm và sấy khô bên chúng tôi bao gồm loại bỏ các bụi bẩn và lông bị rối, tắm rửa qua 2 lần dầu gọi," +
                        " sấy khô và giữ ấm cho thú cưng của bạn, đặc biệt còn được sử dụng nước hoa mùi hương sang trọng dễ chịu.",
                R.drawable.tamsay, "160,000", (float) (4.7), "200,000", "-20%"));
    }

    @Override
    public void viewService(ServicePet servicePet) {
        Intent intent = new Intent(getContext(), BookingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        intent.putExtra("service", (Serializable) servicePet);

        startActivity(intent);
    }
}
