package com.example.appvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appvideo.Fragment.Seting.UserFragment;
import com.example.appvideo.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutuser, new  UserFragment()).commit();
    }
}