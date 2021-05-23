package com.example.appvideo.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appvideo.Activity.MainActivity;
import com.example.appvideo.FirebaseConection.FirebaseUser;
import com.example.appvideo.Fragment.Seting.UserFragment;
import com.example.appvideo.R;
import com.example.appvideo.Session.Session;
import com.example.appvideo.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.HashMap;

public class ChangePFragment extends Fragment implements Serializable {
    private EditText oldPassword, newPassword, retypePassword;
    private  Button changePassword;
    private  User user = new User();
    private  DatabaseReference databaseReference;
    private   Bundle bundle = new Bundle();
    private   AlertDialog.Builder b;
    private  Session session;
    private FirebaseUser firebaseUser;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_pw, container, false);
        firebaseUser = new FirebaseUser();
        oldPassword = view.findViewById(R.id.old_password);
        newPassword = view.findViewById(R.id.new_password);
        retypePassword = view.findViewById(R.id.retype_password);
        changePassword = view.findViewById(R.id.change_password);
        session = new Session(view.getContext());

        b = new AlertDialog.Builder(view.getContext());
        b.setTitle("Mật khẩu không đúng");

        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        user = session.getSession();
        //    user = (User) getArguments().getSerializable("user");

        changePassword(view);

        return view;


    }

    private void changePassword(View view) {
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldPassword.getText().toString().equals(user.getPassword())) {
                    if (newPassword.getText().toString().equals(retypePassword.getText().toString())) {
                        firebaseUser.updateUserPassword(newPassword.getText().toString(), user);// sửa đổi mật khẩu//
                        session.updatePassword(newPassword.getText().toString());

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutuser, new UserFragment()).commit();
                    } else
                        Toast.makeText(view.getContext(), "Mật khẩu mới nhập không khớp mời nhập lại", Toast.LENGTH_SHORT);

                } else {

                    AlertDialog al = b.create();
//Hiển thị
                    al.show();
                }

            }
        });
    }
}
