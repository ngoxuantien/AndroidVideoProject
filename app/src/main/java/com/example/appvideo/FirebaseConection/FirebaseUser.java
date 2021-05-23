package com.example.appvideo.FirebaseConection;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.appvideo.Activity.LoginActivity;
import com.example.appvideo.Activity.SignUpActivity;
import com.example.appvideo.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FirebaseUser {
    private DatabaseReference databaseReference;

    public FirebaseUser() {

    }

    /// hàm tạo 1 lớp đại diện là users chứa các list các tài khoản///
    public void createUserObject() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
    }

    //// hàm thêm tài khoản//
    public void addUserObject(User user) {
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        //   new User(d, nameSigUp.getText().toString(), emailSigUp.getText().toString(), "không có dữ liệu", "không có dữ liệu", "không có dữ liệu", passwordSigUp.getText().toString())
        databaseReference.child(user.getId() + "").setValue(user);


    }

    /// hàm cập nhật tài khoản///
    public void updateUserObject(User user) {
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        HashMap postValues = new HashMap<>();
        postValues.put("dateofbirthuser", user.getDateofbirthuser());
        postValues.put("emailuser", user.getEmailuser());
        postValues.put("genderuser", user.getGenderuser());
        //   postValues.put("id",user.getId()+"");
        postValues.put("nameuser", user.getNameuser());
        //  postValues.put("password",user.getPassword());
        postValues.put("placeuser", user.getPlaceuser());

        databaseReference.child(user.getId() + "").updateChildren(postValues).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {

            }
        });

    }

    //// hàm sửa mật khẩu//
    public void updateUserPassword(String password, User user) {
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        HashMap postValues = new HashMap<>();
        postValues.put("password", password);
        databaseReference.child(user.getId() + "").updateChildren(postValues).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                //   Toast.makeText(view.getContext(), "thanh cong", Toast.LENGTH_SHORT);
            }
        });
    }


}
