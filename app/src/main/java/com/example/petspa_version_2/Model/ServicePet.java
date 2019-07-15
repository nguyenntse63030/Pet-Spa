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
    float rating;



    public ServicePet(String serviceTitle, String serviceDescription, String serviceContent, int serviceImage, String servicePrice, float rating) {
        this.serviceTitle = serviceTitle;
        this.serviceDescription = serviceDescription;
        this.serviceContent = serviceContent;
        this.serviceImage = serviceImage;
        this.servicePrice = servicePrice;
        this.rating = rating;
    }
    public ServicePet(String serviceTitle, String serviceDescription, String serviceContent, int serviceImage, String servicePrice, float rating,String serviceOldPrice) {
        this.serviceTitle = serviceTitle;
        this.serviceDescription = serviceDescription;
        this.serviceContent = serviceContent;
        this.serviceImage = serviceImage;
        this.servicePrice = servicePrice;
        this.rating = rating;
        this.serviceOldPrice = serviceOldPrice;
    }

    public ServicePet(String serviceTitle, String serviceDescription, String serviceContent, int serviceImage, String servicePrice) {
        this.serviceTitle = serviceTitle;
        this.serviceDescription = serviceDescription;
        this.serviceContent = serviceContent;
        this.serviceImage = serviceImage;
        this.servicePrice = servicePrice;
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
}
