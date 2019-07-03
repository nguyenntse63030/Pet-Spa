package com.example.petspa_version_2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petspa_version_2.Adapter.ListNewsFragmentAdapter;
import com.example.petspa_version_2.Listener.List_News_Listener;
import com.example.petspa_version_2.Model.News;
import com.example.petspa_version_2.R;

import java.util.ArrayList;
import java.util.List;

public class ListNewsFragment extends Fragment implements List_News_Listener {
    private List<News> listNews = new ArrayList<>();
    private ListNewsFragmentAdapter adapter;
    private RecyclerView recyclerView;
    private List_News_Listener mCallBack;

    public void setmCallBack(List_News_Listener mCallBack) {
        this.mCallBack = mCallBack;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_news, container, false);

        recyclerView = view.findViewById(R.id.recyclerListNews);
        adapter = new ListNewsFragmentAdapter(getContext(), listNews, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClickItemNews(News news) {
        mCallBack.onClickItemNews(news);
    }

    private void inputData(){
        listNews.add(new News("Cùng chải chuốt cùng pet spa",
                "Bạn muốn chải chuốt cho người bạn của mình hay đến với pet spa vào ......",
                R.drawable.grooming, "08/06/2019",
                "Bạn muốn chải chuốt cho người bạn của mình hay đến với pet spa vào thứ 2 đến thứ 5 để được giảm 10% "));

        listNews.add(new News("Thú cưng cũng muốn đi chơi",
                "Thời gian bạn dành cho thú cưng không có nhiều ? .....",
                R.drawable.pet_play, "07/06/2019",
                "Thời gian bạn dành cho thú cưng không có nhiều ? Hãy dến với pet spa để " +
                        "thú cưng của bạn đc chơi đùa thoải mái cùng bạn bè ở khu vui chơi pet spa "));

        listNews.add(new News("Cuối tháng 2 vui vẻ ",
                "Cuối tháng 2 vui vẻ event dành cho dịch vụ cắt tỉa long thú cưng giảm .....",
                R.drawable.grooming_event, "06/06/2019",
                "Cuối tháng 2 vui vẻ event dành cho dịch vụ cắt tỉa long thú cưng giảm giá 10%!" +
                        " Nhanh chân đến với pet spa thú cưng của bạn đang rất háo hức ! "));

        listNews.add(new News("Pet spa phong cách Hàn Quốc",
                "Thú Cưng cũng muốn đi spa!!......",
                R.drawable.pet_spa_with_petcity, "05/06/2019",
                "Thú Cưng cũng muốn đi spa!! Pet spa phục vụ cho thú cưng bạn một môi trường thư giản tuyệt vời với nhiều loại tinh dầu " +
                        "chất lượng nhập từ Hàn Quốc. Đến với pet spa để thú cưng bạn có những trãi nghiệm không thể quên!"));

        listNews.add(new News("Cùng khỏe mạnh cùng pet spa",
                "Thú cưng của bạn đang lười vận động ?Sức khỏe thú cưng càng ngày càng kém?.......",
                R.drawable.dog_walking, "04/06/2019",
                "Thú cưng của bạn đang lười vận động ?Sức khỏe thú cưng càng ngày càng kém? Hãy đến với pet spa," +
                        " chúng tôi sẽ dắt thú cưng của bạn đi khắp thế giới và dần sức khỏe của chú thú cưng của bạn sẽ tốt hơn."));

        listNews.add(new News("Pet sitter bạn có thể tin tưởng",
                "Bạn có việc bận ? Bạn cần người chăm sóc thú cưng có thể tin tưởng? ....",
                R.drawable.pet_sitter, "03/06/2019",
                "Bạn có việc bận ? Bạn cần người chăm sóc thú cưng có thể tin tưởng? Hãy đến với pet spa, chúng tôi có một đội ngũ pet sitter chuyên nghiệp" +
                        " chúng tôi sẽ hoàng tiến nếu thú cưng của bạn gặp vấn đề gì trong lúc chăm sóc"));

//        listNews.add(new News("Đức Toàn",
//                "Lời nói của bạn có sức mạnh làm tan vỡ trái tim, hàn gắn mối quan hệ, khai sáng.......",
//                R.drawable.album5, "02/06/2019",
//                "Lời nói của bạn có sức mạnh làm tan vỡ trái tim, hàn gắn mối quan hệ, khai sáng con người và thay đổi thế giới." +
//                        " Hãy nói có trách nhiệm và đừng quên trách nhiệm với lời nói của bạn."));
//
//        listNews.add(new News("Bá Nam",
//                "Còn gì đẹp bằng một trái tim đang tan vỡ vẫn có thể tiếp tục tin vào tình yêu. Còn gì cao cả bằng một con người đang trải.....",
//                R.drawable.album4, "01/06/2019",
//                "Còn gì đẹp bằng một trái tim đang tan vỡ vẫn có thể tiếp tục tin vào tình yêu. Còn gì cao cả bằng một con người đang trải" +
//                        " qua bão tố cuộc đời mình vẫn tiếp tục có thể nâng đỡ những người khác."));
    }
}
