package com.example.petspa_version_2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petspa_version_2.Listener.Card_View_Listener;
import com.example.petspa_version_2.Model.ServiceCardView;
import com.example.petspa_version_2.R;

import java.util.List;

public class ServiceCardViewFragmentAdapter extends RecyclerView.Adapter<ServiceCardViewFragmentAdapter.MyViewHolder>{
    private Card_View_Listener cardViewListener;
    private List<ServiceCardView> listServiceCard;
    private Context context;

    public ServiceCardViewFragmentAdapter(List<ServiceCardView> listServiceCard, Context context, Card_View_Listener cardViewListener) {
        this.cardViewListener = cardViewListener;
        this.listServiceCard = listServiceCard;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_service_item, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.txtServiceCardContent.setText(listServiceCard.get(position).getServiceContent());
        holder.imageServiceCard.setImageResource(listServiceCard.get(position).getServiceImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewListener.openServicePetList(listServiceCard.get(position).getServiceContent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listServiceCard.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageServiceCard;
        TextView txtServiceCardContent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageServiceCard = itemView.findViewById(R.id.imageServiceCardView);
            txtServiceCardContent = itemView.findViewById(R.id.txtServiceCardContent);
        }
    }
}
