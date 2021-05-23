package com.example.appvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.appvideo.Fragment.Seting.UserFragment;
import com.example.appvideo.R;
import com.example.appvideo.model.User;

import java.io.Serializable;

public class UserActivity extends AppCompatActivity implements Serializable {
    private User user = new User();
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Fragment selectedFragment= new UserFragment();
        //selectedFragment.setArguments(bundle);//// chuyền dữ liệu vào fragment bằng bundle
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutuser, selectedFragment).commit();
    }
}