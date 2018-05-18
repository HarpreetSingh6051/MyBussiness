package com.harpreet.mybussiness.Model;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.harpreet.mybussiness.Adapter.UserAdapter;
import com.harpreet.mybussiness.R;
import com.harpreet.mybussiness.Views.ProfileActivity;

import java.util.ArrayList;
import java.util.UUID;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    FirebaseAuth auth;
    User user = new User();

    FirebaseFirestore firestore;
    FirebaseStorage storage;
    StorageReference storageReference;

    String uid ;
    Uri uri;

    ImageView imageView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        final TextView txtName = (TextView) view.findViewById(R.id.txtName);
        final TextView txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        imageView = (ImageView) view.findViewById(R.id.Profile);
        TextView txtAddress = (TextView) view.findViewById(R.id.txtAddress);
        Button btnLog = (Button) view.findViewById(R.id.btnSubmit);
        Button btnUpload = (Button) view.findViewById(R.id.profileImage);
        btnUpload.setOnClickListener(this);

        firestore = FirebaseFirestore.getInstance();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        auth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = auth.getCurrentUser();
        uid = firebaseUser.getUid();
        Toast.makeText(getContext(), "UID " + uid, Toast.LENGTH_SHORT).show();
        DocumentReference documentReference = firestore.collection("users").document(uid);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {
                    txtName.setText(documentSnapshot.get("name").toString());
                    txtEmail.setText(firebaseUser.getEmail());
                }
                else
                {
                    Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();

        if(id == R.id.profileImage)
        {
            Toast.makeText(getContext(), "Please Upload Image", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");

            //Toast.makeText(this, "" + getPackageManager(), Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, 101);

        }

        Toast.makeText(getContext(),"You Clicked LogOut",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101 && data != null && data.getData() != null)
        {
            uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
                uploadImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    private void uploadImage() {

        if(uri != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child(UUID.randomUUID().toString());
            ref.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}

