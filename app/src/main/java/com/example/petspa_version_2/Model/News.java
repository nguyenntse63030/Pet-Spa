package com.example.petspa_version_2.Model;
/**
 * @author LongDong(06/06/2019)
 * */
public class News {
    String newsTitle;
    String newsContent;
    int newsImage;
    String dateOfNews;
    String newsContentDetail;

    public News(String newsTitle, String newsContent, int newsImage, String dateOfNews) {
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.newsImage = newsImage;
        this.dateOfNews = dateOfNews;
    }

    public News(String newsTitle, String newsContent, int newsImage, String dateOfNews, String newsContentDetail) {
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.newsImage = newsImage;
        this.dateOfNews = dateOfNews;
        this.newsContentDetail = newsContentDetail;
    }

    public News(String newsTitle, int newsImage, String dateOfNews, String newsContentDetail) {
        this.newsTitle = newsTitle;
        this.newsImage = newsImage;
        this.dateOfNews = dateOfNews;
        this.newsContentDetail = newsContentDetail;
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

    public String getDateOfNews() {
        return dateOfNews;
    }

    public String getNewsContentDetail() {
        return newsContentDetail;
    }
}
