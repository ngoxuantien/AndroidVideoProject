package com.example.appvideo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.appvideo.Database.DBManager;
import com.example.appvideo.R;
import com.example.appvideo.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.royrodriguez.transitionbutton.TransitionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends Activity implements Serializable {
    private TransitionButton loginBt;
    private EditText usernametv, passwordtv;
    private TextView forgotpasswordtv, toSignUp;
    private DBManager dbManager;
    private DatabaseReference databaseReference;
    private static final String TAG = "FBAUTH";
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private User userlogin = new User();
    private boolean valueLogin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        forgotpasswordtv = findViewById(R.id.forgotpasswordtv);
        toSignUp = findViewById(R.id.toSignUp);
        usernametv = findViewById(R.id.usernamtv);
        passwordtv = findViewById(R.id.passwordtv);
        dbManager = new DBManager(LoginActivity.this);


        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        callbackManager = CallbackManager.Factory.create();

        setToSignUp();
        setForgotpasswordtv();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        // loginButton.setFragment(LoginActivity.this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d(TAG, "onError" + exception.getMessage());
            }
        });

        setOnClick();
    }

    private void setToSignUp() {
        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    //// liên quan tới đăng nhập bằng facebook/////////////
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            //    FirebaseUser user = auth.getCurrentUser();
                            openProfile();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void openProfile() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TrangthaiDN", 1);
        startActivity(intent);
        finish();
    }

    ////// chú ý phần code bên dưới ///// xem lại
    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            openProfile();
        }
    }

    ///// set sự kiện khi Click button
    public void setOnClick() {
        loginBt = findViewById(R.id.login);
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setLogin(); /// gọi để kiểm tra đăng nhập nếu có thì sẽ gửi đi


                ///// chú ý thêm toString vào khi getText
                // Start the loading animation when the user tap the button
                loginBt.startAnimation();

                // Do your networking task or background work here.
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        // Choose a stop animation if your call was succesful or not
                        if (valueLogin) {

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("TrangthaiDN", 2);
                            intent.putExtra("UserObject", userlogin); /// khi muốn chuyển object qua Intent hoặc Bundel thì phải implemnet hết cả lớp class và lớp main cho chắc
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                            valueLogin = false;
                        } else {
                            loginBt.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }
                    }
                }, 1000);
            }

        });
    }

    //////// hàm kiểm tra mật khẩu trên friebase và gán giá trị cho userLogin và valueLogin////////
    private void setLogin() {

        //    List<User> listfriebase = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot userdataSnapshot : snapshot.getChildren()) {
                    User user = userdataSnapshot.getValue(User.class);

                    if (usernametv.getText().toString().equals(user.getNameuser()) && passwordtv.getText().toString().equals(user.getPassword())) {

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


    //// set nut chuyển sang màn hình lấy lại mật khẩu/////
    private void setForgotpasswordtv() {
        forgotpasswordtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PasswordRetrievalActivity.class);
                startActivity(intent);
            }
        });
    }
}