package com.harpreet.mybussiness.Views;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.harpreet.mybussiness.R;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = auth.getCurrentUser();
        if(firebaseUser != null){
            handler.sendEmptyMessageDelayed(201,2500);
        }else{
            handler.sendEmptyMessageDelayed(101,2500);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            Intent intent = null ;

            if(msg.what==101){
                intent = new Intent(SplashActivity.this,LoginActivity.class);
            }else {
                intent = new Intent(SplashActivity.this,ProfileActivity.class);
            }
            startActivity(intent);
            finish();
        }
    };
}
