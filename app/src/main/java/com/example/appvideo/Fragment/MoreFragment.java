package com.example.appvideo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appvideo.Activity.HistoryActivity;
import com.example.appvideo.Activity.HistorySearchActivity;
import com.example.appvideo.Activity.LoginActivity;
import com.example.appvideo.Activity.MainActivity;
import com.example.appvideo.Activity.UserActivity;
import com.example.appvideo.R;
import com.example.appvideo.Session.Session;
import com.example.appvideo.model.User;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MoreFragment extends Fragment {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser userfb = auth.getCurrentUser();
    private  Button button;
    private  RelativeLayout relativeLayout;
    private  TextView nameUsertv, emailUsertv;
    private  User user = new User();
    private  Bundle bundle = new Bundle();
    private Session session;
    private RelativeLayout historySearch,historyWatched;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);




        relativeLayout = view.findViewById(R.id.log_out);
        nameUsertv = view.findViewById(R.id.nameUsertv);
        emailUsertv = view.findViewById(R.id.gmailUsertv);
        button = (Button) view.findViewById(R.id.editusetbt);
        historySearch= view.findViewById(R.id.historySearch);
        historyWatched = view.findViewById(R.id.historyWatched);
        session = new Session(view.getContext());

        setOnClikcBtedit(view);

        if (session.getTypeUser() == 1) {
            //    user = (User) getArguments().getSerializable("User");//// lấy dữ liệu từ bundle
            user = session.getSession();
            nameUsertv.setText(user.getNameuser().toString());
            emailUsertv.setText(user.getEmailuser().toString());
        } else {
            ///// lấy dữ liệu từ facebook/////
            nameUsertv.setText(userfb.getDisplayName());
            emailUsertv.setText(userfb.getEmail());
        }

        setOnClicklogout(view);

        setOnClickhistorySearch(view);
        setOnClickHistoryWatched(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private void setOnClickHistoryWatched(View view) {
        historyWatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setOnClickhistorySearch(View view) {
        historySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), HistorySearchActivity.class);
                startActivity(intent);
            }
        });

    }


    private void setOnClicklogout(View view) {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                session.clearSession();
                startActivity(intent);

            }
        });
    }

    private void setOnClikcBtedit(View view) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), UserActivity.class);
                //   intent.putExtra("UserObject", user);//// chuyền dữ liệu

                // intent.putExtra("TrangthaiDN", 1);
                startActivity(intent);
            }
        });
    }


}
