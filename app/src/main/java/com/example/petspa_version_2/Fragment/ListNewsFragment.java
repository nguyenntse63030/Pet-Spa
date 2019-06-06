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
import com.example.petspa_version_2.Model.News;
import com.example.petspa_version_2.R;

import java.util.ArrayList;
import java.util.List;

public class ListNewsFragment extends Fragment {
    private List<News> listNews = new ArrayList<>();
    private ListNewsFragmentAdapter adapter;
    private RecyclerView recyclerView;

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
        adapter = new ListNewsFragmentAdapter(getContext(), listNews);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void inputData(){
        listNews.add(new News("Bảo Long",
                "Không phải vết thương nào chảy máu cũng đều đau",
                R.drawable.album11));

        listNews.add(new News("Trung Nguyên",
                "Có đôi khi vết thương không nhìn thấy máu mới thật sự là vết thương đau nhất.",
                R.drawable.album10));

        listNews.add(new News("Hữu Lễ",
                "Đừng lập gia đình sớm, dù bất cứ lý do nào đừng vội khi chưa sẵn sàng, chưa từng trải chưa hiểu được chung sống là một thử thách to lớn thế nào.",
                R.drawable.album9));

        listNews.add(new News("Hoàng Nhân",
                "Không ai có quyền phán xét bạn vì họ không biết bạn đã trải qua những gì.",
                R.drawable.album8));

        listNews.add(new News("Duy Thắng",
                "Hãy tự biết cách gây áp lực cho chính bản thân để vươn lên và tỏa sáng. Bởi vì không ai sẽ làm điều đó thay cho bạn.",
                R.drawable.album7));

        listNews.add(new News("Bình Minh",
                "Đừng mơ trong cuộc sống mà hãy sống trong giấc mơ.",
                R.drawable.album6));

        listNews.add(new News("Đức Toàn",
                "Dù bạn có vấp ngã hàng trăm lần thì cũng đừng bỏ cuộc. Hãy đứng dậy.",
                R.drawable.album5));

        listNews.add(new News("Bá Nam",
                "Đôi khi nếu bạn chờ đợi quá nhiều thứ cùng lúc, rất có thể bạn sẽ ra về trắng tay.",
                R.drawable.album4));
    }
}
