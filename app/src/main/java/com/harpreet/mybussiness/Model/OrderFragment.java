package com.harpreet.mybussiness.Model;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.harpreet.mybussiness.R;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class OrderFragment extends Fragment implements View.OnClickListener {

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    String uid;

    EditText edtWeightCloth;
    EditText edtNoCloth;
    EditText edtAddress;
    EditText edtPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        uid = auth.getCurrentUser().getUid();

        View view = inflater.inflate(R.layout.fragment_order, container, false);
        edtWeightCloth = (EditText) view.findViewById(R.id.edtWeightCloth);
        edtNoCloth = (EditText) view.findViewById(R.id.edtClothNumber);
        edtAddress = (EditText) view.findViewById(R.id.edtAddress);
        edtPhone = (EditText) view.findViewById(R.id.edtPhone);
        Button btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);


        return view;

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSubmit:

                Toast.makeText(getContext(), "You Clicked On Submit", Toast.LENGTH_LONG).show();
                CollectionReference collectionReference1 = firestore.collection("users").document(uid).collection("oreders");

                //Address a1 = new Address("2144", "B20 KN", "Ludhiana", "Punjab", 141002);

                Order order = new Order();
                order.WeightCloth = Integer.valueOf(edtWeightCloth.getText().toString().trim());
                order.NOCloth = Integer.valueOf(edtNoCloth.getText().toString().trim());
                order.Address = edtAddress.getText().toString().trim();
                order.Phone = Double.valueOf(edtPhone.getText().toString().trim());


                collectionReference1.add(order).addOnSuccessListener((Activity) getContext(), new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String aid = documentReference.getId();
                        Log.i("User", "Orders Created with ID " + aid);
                    }
                });
                final ArrayList<User> userList = new ArrayList<>();

                Query query = firestore.collection("users").orderBy("name", Query.Direction.ASCENDING);

                query.addSnapshotListener((Activity) getContext(), new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        for(DocumentSnapshot documentSnapshot : documentSnapshots){
                            User user = documentSnapshot.toObject(User.class);
                            userList.add(user);
                            Toast.makeText(getContext(),"Oreder Sucess",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                edtWeightCloth.setText("");
                edtAddress.setText("");
                edtNoCloth.setText("");
                edtPhone.setText("");
                break;
        }
    }
}
