package com.harpreet.mybussiness.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.harpreet.mybussiness.R;

public class EntersActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnNew;
    Button btnLogin;

    public void intiViews(){
        btnNew = findViewById(R.id.btnNew);
        btnLogin = findViewById(R.id.btnLogin);
        btnNew.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enters);
        intiViews();
    }

    @Override
    public void onClick(View v) {

        if(v == btnNew){

            Intent intent = new Intent(EntersActivity.this,RegisterActivity.class);
            startActivity(intent);
        }else {

            Intent intent = new Intent(EntersActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}