package com.example.petspa_version_2.Model;

public class ServiceCardView {
    String serviceContent;
    int serviceImage;

    public ServiceCardView(String serviceContent, int serviceImage) {
        this.serviceContent = serviceContent;
        this.serviceImage = serviceImage;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public int getServiceImage() {
        return serviceImage;
    }
}
