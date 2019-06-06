package com.example.petspa_version_2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petspa_version_2.Model.News;
import com.example.petspa_version_2.R;

import java.util.List;

public class ListNewsFragmentAdapter extends RecyclerView.Adapter<ListNewsFragmentAdapter.MyViewHolder> {
    private Context context;
    private List<News> listNews;

    public ListNewsFragmentAdapter(Context context, List<News> listNews) {
        this.context = context;
        this.listNews = listNews;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_news_item, parent, false);
        context = parent.getContext();

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageNews.setImageResource(listNews.get(position).getNewsImage());
        holder.txtNewsTitle.setText(listNews.get(position).getNewsTitle());
        holder.txtNewsContent.setText(listNews.get(position).getNewsContent());
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageNews;
        TextView txtNewsTitle, txtNewsContent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageNews = itemView.findViewById(R.id.imageNews);
            txtNewsTitle = itemView.findViewById(R.id.txtNewsTitle);
            txtNewsContent = itemView.findViewById(R.id.txtNewsContent);
        }
    }
}
