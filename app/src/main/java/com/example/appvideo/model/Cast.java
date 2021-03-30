package com.example.appvideo.model;

public class Cast {
    private int id;
    private String name, date,place,sex,imagecast,story;
    public Cast(int id, String name, String date, String place, String sex, String imagecast, String story) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.place = place;
        this.sex = sex;
        this.imagecast = imagecast;
        this.story = story;
    }

    public Cast(int id, String name, String imagecast) {
        this.id = id;
        this.name = name;
        this.imagecast = imagecast;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImagecast() {
        return imagecast;
    }

    public void setImagecast(String imagecast) {
        this.imagecast = imagecast;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
