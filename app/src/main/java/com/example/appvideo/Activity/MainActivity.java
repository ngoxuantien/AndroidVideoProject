package com.example.appvideo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.appvideo.Fragment.HomeFragment;
import com.example.appvideo.Fragment.MoreFragment;
import com.example.appvideo.Fragment.MovieFragment;
import com.example.appvideo.Fragment.Seting.UserFragment;
import com.example.appvideo.Fragment.TvShowFragment;
import com.example.appvideo.R;
import com.example.appvideo.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {
    BottomNavigationView bottomNavigationView;
    User user = new User();
    Bundle bundle = new Bundle();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser userfb = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.ALPHA_CHANGED, WindowManager.LayoutParams.ALPHA_CHANGED);
//        getSupportActionBar().hide();/// bỏ dòng title bên trên
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomnavigationview);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).commit();

      if(getIntent().getExtras().getInt("TrangthaiDN")==2){
          user=(User) getIntent().getExtras().getSerializable("UserObject");
           bundle.putSerializable("User", user);//// chuyền dữ liệu dùng bundle
          bundle.putInt("TrangthaiDN",1);

        }




    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.homeitem:
                            selectedFragment = new HomeFragment();

                            break;
                        case R.id.historyitem:
                            selectedFragment = new MovieFragment();
                            break;
                        case R.id.moviepageitem:
                            selectedFragment = new MovieFragment();
                            break;
                        case R.id.tvshowitem:
                            selectedFragment = new TvShowFragment();
                            break;
                        case R.id.moreitem:
                            selectedFragment = new MoreFragment();
                            selectedFragment.setArguments(bundle);//// chuyền dữ liệu vào fragment bằng bundle
                            break;
                        default:

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();

                    return true;
                }


            };
}