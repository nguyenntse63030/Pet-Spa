package com.example.petspa_version_2.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petspa_version_2.Listener.List_Service_Item_Listener;
import com.example.petspa_version_2.Model.ServicePet;
import com.example.petspa_version_2.R;

import java.util.List;
/**
 * @author LongDong(04/06/2019)
 * */
public class ListServicePetFragmentAdapter extends RecyclerView.Adapter<ListServicePetFragmentAdapter.MyViewHolder> {
    private List<ServicePet> listServicePet;
    private Context context;
    private List_Service_Item_Listener list_service_item_listener;


    public ListServicePetFragmentAdapter(List<ServicePet> listServicePet, Context context, List_Service_Item_Listener list_service_item_listener) {
        this.listServicePet = listServicePet;
        this.context = context;
        this.list_service_item_listener = list_service_item_listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_service_pet_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.imageServicePet.setImageResource(listServicePet.get(position).getServiceImage());
        holder.txtServicePetTitle.setText(listServicePet.get(position).getServiceTitle());
        holder.txtServicePetDescription.setText(listServicePet.get(position).getServiceDescription());
        holder.txtServicePetPrice.setText(listServicePet.get(position).getServicePrice() + " đ");
        if (listServicePet.get(position).getServiceOldPrice() != null){
            holder.txtServicePetOldPrice.setText(listServicePet.get(position).getServiceOldPrice() + " đ");
            holder.txtServicePetOldPrice.setPaintFlags(holder.txtServicePetOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }else{
            holder.txtServicePetOldPrice.setText("");
        }
        if (listServicePet.get(position).getDiscountPercent() != null){
            holder.txtDiscountPercent.setText(listServicePet.get(position).getDiscountPercent());

        }else{
            holder.txtDiscountPercent.setText("");
        }
        holder.ratingBar.setRating(listServicePet.get(position).getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ServicePet service = listServicePet.get(position);

                list_service_item_listener.openBookingScreen(service);
            }
        });
    }
    public void filterList(List<ServicePet> filterList){
        listServicePet = filterList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listServicePet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageServicePet;
        TextView txtServicePetTitle;
        TextView txtServicePetDescription;
        TextView txtServicePetPrice;
        TextView txtServicePetOldPrice;
        TextView txtDiscountPercent;
        RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageServicePet = itemView.findViewById(R.id.imageServicePet);
            txtServicePetDescription = itemView.findViewById(R.id.txtServicePetDescription);
            txtServicePetPrice = itemView.findViewById(R.id.txtServicePetPrice);
            txtServicePetOldPrice = itemView.findViewById(R.id.txtServicePetOldPrice);
            txtServicePetTitle = itemView.findViewById(R.id.txtServicePetTitle);
            txtDiscountPercent = itemView.findViewById(R.id.txtDiscountPercent);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
