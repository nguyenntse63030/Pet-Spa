package com.example.petspa_version_2.Model;
/**
 * @author LongDong(06/06/2019)
 * */
public class News {
    String newsTitle;
    String newsContent;
    int newsImage;

    public News(String newsTitle, String newsContent, int newsImage) {
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.newsImage = newsImage;
    }

    public News(String newsTitle, int newsImage) {
        this.newsTitle = newsTitle;
        this.newsImage = newsImage;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public int getNewsImage() {
        return newsImage;
    }
}
