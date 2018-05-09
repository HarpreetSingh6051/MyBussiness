package com.harpreet.mybussiness.Views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.harpreet.mybussiness.Model.User;
import com.harpreet.mybussiness.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtEmail;
    EditText edtPasseord;

    Button btnSubmit;

    FirebaseAuth auth;
    User user;

    ProgressDialog progressDialog;

    void initViews(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPasseord = findViewById(R.id.edtPassword);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
        user = new User();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("--Please Wait--");
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    void loginUser(){
        progressDialog.show();
        auth.signInWithEmailAndPassword(user.email,user.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,user.email+" Login Sucess !! ",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this,ProfileActivity.class);
                            startActivity(intent);
                            finish();
                            progressDialog.dismiss();
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("User","User Login Failed: "+e.getMessage());
                e.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        user.email = edtEmail.getText().toString().trim();
        user.password = edtPasseord.getText().toString().trim();

        loginUser();
    }
}
