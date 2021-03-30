package com.example.appvideo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appvideo.Database.DBManager;
import com.example.appvideo.JavaMailAPI;
import com.example.appvideo.R;
import com.example.appvideo.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.royrodriguez.transitionbutton.TransitionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PasswordRetrievalActivity extends AppCompatActivity {
    EditText etTo, nameUserForgot;
    private TransitionButton btSend;
    DBManager dbManager;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private User userlogin = new User();
    private boolean valueLogin = false;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_retrieval);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        dbManager = new DBManager(PasswordRetrievalActivity.this);
        etTo = findViewById(R.id.et_to);
        nameUserForgot = findViewById(R.id.nameUserForgot);


        setBtSend();


    }

    private void setBtSend() {
        btSend = findViewById(R.id.bt_send);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin();


                btSend.startAnimation();

                // Do your networking task or background work here.
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        // Choose a stop animation if your call was succesful or not
                        if (valueLogin) {

                            sendEmail(userlogin.getPassword().toString());

                            Toast.makeText(PasswordRetrievalActivity.this, "Đã gửi mật khẩu đến Gmail:" + etTo.getText().toString(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PasswordRetrievalActivity.this, LoginActivity.class);
                            startActivity(intent);
                            valueLogin = false;
                        } else {
                            Toast.makeText(PasswordRetrievalActivity.this, "Mật khẩu hoặc email không chính xác !", Toast.LENGTH_SHORT).show();
                            btSend.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }
                    }
                }, 1000);
            }


        });
    }


    private void sendEmail(String message) {
        String email = etTo.getText().toString();

        //   = etMessage.getText().toString();
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, email, "Gửi lại mật khẩu", message);
        javaMailAPI.execute();

    }

    private void setLogin() {

        //    List<User> listfriebase = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot userdataSnapshot : snapshot.getChildren()) {
                    User user = userdataSnapshot.getValue(User.class);

                    if (nameUserForgot.getText().toString().equals(user.getNameuser()) && etTo.getText().toString().equals(user.getEmailuser())) {
                        valueLogin = true;
                        userlogin = user;
                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}