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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.harpreet.mybussiness.Model.User;
import com.harpreet.mybussiness.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName;
    EditText edtEmail;
    EditText edtPasseord;

    Button btnSubmit;

    FirebaseAuth auth;
    User user;

    CollectionReference userCollection;
    FirebaseFirestore firebaseFirestore;

    String uid;
    ProgressDialog progressDialog;

    void initViews(){
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPasseord = findViewById(R.id.edtPassword);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        firebaseFirestore = FirebaseFirestore.getInstance();
        userCollection = firebaseFirestore.collection("users");

        user = new User();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(" Please Wait***");
        progressDialog.setCancelable(false);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }
    void saveUser() {

        userCollection.document(uid).set(user).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(RegisterActivity.this, "User Saved in DB", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this,ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error while saving User", Toast.LENGTH_LONG).show();
                    }
                });

    }
    void registerUser(){

        progressDialog.show();

        auth.createUserWithEmailAndPassword(user.email,user.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            uid = task.getResult().getUser().getUid();

                            Toast.makeText(RegisterActivity.this,user.name+" Registered Sucess !!",Toast.LENGTH_LONG).show();
                            Log.i("User","User Registered: "+uid);

                            saveUser();
                        }
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(this,new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("User","User Registration Failed: "+e.getMessage());
                e.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }
    @Override
    public void onClick(View view) {

        user.name = edtName.getText().toString().trim();
        user.email = edtEmail.getText().toString().trim();
        user.password = edtPasseord.getText().toString().trim();

        registerUser();
    }
}
