package com.example.appvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appvideo.Database.DBManager;
import com.example.appvideo.FirebaseConection.FirebaseUser;
import com.example.appvideo.R;
import com.example.appvideo.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private Button buttonLogUp;
    private DBManager dbManager;
    private EditText nameSigUp, emailSigUp, passwordSigUp, retypepassword;
    private CheckBox checkBox;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseUser = new FirebaseUser();
        buttonLogUp = findViewById(R.id.signUpUser);
        nameSigUp = findViewById(R.id.namesigup);
        emailSigUp = findViewById(R.id.emailsigup);
        passwordSigUp = findViewById(R.id.passwordSigup);
        checkBox = findViewById(R.id.check);
        retypepassword = findViewById(R.id.retype_password);
        firebaseUser.createUserObject();



        dbManager = new DBManager(SignUpActivity.this);
        setOnclickBt();


    }



    private void ShowDialog(String km) {
        Button bt1;
        TextView tv1, tv2;
        dialog = new Dialog(SignUpActivity.this);
        dialog.setContentView(R.layout.dialog_notification_error);// khởi tạo view cho dialog
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);// set thông báo ở trung tâm
        //  window.getAttributes().windowAnimations= R.style.DialogAnimation;
        bt1 = dialog.findViewById(R.id.button_dialog);
        tv1 = dialog.findViewById(R.id.titleDialog);
        tv2 = dialog.findViewById(R.id.error_text);
        tv2.setText(km.toString());

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);// tìm hiểu thêm
        dialog.show();

    }

    private void setOnclickBt() {
        buttonLogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();
                int d = random.nextInt(99999);

             //   User user = new User(d, nameSigUp.getText().toString(), emailSigUp.getText().toString(), "không có dữ liệu", "không có dữ liệu", "không có dữ liệu", passwordSigUp.getText().toString());
//
                if (checkBox.isChecked()) {/// check đồng ý
                    if (nameSigUp.getText().toString().equals("")==true || emailSigUp.getText().toString().equals("")==true){/// check ten đăng nhập và mật khẩu

                        ShowDialog("Không được bỏ trống tên hoặc email");

                        } else {
                        if (retypepassword.getText().toString().equals(passwordSigUp.getText().toString())) {/// check xem có trùng mật khẩu không

                        User  user = new User(d, nameSigUp.getText().toString(), emailSigUp.getText().toString(), "không có dữ liệu", "không có dữ liệu", "không có dữ liệu", passwordSigUp.getText().toString());
                            firebaseUser.addUserObject(user);


                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);

                        }else{
                            ShowDialog("Mật khẩu nhập lại không trùng với nhau");
                        }

                    }
                } else {
                    ShowDialog("Chưa tích đồng ý điều khoản");
                }
//                //// lưu dữ liệu user vào fribase//////





            }
        });
    }
}