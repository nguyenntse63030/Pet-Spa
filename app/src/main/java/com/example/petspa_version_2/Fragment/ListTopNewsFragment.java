package com.example.petspa_version_2.Fragment;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.example.petspa_version_2.Adapter.ListTopNewsFragmentAdapter;
import com.example.petspa_version_2.Listener.Top_News_Listener;
import com.example.petspa_version_2.Model.News;
import com.example.petspa_version_2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListTopNewsFragment extends Fragment implements Top_News_Listener {
    private List<News> listNews = new ArrayList<>();
    private ListTopNewsFragmentAdapter adapter;
    private RecyclerView recyclerView;
    private Top_News_Listener mCallBack;
    private HorizontalScrollView horizontalScrollView;
    int end=0;
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();

    public void setmCallBack(Top_News_Listener mCallBack) {
        this.mCallBack = mCallBack;
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
        horizontalScrollView = view.findViewById(R.id.horizontalView);
        recyclerView = view.findViewById(R.id.recyclerListTopNews);
        adapter = new ListTopNewsFragmentAdapter(getContext(), listNews, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), listNews.size()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void openTopNewsItem(News news) {
        mCallBack.openTopNewsItem(news);
    }

    private void inputData(){
        listNews.add(new News("Bảo Long",
                "Có nhiều người lạ lắm, mặc dù họ chẳng hề có ý định dành cho bạn một phần nhỏ......",
                R.drawable.album11, "08/06/2019",
                "Có nhiều người lạ lắm, mặc dù họ chẳng hề có ý định dành cho bạn một phần nhỏ " +
                        "xíu nào trong cuộc đời họ nhưng lúc nào cũng muốn là một phần rất quan trọng trong cuộc đời bạn."));

        listNews.add(new News("Trung Nguyên",
                "Thời gian một người bỏ ra cho bạn là tình yêu của người đó dành cho bạn. Không phải ai rảnh.....",
                R.drawable.album10, "07/06/2019",
                "Thời gian một người bỏ ra cho bạn là tình yêu của người đó dành cho bạn. Không phải ai rảnh" +
                        " sẽ bỏ ra nhiều hơn mà là ai yêu nhiều hơn sẽ cố gắng ở bên bạn nhiều hơn."));

        listNews.add(new News("Hữu Lễ",
                "Không phải vết thương nào chảy máu cũng đều đau. Có đôi khi vết thương không nhìn thấy.....",
                R.drawable.album9, "06/06/2019",
                "Không phải vết thương nào chảy máu cũng đều đau. Có đôi khi vết thương không nhìn thấy" +
                        " máu mới thật sự là vết thương đau nhất."));

        listNews.add(new News("Hoàng Nhân",
                "Đừng lập gia đình sớm, dù bất cứ lý do nào đừng vội khi chưa sẵn sàng, chưa từng trải chưa......",
                R.drawable.album8, "05/06/2019",
                "Đừng lập gia đình sớm, dù bất cứ lý do nào đừng vội khi chưa sẵn sàng, chưa từng trải chưa" +
                        " hiểu được chung sống là một thử thách to lớn thế nào."));

        listNews.add(new News("Duy Thắng",
                "Không ai có quyền phán xét bạn vì họ không biết bạn đã trải qua những gì. Họ có thể đã nghe những.......",
                R.drawable.album7, "04/06/2019",
                "Không ai có quyền phán xét bạn vì họ không biết bạn đã trải qua những gì. Họ có thể đã nghe những " +
                        "câu chuyện nhưng họ sẽ không bao giờ có cùng cảm giác với bạn."));

        listNews.add(new News("Bình Minh",
                "Đừng mơ trong cuộc sống mà hãy sống trong giấc mơ.",
                R.drawable.album6, "03/06/2019", "aaaaa"));

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
    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {
                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        //get the current timeStamp
                        end += horizontalScrollView.getWidth();
                        horizontalScrollView.smoothScrollTo(end,0);
                        if (end >= (horizontalScrollView.getWidth()*5)){
                            end = 0 - horizontalScrollView.getWidth();
                        }
                    }
                });
            }
        };
    }
    @Override
    public void onPause() {
        super.onPause();
        stoptimertask();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onStart() {
        super.onStart();
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask,3000,3000);

        horizontalScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sleepTimer();
                return false;
            }
        });

    }
    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
    public void sleepTimer(){
        if (timer != null) {
            stoptimertask();
            try {
                timer = new Timer();
                initializeTimerTask();
                timer.schedule(timerTask, 5000,3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }
}
