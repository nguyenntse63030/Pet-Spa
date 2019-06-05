package com.example.petspa_version_2.Model;

public class ServicePet {
    String serviceTitle;
    String serviceContent;
    int serviceImage;
    int servicePrice;

    public ServicePet(String serviceTitle, String serviceContent, int serviceImage, int servicePrice) {
        this.serviceTitle = serviceTitle;
        this.serviceContent = serviceContent;
        this.serviceImage = serviceImage;
        this.servicePrice = servicePrice;
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

    public int getServicePrice() {
        return servicePrice;
    }
}
