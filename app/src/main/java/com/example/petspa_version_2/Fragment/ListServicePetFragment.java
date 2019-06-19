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
import com.example.petspa_version_2.Global.GlobalValue;
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

    private String cardView;

    public ListServicePetFragment(String cardView) {
        this.cardView = cardView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(cardView.equals(GlobalValue.CARD_VIEW_2)){
            inputData2();
        }else if(cardView.equals(GlobalValue.CARD_VIEW_3)){
            inputData3();
        }else if(cardView.equals(GlobalValue.CARD_VIEW_4)){
            inputData4();
        }else {
            inputData();
        }
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
        listServicePet.add(new ServicePet("Vệ sinh tổng thể (2h đến 3h)",
                "Cắt tỉa lông, tắm gội, sấy khố, tạo mẫu",
                "Dịch vụ vệ sinh tổng thể của chúng tôi sẽ cung cấp đầy đủ những thứ mà thú cưng và bạn cần, bao gồm tỉa lông, cắt móng chân, " +
                        "tỉa lông cân, vệ sinh tai, 2 lần tắm gội bằng xà bông chuyên dùng cho chó và mèo, sấy khô và giữ ấm, tạo mẫu cho tóc và đuôi.",
                R.drawable.pet1, "300,000"));

        listServicePet.add(new ServicePet("Gỡ rối lông (15' đến 30')",
                "Lông rối thì không có gì phải lo.",
                "Đối với những thú cưng nghịch ngợm, thích chạy nhảy và đôi khi lông của chúng bị rối và khô," +
                        " chúng tôi cung cấp dịch vụ gỡ rối lông và làm mượt lông làm cho thú cưng của bạn dễ chịu " +
                        "và bạn cũng bớt lo lắng khi đi chơi cùng thú cưng của mình.",
                R.drawable.pet2, "50,000"));

        listServicePet.add(new ServicePet("Loại bỏ vết ố ở mắt (15' đến 30')",
                "Vết ố ở mắt có làm thú cưng của bạn khó chịu",
                "Thú cưng của bạn đôi khi bị bẩn ở khóe mắt và bạn không thể làm sạch được, đến với dịch vụ của chúng tôi, " +
                        "chúng tôi sử dụng dung dịch vệ sinh chuyên dụng để làm sạch khóe mắt cho thú cưng của bạn, đảm bảo vô hại với thú cưng.",
                R.drawable.pet3, "50,000"));

        listServicePet.add(new ServicePet("Làm sạch hơi thở (15' đến 20')",
                "Một hơi thở sạch sẽ chẳng phải tốt hơn sao",
                "Thú cưng của bạn có một hơi thở làm bạn thấy khó chịu do thức ăn, chúng tôi sẽ giải quyết vấn đề này giúp bạn không qua 30'.",
                R.drawable.pet4, "50,000"));

        listServicePet.add(new ServicePet("Tắm và sấy khô (1h30 đến 2h)",
                "Không cần quá mất thời gian về việc vệ sinh, đã có Petspa.",
                "Dịch vụ tắm và sấy khô bên chúng tôi bao gồm loại bỏ các bụi bẩn và lông bị rối, tắm rửa qua 2 lần dầu gọi," +
                        " sấy khô và giữ ấm cho thú cưng của bạn, đặc biệt còn được sử dụng nước hoa mùi hương sang trọng dễ chịu.",
                R.drawable.pet5, "200,000"));
    }

    private void inputData2(){
        listServicePet.add(new ServicePet("Tẩy lông và hồi phục màu lông (15' đến 30')",
                "Bạn muốn phục hồi lại màu lông của thú cưng",
                "Thú cưng của bạn đôi khi ham chơi và lỡ làm bẩn những chỗ như lông cổ," +
                        " lông đuôi mà cỡ nào bạn cũng không thể loại bỏ được những bụi bẩn ấy," +
                        " hãy để chúng tôi làm điều này giúp bạn, dịch vụ hồi phục và " +
                        "tẩy trắng lông của chúng tôi sẽ giải quyết được khó khăn của bạn.",
                R.drawable.pet1, "100,000"));

        listServicePet.add(new ServicePet("Tạo mẫu tóc (30' đến 1h)",
                "Đôi khi thú cưng của bạn cần một sự khác biệt.",
                "Những nhà tạo mẫu bên chúng tôi sẽ cung cấp những mẫu tóc bắt kịp xu hướng cho thú cưng của bạn," +
                        " đảm bảo không bị rối và làm hài thú cưng của bạn trở nên tuyệt vời hơn," +
                        " được miễn phí một lần chụp hình tạo mẫu chuyên nghiệp.",
                R.drawable.pet2, "80,000"));

        listServicePet.add(new ServicePet("Sơn móng chân (20' đến 30')",
                "Tại sao lại không, giúp cho thú cưng của bạn xinh đẹp hơn mà.",
                "Bạn muốn thú cưng của bạn trông xinh xắn hơn với một bộ móng chân khác biệt, chúng tôi cung cấp dịch vụ sơn móng chân " +
                        "và tỉa móng, biến những đôi chân tinh nghịch của thú cưng của bạn trông xinh xắn hơn.",
                R.drawable.pet3, "50,000"));

        listServicePet.add(new ServicePet("Massage thư giãn và đắp mặt nạ (15' đến 30')",
                "Thú cưng của bạn cũng cần massage mà.",
                "Thú cưng của bạn sẽ được sử dụng máy massage chuyên dùng cho thú cưng," +
                        " giúp thú cưng của bạn có những giây phút thoải mái nhất.",
                R.drawable.pet4, "50,000"));

        listServicePet.add(new ServicePet("Điều trị giảm béo (20' đến 30')",
                "Béo ư, hãy đến với Petspa",
                "Lại là vấn đề về cân nặng, thú cưng của bạn quá ham ăn, ăn quá nhiều " +
                        "và phát phì vì điều này, trở nên lười biếng, ít vận động." +
                        " Hãy đến với dịch vụ của chúng tôi, chúng tôi cung cấp các phương pháp điều trị giảm béo cho thú cưng của bạn.",
                R.drawable.pet5, "50,000"));
    }

    private void inputData3(){
        listServicePet.add(new ServicePet("Giữ thú cưng theo giờ",
                "Bạn bận ư, hãy để chúng tôi chăm sóc thú cưng của bạn.",
                "Bạn phải đi làm xa, đi chơi với gia đình, bạn bè và không thể mang theo thú cưng của bạn." +
                        " Đừng lo, chúng tôi cung cấp dịch vụ giữ và chăm sóc thú cưng 24/7," +
                        " với dịch vụ đồ ăn và nước uống kèm theo 3 bữa/ngày đảm bảo thú cưng của bạn luôn cảm thấy vui vẻ." +
                        " Vào mọi thời điểm, bạn cần là chúng tôi sẽ có mặt. (Vui lòng liên hệ theo hotline 0903 xxx xxx để đặt lịch và được báo giá)",
                R.drawable.pet1, "xxx"));
    }

    private void inputData4(){
        listServicePet.add(new ServicePet("Vệ sinh tổng thể: (2h đến 3h)",
                "Cắt tỉa lông, tắm gội, sấy khố, tạo mẫu",
                "Dịch vụ vệ sinh tổng thể của chúng tôi sẽ cung cấp đầy đủ những thứ mà thú cưng và bạn cần, bao gồm tỉa lông, cắt móng chân, " +
                        "tỉa lông cân, vệ sinh tai, 2 lần tắm gội bằng xà bông chuyên dùng cho chó và mèo, sấy khô và giữ ấm, tạo mẫu cho tóc và đuôi.",
                R.drawable.pet1, "300,000"));

        listServicePet.add(new ServicePet("Gỡ rối lông: (15' đến 30')",
                "Lông rối thì không có gì phải lo.",
                "Đối với những thú cưng nghịch ngợm, thích chạy nhảy và đôi khi lông của chúng bị rối và khô," +
                        " chúng tôi cung cấp dịch vụ gỡ rối lông và làm mượt lông làm cho thú cưng của bạn dễ chịu " +
                        "và bạn cũng bớt lo lắng khi đi chơi cùng thú cưng của mình.",
                R.drawable.pet2, "50,000"));

        listServicePet.add(new ServicePet("Loại bỏ vết ố ở mắt: (15' đến 30')",
                "Vết ố ở mắt có làm thú cưng của bạn khó chịu",
                "Thú cưng của bạn đôi khi bị bẩn ở khóe mắt và bạn không thể làm sạch được, đến với dịch vụ của chúng tôi, " +
                        "chúng tôi sử dụng dung dịch vệ sinh chuyên dụng để làm sạch khóe mắt cho thú cưng của bạn, đảm bảo vô hại với thú cưng.",
                R.drawable.pet3, "50,000"));

        listServicePet.add(new ServicePet("Làm sạch hơi thở: (15' đến 20')",
                "Một hơi thở sạch sẽ chẳng phải tốt hơn sao",
                "Thú cưng của bạn có một hơi thở làm bạn thấy khó chịu do thức ăn, chúng tôi sẽ giải quyết vấn đề này giúp bạn không qua 30'.",
                R.drawable.pet4, "50,000"));

        listServicePet.add(new ServicePet("Tắm và sấy khô: (1h30 đến 2h)",
                "Không cần quá mất thời gian về việc vệ sinh, đã có Petspa.",
                "Dịch vụ tắm và sấy khô bên chúng tôi bao gồm loại bỏ các bụi bẩn và lông bị rối, tắm rửa qua 2 lần dầu gọi," +
                        " sấy khô và giữ ấm cho thú cưng của bạn, đặc biệt còn được sử dụng nước hoa mùi hương sang trọng dễ chịu.",
                R.drawable.pet5, "200,000"));
    }
}
