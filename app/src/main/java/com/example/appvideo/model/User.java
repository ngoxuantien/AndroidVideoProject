package com.example.appvideo.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String nameuser,emailuser,dateofbirthuser,placeuser,genderuser,password;

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String nameuser, String emailuser, String dateofbirthuser, String placeuser, String genderuser, String password) {
        this.id = id;
        this.nameuser = nameuser;
        this.emailuser = emailuser;
        this.dateofbirthuser = dateofbirthuser;
        this.placeuser = placeuser;
        this.genderuser = genderuser;
        this.password = password;
    }

    public User(int id, String nameuser, String emailuser, String dateofbirthuser, String placeuser, String genderuser) {
        this.id = id;
        this.nameuser = nameuser;
        this.emailuser = emailuser;
        this.dateofbirthuser = dateofbirthuser;
        this.placeuser = placeuser;
        this.genderuser = genderuser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public String getEmailuser() {
        return emailuser;
    }

    public void setEmailuser(String emailuser) {
        this.emailuser = emailuser;
    }

    public String getDateofbirthuser() {
        return dateofbirthuser;
    }

    public void setDateofbirthuser(String dateofbirthuser) {
        this.dateofbirthuser = dateofbirthuser;
    }

    public String getPlaceuser() {
        return placeuser;
    }

    public void setPlaceuser(String placeuser) {
        this.placeuser = placeuser;
    }

    public String getGenderuser() {
        return genderuser;
    }

    public void setGenderuser(String genderuser) {
        this.genderuser = genderuser;
    }
}
