package com.example.appvideo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.appvideo.R;

public class SignInSignUpActivity extends Activity {
Button signInBt,signUpBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_sign_in_sign_up);

        setOnClick();
        setOnClickSignUp();
    }

    private void setOnClickSignUp() {
        signUpBt = findViewById(R.id.signUp);
        signUpBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignInSignUpActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });
    }

    public void setOnClick(){
        signInBt = findViewById(R.id.signIn);
        signInBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInSignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}