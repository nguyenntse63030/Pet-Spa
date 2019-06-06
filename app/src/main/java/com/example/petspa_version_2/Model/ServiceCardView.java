package com.example.petspa_version_2.Model;
/**
 * @author LongDong(04/06/2019)
 * */
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
