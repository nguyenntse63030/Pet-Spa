package com.example.petspa_version_2.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petspa_version_2.Adapter.ListHorizontalServiceFragmentAdapter;
import com.example.petspa_version_2.Listener.List_Service_Card_Item_Listener;
import com.example.petspa_version_2.Model.ServicePet;
import com.example.petspa_version_2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListHorizontalServiceFragment extends Fragment implements List_Service_Card_Item_Listener {
    private List<ServicePet> listServicePet1=new ArrayList<>();
    private List<ServicePet> listServicePet2=new ArrayList<>();
    private List<ServicePet> listServicePet3=new ArrayList<>();
    private List<ServicePet> listServicePet4=new ArrayList<>();
    private ListHorizontalServiceFragmentAdapter adapterKhuyenmai;
    private ListHorizontalServiceFragmentAdapter adapterVeSinh;
    private ListHorizontalServiceFragmentAdapter adapterSpa;
    private ListHorizontalServiceFragmentAdapter adapterSitter;
    private RecyclerView recyclerListService;
    private RecyclerView recyclerListServiceVesinh;
    private RecyclerView recyclerListServiceSpa;
    private RecyclerView recyclerListServiceSitter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_horizontal, container, false);
        recyclerListService = view.findViewById(R.id.recyclerListService);
        inputData4();
        adapterKhuyenmai = new ListHorizontalServiceFragmentAdapter( listServicePet1, (List_Service_Card_Item_Listener) this);
        recyclerListService.setLayoutManager(new GridLayoutManager(getContext(), listServicePet1.size()));
        recyclerListService.setAdapter(adapterKhuyenmai);

        recyclerListServiceVesinh = view.findViewById(R.id.recyclerListServiceVesinh);
        inputData();
        adapterVeSinh = new ListHorizontalServiceFragmentAdapter( listServicePet2, (List_Service_Card_Item_Listener) this);
        recyclerListServiceVesinh.setLayoutManager(new GridLayoutManager(getContext(), listServicePet2.size()));
        recyclerListServiceVesinh.setAdapter(adapterVeSinh);

        recyclerListServiceSpa = view.findViewById(R.id.recyclerListServiceSpa);
        inputData2();
        adapterSpa = new ListHorizontalServiceFragmentAdapter( listServicePet3, (List_Service_Card_Item_Listener) this);
        recyclerListServiceSpa.setLayoutManager(new GridLayoutManager(getContext(), listServicePet3.size()));
        recyclerListServiceSpa.setAdapter(adapterSpa);

        recyclerListServiceSitter = view.findViewById(R.id.recyclerListServiceSitter);
        inputData3();
        adapterSitter = new ListHorizontalServiceFragmentAdapter( listServicePet4, (List_Service_Card_Item_Listener) this);
        recyclerListServiceSitter.setLayoutManager(new GridLayoutManager(getContext(), listServicePet4.size()));
        recyclerListServiceSitter.setAdapter(adapterSitter);
        return view;
    }
    private void inputData(){
        listServicePet2.add(new ServicePet("Vệ sinh tổng thể (2h đến 3h)",
                "Cắt tỉa lông, tắm gội, sấy khố, tạo mẫu",
                "Dịch vụ vệ sinh tổng thể của chúng tôi sẽ cung cấp đầy đủ những thứ mà thú cưng và bạn cần, bao gồm tỉa lông, cắt móng chân, " +
                        "tỉa lông cân, vệ sinh tai, 2 lần tắm gội bằng xà bông chuyên dùng cho chó và mèo, sấy khô và giữ ấm, tạo mẫu cho tóc và đuôi.",
                R.drawable.down_shower, "300,000", 4));

        listServicePet2.add(new ServicePet("Gỡ rối lông (15' đến 30')",
                "Lông rối thì không có gì phải lo.",
                "Đối với những thú cưng nghịch ngợm, thích chạy nhảy và đôi khi lông của chúng bị rối và khô," +
                        " chúng tôi cung cấp dịch vụ gỡ rối lông và làm mượt lông làm cho thú cưng của bạn dễ chịu " +
                        "và bạn cũng bớt lo lắng khi đi chơi cùng thú cưng của mình.",
                R.drawable.service_go_roi_long, "50,000", (float)(4.5)));

        listServicePet2.add(new ServicePet("Loại bỏ vết ố ở mắt (15' đến 30')",
                "Vết ố ở mắt có làm thú cưng của bạn khó chịu",
                "Thú cưng của bạn đôi khi bị bẩn ở khóe mắt và bạn không thể làm sạch được, đến với dịch vụ của chúng tôi, " +
                        "chúng tôi sử dụng dung dịch vệ sinh chuyên dụng để làm sạch khóe mắt cho thú cưng của bạn, đảm bảo vô hại với thú cưng.",
                R.drawable.service_ve_sinh_mat, "50,000", (float)(3.3)));

        listServicePet2.add(new ServicePet("Làm sạch hơi thở (15' đến 20')",
                "Một hơi thở sạch sẽ chẳng phải tốt hơn sao",
                "Thú cưng của bạn có một hơi thở làm bạn thấy khó chịu do thức ăn, chúng tôi sẽ giải quyết vấn đề này giúp bạn không qua 30'.",
                R.drawable.ve_sinh_mieng, "50,000", (float)(5)));

        listServicePet2.add(new ServicePet("Tắm và sấy khô (1h30 đến 2h)",
                "Không cần quá mất thời gian về việc vệ sinh, đã có Petspa.",
                "Dịch vụ tắm và sấy khô bên chúng tôi bao gồm loại bỏ các bụi bẩn và lông bị rối, tắm rửa qua 2 lần dầu gọi," +
                        " sấy khô và giữ ấm cho thú cưng của bạn, đặc biệt còn được sử dụng nước hoa mùi hương sang trọng dễ chịu.",
                R.drawable.tamsay, "200,000", (float)(4.1)));
    }

    private void inputData2(){
        listServicePet3.add(new ServicePet("Tẩy lông và hồi phục màu lông (15' đến 30')",
                "Bạn muốn phục hồi lại màu lông của thú cưng",
                "Thú cưng của bạn đôi khi ham chơi và lỡ làm bẩn những chỗ như lông cổ," +
                        " lông đuôi mà cỡ nào bạn cũng không thể loại bỏ được những bụi bẩn ấy," +
                        " hãy để chúng tôi làm điều này giúp bạn, dịch vụ hồi phục và " +
                        "tẩy trắng lông của chúng tôi sẽ giải quyết được khó khăn của bạn.",
                R.drawable.spa_long, "100,000", (float)(3.9)));

        listServicePet3.add(new ServicePet("Tạo mẫu tóc (30' đến 1h)",
                "Đôi khi thú cưng của bạn cần một sự khác biệt.",
                "Những nhà tạo mẫu bên chúng tôi sẽ cung cấp những mẫu tóc bắt kịp xu hướng cho thú cưng của bạn," +
                        " đảm bảo không bị rối và làm hài thú cưng của bạn trở nên tuyệt vời hơn," +
                        " được miễn phí một lần chụp hình tạo mẫu chuyên nghiệp.",
                R.drawable.spa_hair, "80,000", (float)(4.9)));

        listServicePet3.add(new ServicePet("Sơn móng chân (20' đến 30')",
                "Tại sao lại không, giúp cho thú cưng của bạn xinh đẹp hơn mà.",
                "Bạn muốn thú cưng của bạn trông xinh xắn hơn với một bộ móng chân khác biệt, chúng tôi cung cấp dịch vụ sơn móng chân " +
                        "và tỉa móng, biến những đôi chân tinh nghịch của thú cưng của bạn trông xinh xắn hơn.",
                R.drawable.spa_nail, "50,000", (float)(5)));

        listServicePet3.add(new ServicePet("Massage thư giãn và đắp mặt nạ (15' đến 30')",
                "Thú cưng của bạn cũng cần massage mà.",
                "Thú cưng của bạn sẽ được sử dụng máy massage chuyên dùng cho thú cưng," +
                        " giúp thú cưng của bạn có những giây phút thoải mái nhất.",
                R.drawable.spa_massage, "50,000", (float)(3.7)));

        listServicePet3.add(new ServicePet("Điều trị giảm béo (20' đến 30')",
                "Béo ư, hãy đến với Petspa",
                "Lại là vấn đề về cân nặng, thú cưng của bạn quá ham ăn, ăn quá nhiều " +
                        "và phát phì vì điều này, trở nên lười biếng, ít vận động." +
                        " Hãy đến với dịch vụ của chúng tôi, chúng tôi cung cấp các phương pháp điều trị giảm béo cho thú cưng của bạn.",
                R.drawable.spa_excercise, "50,000", (float)(4.6)));
    }

    private void inputData3(){
        listServicePet4.add(new ServicePet("Giữ thú cưng theo giờ",
                "Bạn bận ư, hãy để chúng tôi chăm sóc thú cưng của bạn.",
                "Bạn phải đi làm xa, đi chơi với gia đình, bạn bè và không thể mang theo thú cưng của bạn." +
                        " Đừng lo, chúng tôi cung cấp dịch vụ giữ và chăm sóc thú cưng 24/7," +
                        " với dịch vụ đồ ăn và nước uống kèm theo 3 bữa/ngày đảm bảo thú cưng của bạn luôn cảm thấy vui vẻ." +
                        " Vào mọi thời điểm, bạn cần là chúng tôi sẽ có mặt. (Vui lòng liên hệ theo hotline 0903 xxx xxx để đặt lịch và được báo giá)",
                R.drawable.pet_take_care, "Liên hệ để được báo giá", (float)(4.0)));
    }

    private void inputData4(){
        listServicePet1.add(new ServicePet("Vệ sinh tổng thể: (2h đến 3h)",
                "Cắt tỉa lông, tắm gội, sấy khố, tạo mẫu",
                "Dịch vụ vệ sinh tổng thể của chúng tôi sẽ cung cấp đầy đủ những thứ mà thú cưng và bạn cần, bao gồm tỉa lông, cắt móng chân, " +
                        "tỉa lông cân, vệ sinh tai, 2 lần tắm gội bằng xà bông chuyên dùng cho chó và mèo, sấy khô và giữ ấm, tạo mẫu cho tóc và đuôi.",
                R.drawable.down_shower, "240,000", (float)(3.0)));

        listServicePet1.add(new ServicePet("Tạo mẫu tóc (30' đến 1h)",
                "Đôi khi thú cưng của bạn cần một sự khác biệt.",
                "Những nhà tạo mẫu bên chúng tôi sẽ cung cấp những mẫu tóc bắt kịp xu hướng cho thú cưng của bạn," +
                        " đảm bảo không bị rối và làm hài thú cưng của bạn trở nên tuyệt vời hơn," +
                        " được miễn phí một lần chụp hình tạo mẫu chuyên nghiệp.",
                R.drawable.spa_hair, "64,000", (float)(3.9)));

        listServicePet1.add(new ServicePet("Loại bỏ vết ố ở mắt: (15' đến 30')",
                "Vết ố ở mắt có làm thú cưng của bạn khó chịu",
                "Thú cưng của bạn đôi khi bị bẩn ở khóe mắt và bạn không thể làm sạch được, đến với dịch vụ của chúng tôi, " +
                        "chúng tôi sử dụng dung dịch vệ sinh chuyên dụng để làm sạch khóe mắt cho thú cưng của bạn, đảm bảo vô hại với thú cưng.",
                R.drawable.service_ve_sinh_mat, "40,000", (float)(4.6)));

        listServicePet1.add(new ServicePet("Điều trị giảm béo (20' đến 30')",
                "Béo ư, hãy đến với Petspa",
                "Lại là vấn đề về cân nặng, thú cưng của bạn quá ham ăn, ăn quá nhiều " +
                        "và phát phì vì điều này, trở nên lười biếng, ít vận động." +
                        " Hãy đến với dịch vụ của chúng tôi, chúng tôi cung cấp các phương pháp điều trị giảm béo cho thú cưng của bạn.",
                R.drawable.spa_excercise, "40,000", (float)(4.5)));

        listServicePet1.add(new ServicePet("Tắm và sấy khô: (1h30 đến 2h)",
                "Không cần quá mất thời gian về việc vệ sinh, đã có Petspa.",
                "Dịch vụ tắm và sấy khô bên chúng tôi bao gồm loại bỏ các bụi bẩn và lông bị rối, tắm rửa qua 2 lần dầu gọi," +
                        " sấy khô và giữ ấm cho thú cưng của bạn, đặc biệt còn được sử dụng nước hoa mùi hương sang trọng dễ chịu.",
                R.drawable.tamsay, "160,000", (float)(4.7)));
    }

    @Override
    public void viewService(ServicePet servicePet) {

    }
}
