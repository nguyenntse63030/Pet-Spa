package com.example.petspa_version_2.Listener;

import com.example.petspa_version_2.Model.Booking;
import com.example.petspa_version_2.Model.ServicePet;

public interface Booking_list_listener {
    public void onClickBookingItem(ServicePet servicePet, String day, String month,
                                   String year, String hour, String minute, String oldBooking, int bookID);
}
