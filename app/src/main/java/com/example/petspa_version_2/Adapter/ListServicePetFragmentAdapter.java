package com.example.petspa_version_2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        holder.txtServicePetPrice.setText(listServicePet.get(position).getServicePrice() + " VND");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceTitle = listServicePet.get(position).getServiceTitle();
                String serviceDescription = listServicePet.get(position).getServiceDescription();
                String serviceContent = listServicePet.get(position).getServiceContent();
                int serviceImage = listServicePet.get(position).getServiceImage();
                String servicePrice = listServicePet.get(position).getServicePrice();

                ServicePet service = new ServicePet(serviceTitle, serviceDescription, serviceContent, serviceImage, servicePrice);

                list_service_item_listener.openBookingScreen(service);
            }
        });
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageServicePet = itemView.findViewById(R.id.imageServicePet);
            txtServicePetDescription = itemView.findViewById(R.id.txtServicePetDescription);
            txtServicePetPrice = itemView.findViewById(R.id.txtServicePetPrice);
            txtServicePetTitle = itemView.findViewById(R.id.txtServicePetTitle);
        }
    }
}
