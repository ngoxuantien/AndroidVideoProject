package com.example.appvideo.model;

public class HistoryMovieWatched {
    private int id;
    private  String name, ImageUrl,review;

    public HistoryMovieWatched(int id, String name, String imageUrl, String review) {
        this.id = id;
        this.name = name;
        ImageUrl = imageUrl;
        this.review = review;
    }

    public HistoryMovieWatched() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
