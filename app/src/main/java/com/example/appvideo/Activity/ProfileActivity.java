package com.example.appvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appvideo.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
//private FirebaseAuth auth = FirebaseAuth.getInstance();
//private FirebaseUser user = auth.getCurrentUser();
//private TextView name,email;
//private Button button;
//private ImageView imageImage;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//        name = findViewById(R.id.name);
//        email=findViewById(R.id.email);
//        imageImage= findViewById(R.id.imagetest);
//
//        if(user!=null){
//            name.setText(user.getDisplayName());
//            email.setText(user.getEmail());
//
//
//
//        }
//        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                auth.signOut();
//                LoginManager.getInstance().logOut();
//                openLogin();
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(user==null){
//            openLogin();
//        }
//    }
//
//    private void openLogin() {
//        startActivity(new Intent(this,LoginActivity.class));
//    finish();
//    }
}