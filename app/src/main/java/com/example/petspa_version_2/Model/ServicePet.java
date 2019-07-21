package com.example.petspa_version_2.Model;

import java.io.Serializable;

/**
 * @author LongDong(04/06/2019)
 * */
public class ServicePet implements Serializable {
    String serviceTitle;
    String serviceDescription;
    String serviceContent;
    int serviceImage;
    String servicePrice;
    String serviceOldPrice;
    String discountPercent;
    float rating;
    String distance;



    public ServicePet(String serviceTitle, String serviceDescription, String serviceContent, int serviceImage, String servicePrice, float rating) {
        this.serviceTitle = serviceTitle;
        this.serviceDescription = serviceDescription;
        this.serviceContent = serviceContent;
        this.serviceImage = serviceImage;
        this.servicePrice = servicePrice;
        this.rating = rating;
    }
    public ServicePet(String serviceTitle, String serviceDescription, String serviceContent, int serviceImage, String servicePrice, float rating,String distance) {
        this.serviceTitle = serviceTitle;
        this.serviceDescription = serviceDescription;
        this.serviceContent = serviceContent;
        this.serviceImage = serviceImage;
        this.servicePrice = servicePrice;
        this.rating = rating;
        this.distance = distance;
    }
    public ServicePet(String serviceTitle, String serviceDescription, String serviceContent, int serviceImage, String servicePrice, float rating,String serviceOldPrice,String discountPercent) {
        this.serviceTitle = serviceTitle;
        this.serviceDescription = serviceDescription;
        this.serviceContent = serviceContent;
        this.serviceImage = serviceImage;
        this.servicePrice = servicePrice;
        this.rating = rating;
        this.serviceOldPrice = serviceOldPrice;
        this.discountPercent = discountPercent;
    }
    public ServicePet(String serviceTitle, String serviceDescription, String serviceContent, int serviceImage, String servicePrice, float rating,String serviceOldPrice,String discountPercent,String distance) {
        this.serviceTitle = serviceTitle;
        this.serviceDescription = serviceDescription;
        this.serviceContent = serviceContent;
        this.serviceImage = serviceImage;
        this.servicePrice = servicePrice;
        this.rating = rating;
        this.serviceOldPrice = serviceOldPrice;
        this.discountPercent = discountPercent;
        this.distance = distance;
    }

    public ServicePet(String serviceTitle, String serviceDescription, String serviceContent, int serviceImage, String servicePrice) {
        this.serviceTitle = serviceTitle;
        this.serviceDescription = serviceDescription;
        this.serviceContent = serviceContent;
        this.serviceImage = serviceImage;
        this.servicePrice = servicePrice;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public String getServiceOldPrice() {
        return serviceOldPrice;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public int getServiceImage() {
        return serviceImage;
    }


    public String getServicePrice() {
        return servicePrice;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public float getRating() {
        return rating;
    }

    public String getDistance() {
        return distance;
    }
}
