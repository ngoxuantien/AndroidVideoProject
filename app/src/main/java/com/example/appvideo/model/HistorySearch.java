package com.example.appvideo.model;

public class HistorySearch {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HistorySearch(int id, String title) {
        this.id = id;
        this.title = title;
    }

    private String title;

    public HistorySearch() {
    }

    public HistorySearch(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
