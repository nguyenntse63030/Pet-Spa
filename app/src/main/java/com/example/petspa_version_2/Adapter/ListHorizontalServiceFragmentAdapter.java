package com.example.petspa_version_2.Adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petspa_version_2.Listener.List_Service_Card_Item_Listener;
import com.example.petspa_version_2.Model.ServicePet;
import com.example.petspa_version_2.R;

import java.util.List;

public class ListHorizontalServiceFragmentAdapter extends RecyclerView.Adapter<ListHorizontalServiceFragmentAdapter.MyViewHolder> {
    private List<ServicePet> list;
    private List_Service_Card_Item_Listener listener;

    public ListHorizontalServiceFragmentAdapter(List<ServicePet> list, List_Service_Card_Item_Listener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service_cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.imageServiceCardView.setImageResource(list.get(position).getServiceImage());
        holder.txtServiceCardContent.setText(list.get(position).getServiceTitle());
        holder.txtServiceCardPrice.setText(list.get(position).getServicePrice()+" đ");
        if (list.get(position).getServiceOldPrice() != null  ){
            holder.txtServiceCardOldPrice.setText(list.get(position).getServiceOldPrice()+" đ");
            holder.txtServiceCardOldPrice.setPaintFlags(holder.txtServiceCardPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            holder.txtServiceCardOldPrice.setText("");

        }
        if (list.get(position).getDiscountPercent() != null  ){
            holder.txtDiscount.setText(list.get(position).getDiscountPercent());
        }else {
            holder.txtDiscount.setText("");

        }
        holder.ratingBar.setRating(list.get(position).getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ServicePet service =list.get(position);

                listener.viewService(service);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageServiceCardView;
        TextView txtServiceCardContent;
        TextView txtServiceCardPrice;
        TextView txtServiceCardOldPrice;
        TextView txtDiscount;
        RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageServiceCardView = itemView.findViewById(R.id.imageServiceCardView);
            txtServiceCardContent = itemView.findViewById(R.id.txtServiceCardContent);
            txtServiceCardPrice = itemView.findViewById(R.id.txtServiceCardPrice);
            txtServiceCardOldPrice = itemView.findViewById(R.id.txtServiceCardOldPrice);
            txtDiscount = itemView.findViewById(R.id.txtDiscount);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
