package com.example.appvideo.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.appvideo.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class testshare extends AppCompatActivity {
    ShareButton btlink;
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_testshare);
      //  btlink=findViewById(R.id.sharelink);
        callbackManager= CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        printKeyHash();

        if(shareDialog.canShow(ShareLinkContent.class)){
            ShareLinkContent shareLinkContent= new ShareLinkContent.Builder().setContentUrl(Uri.parse("https://www.youtube.com/watch?v=10idaKgAhrQ")).setShareHashtag(new ShareHashtag.Builder().setHashtag("#WittCode").build())
                    .build();
            shareDialog.show(shareLinkContent);
          //  btlink.setShareContent(shareLinkContent);
        }




    }
    private void printKeyHash(){
        try {
            PackageInfo info =getPackageManager().getPackageInfo("com.example.appvideo", PackageManager.GET_SIGNATURES);
            for(Signature signature:info.signatures){
                MessageDigest md =MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

    }
}