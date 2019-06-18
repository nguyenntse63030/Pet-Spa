package com.example.petspa_version_2.Model;

import java.io.Serializable;

public class Booking implements Serializable {
    String dateBook;
    String day;
    String month;
    String hour;
    String minute;
    String service;
    String price;
    int imageServiceBooking;

    public Booking( String dateBook, String day, String month, String hour, String minute, String service, String price, int imageServiceBooking) {
        this.dateBook = dateBook;
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.minute = minute;
        this.service = service;
        this.price = price;
        this.imageServiceBooking = imageServiceBooking;
    }

    public String getDateBook() {
        return dateBook;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public String getService() {
        return service;
    }

    public String getPrice() {
        return price;
    }

    public int getImageServiceBooking() {
        return imageServiceBooking;
    }
}
