package com.example.petspa_version_2.Model;

import java.io.Serializable;

public class Booking implements Serializable {
    private int bookID;
    private String dateBook;
    private String day;
    private String month;
    private String hour;
    private String minute;
    private String service;
    private String price;
    private int imageServiceBooking;
    private String year;
    private String serviceDescription;
    private String serviceContent;

    private String oldBooking;

//    public Booking( String dateBook, String day, String month, String hour,
//                    String minute, String service, String price,
//                    int imageServiceBooking, String year) {
//        this.dateBook = dateBook;
//        this.day = day;
//        this.month = month;
//        this.hour = hour;
//        this.minute = minute;
//        this.service = service;
//        this.price = price;
//        this.imageServiceBooking = imageServiceBooking;
//        this.year = year;
//    }

    public Booking(String dateBook, String day, String month, String hour, String minute,
                   String service, String price, int imageServiceBooking, String year,
                   String serviceDescription, String serviceContent, String oldBooking) {
        this.dateBook = dateBook;
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.minute = minute;
        this.service = service;
        this.price = price;
        this.imageServiceBooking = imageServiceBooking;
        this.year = year;
        this.serviceDescription = serviceDescription;
        this.serviceContent = serviceContent;
        this.oldBooking = oldBooking;
    }

    public Booking(int bookID, String dateBook, String day, String month, String hour, String minute,
                   String service, String price, int imageServiceBooking, String year,
                   String serviceDescription, String serviceContent) {
        this.bookID = bookID;
        this.dateBook = dateBook;
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.minute = minute;
        this.service = service;
        this.price = price;
        this.imageServiceBooking = imageServiceBooking;
        this.year = year;
        this.serviceDescription = serviceDescription;
        this.serviceContent = serviceContent;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public String getServiceContent() {
        return serviceContent;
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

    public String getYear() {
        return year;
    }

    public String getOldBooking() {
        return oldBooking;
    }

    public int getBookID() {
        return bookID;
    }
}
