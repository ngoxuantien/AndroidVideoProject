package com.example.appvideo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.appvideo.model.User;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    public DBManager(@Nullable Context context) {
        super(context, "DatabaseUser.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUser = "create table User(id int primary key, password varchar(20), nameuser varchar(20),emailuser varchar(20),dateofbirthuser varchar(20),placeuser varchar(20),genderuser varchar(20))";
        db.execSQL(createUser);
//// chú ý id chỉ duy nhất khi gặp lỗi ở đây nên xem lại giá trị gán vào biến id///////

    }

    public void addUser(User user) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", user.getId());
        contentValues.put("password", user.getPassword());
        contentValues.put("nameuser", user.getNameuser());
        contentValues.put("emailuser", user.getEmailuser());
        contentValues.put("dateofbirthuser", user.getDateofbirthuser());
        contentValues.put("placeuser", user.getPlaceuser());
        contentValues.put("genderuser", user.getGenderuser());

        database.insert("User", null, contentValues);
    }


    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        String querySql = " select * from User";
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(querySql, null);


        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setPassword(cursor.getString(1));
                user.setNameuser(cursor.getString(2));
                user.setEmailuser(cursor.getString(3));
                user.setDateofbirthuser(cursor.getString(4));
                user.setPlaceuser(cursor.getString(5));
                user.setGenderuser(cursor.getString(6));
userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return userList;


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
