package com.example.petspa_version_2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petspa_version_2.Listener.Top_News_Listener;
import com.example.petspa_version_2.Model.News;
import com.example.petspa_version_2.R;

import java.util.List;

public class ListTopNewsFragmentAdapter extends RecyclerView.Adapter<ListTopNewsFragmentAdapter.MyViewHolder> {
    private Context context;
    private List<News> listNews;
    private Top_News_Listener top_news_listener;

    public ListTopNewsFragmentAdapter(Context context, List<News> listNews, Top_News_Listener top_news_listener) {
        this.context = context;
        this.listNews = listNews;
        this.top_news_listener = top_news_listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_top_news_item, parent, false);
        context = parent.getContext();

        return new ListTopNewsFragmentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.imageNews.setImageResource(listNews.get(position).getNewsImage());
        holder.txtNewsTitle.setText(listNews.get(position).getNewsTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newsTitle = listNews.get(position).getNewsTitle();
                String newsContentDetail = listNews.get(position).getNewsContentDetail();
                int imageNews = listNews.get(position).getNewsImage();
                String dateOfNews = listNews.get(position).getDateOfNews();

                News news = new News(newsTitle, imageNews, dateOfNews, newsContentDetail);

                top_news_listener.openTopNewsItem(news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageNews;
        TextView txtNewsTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageNews = itemView.findViewById(R.id.imageTopNews);
            txtNewsTitle = itemView.findViewById(R.id.txtTopNewsTitle);
        }
    }
}
