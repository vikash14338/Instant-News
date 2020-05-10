package com.example.instantnews;

public class NewsModel {
    String imageUrl;
    String content;



    String title;
    String contentUrl;
    public NewsModel(){}

    public NewsModel(String imageUrl, String content, String title, String contentUrl) {
        this.imageUrl = imageUrl;
        this.content = content;
        this.title = title;
        this.contentUrl = contentUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
