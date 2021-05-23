package com.example.appvideo.Session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.appvideo.model.User;

public class Session {
    private SharedPreferences sharedPreferences;
    private  Context context;

    public Session(Context context) {
        this.context = context;
    }

    public Session() {
    }
    public void setTypeUser(int k){



        sharedPreferences = context.getSharedPreferences("TypeUser",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("type",k);
        editor.commit();


    }
    public int getTypeUser(){
        sharedPreferences = context.getSharedPreferences("TypeUser",Context.MODE_PRIVATE);
        int k= sharedPreferences.getInt("type", 0);
        return k;


    }
    public void createSession( User user ){
      sharedPreferences = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id",user.getId());
        editor.putString("nameuser",user.getNameuser());
        editor.putString("emailuser",user.getEmailuser());
        editor.putString("dateofbirthuser",user.getDateofbirthuser());
        editor.putString("placeuser",user.getPlaceuser());
        editor.putString("genderuser",user.getGenderuser());
        editor.putString("password",user.getPassword());
        editor.commit();

    }
    public  User getSession(){

        sharedPreferences = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id",0);
        String nameuser = sharedPreferences.getString("nameuser",null);
        String emailuser = sharedPreferences.getString("emailuser",null);
        String dateofbirthuser = sharedPreferences.getString("dateofbirthuser",null);
        String placeuser= sharedPreferences.getString("placeuser",null);
        String genderuser = sharedPreferences.getString("genderuser",null);
        String password = sharedPreferences.getString("password",null);
        User user = new User(id,nameuser,emailuser,dateofbirthuser,placeuser,genderuser,password);
        return  user;

    }
    public  void updatePassword(String password){
        sharedPreferences = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password",password);
        editor.commit();

    }
    public  void clearSession(){
       sharedPreferences = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.clear();
        editor.apply();
    }
}
