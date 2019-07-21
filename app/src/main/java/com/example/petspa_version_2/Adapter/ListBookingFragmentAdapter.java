package com.example.petspa_version_2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petspa_version_2.Listener.Booking_list_listener;
import com.example.petspa_version_2.Model.Booking;
import com.example.petspa_version_2.Model.ServicePet;
import com.example.petspa_version_2.R;

import java.util.List;

public class ListBookingFragmentAdapter extends RecyclerView.Adapter<ListBookingFragmentAdapter.MyViewHolder> {
    private List<Booking> listBooking;
    private Booking_list_listener mCallback;

    public ListBookingFragmentAdapter(List<Booking> listBooking, Booking_list_listener mCallback) {
        this.listBooking = listBooking;
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.getImageServiceBooking().setImageResource(listBooking.get(position).getImageServiceBooking());
        holder.getTxtBookingTitle().setText(listBooking.get(position).getService());
        holder.getTxtDateOfBooking().setText(listBooking.get(position).getDateBook());
        holder.getTxtBookingDesciption().setText("_Giờ hẹn sử dụng dịch vụ:\n"
                + "+ " + listBooking.get(position).getHour() + " giờ " + listBooking.get(position).getMinute() + " phút. \n" +
                "+ Ngày " + listBooking.get(position).getDay() + " ,tháng " + listBooking.get(position).getMonth() +
                ",năm " + listBooking.get(position).getYear() + "\n" +
                "+ Chi phí cho dịch vụ là: " + listBooking.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String service = listBooking.get(position).getService();
                String price = listBooking.get(position).getPrice();
                int imageServiceBooking = listBooking.get(position).getImageServiceBooking();
                String serviceDescription = listBooking.get(position).getServiceDescription();
                String serviceContent = listBooking.get(position).getServiceContent();

                ServicePet servicePet = new ServicePet(service, serviceDescription, serviceContent, imageServiceBooking, price);

                String day = listBooking.get(position).getDay();
                String month = listBooking.get(position).getMonth();
                String hour = listBooking.get(position).getHour();
                String minute = listBooking.get(position).getMinute();
                String year = listBooking.get(position).getYear();

                String oldBooking = listBooking.get(position).getOldBooking();
                int bookID = listBooking.get(position).getBookID();
                mCallback.onClickBookingItem(servicePet, day, month, year, hour, minute, oldBooking, bookID);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBooking.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageServiceBooking;
        private TextView txtBookingTitle, txtBookingDesciption, txtDateOfBooking;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageServiceBooking = itemView.findViewById(R.id.imageServiceBooking);
            txtBookingTitle = itemView.findViewById(R.id.txtBookingTitle);
            txtBookingDesciption = itemView.findViewById(R.id.txtBookingDesciption);
            txtDateOfBooking = itemView.findViewById(R.id.txtDateOfBooking);
        }

        public ImageView getImageServiceBooking() {
            return imageServiceBooking;
        }

        public TextView getTxtBookingTitle() {
            return txtBookingTitle;
        }

        public TextView getTxtBookingDesciption() {
            return txtBookingDesciption;
        }

        public TextView getTxtDateOfBooking() {
            return txtDateOfBooking;
        }
    }
}
