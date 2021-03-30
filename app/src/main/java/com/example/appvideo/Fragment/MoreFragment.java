package com.example.appvideo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appvideo.Activity.LoginActivity;
import com.example.appvideo.Activity.UserActivity;
import com.example.appvideo.R;
import com.example.appvideo.model.User;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MoreFragment extends Fragment {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser userfb = auth.getCurrentUser();
    Button button;
    RelativeLayout relativeLayout;
    TextView nameUsertv, emailUsertv;
    User user = new User();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        button = view.findViewById(R.id.editusetbt);
        relativeLayout = view.findViewById(R.id.log_out);
        nameUsertv = view.findViewById(R.id.nameUsertv);
        emailUsertv = view.findViewById(R.id.gmailUsertv);


        if (getArguments().getInt("TrangthaiDN") == 1) {
            user = (User) getArguments().getSerializable("User");//// lấy dữ liệu từ bundle
            nameUsertv.setText(user.getNameuser().toString());
            emailUsertv.setText(user.getEmailuser().toString());
        } else {
            ///// lấy dữ liệu từ facebook/////
            nameUsertv.setText(userfb.getDisplayName());
            emailUsertv.setText(userfb.getEmail());
        }


        setOnClikcBtedit(view);
        setOnClicklogout(view);
        return view;


    }


    private void setOnClicklogout(View view) {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                //if(getArguments().getInt("TrangthaiDN")==1){


                auth.signOut();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);


//                }else {
//                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
//                    startActivity(intent);
//                }


            }
        });
    }

    private void setOnClikcBtedit(View view) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), UserActivity.class);
                startActivity(intent);
            }
        });
    }


}
