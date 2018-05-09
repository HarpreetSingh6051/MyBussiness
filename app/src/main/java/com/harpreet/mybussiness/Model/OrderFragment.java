package com.harpreet.mybussiness.Model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.harpreet.mybussiness.R;

public class OrderFragment extends Fragment implements View.OnClickListener {

    EditText edtNoCloth;
    EditText edtWeightCloth;
    EditText edtAddress;
    EditText edtPhone;
    Button btnSubmit;

    void initViews(){
        edtNoCloth = edtNoCloth.findViewById(R.id.edtClothNumber);
        edtWeightCloth = edtWeightCloth.findViewById(R.id.edtWeightCloth);
        edtAddress = edtAddress.findViewById(R.id.edtAddress);
        edtPhone = edtPhone.findViewById(R.id.edtPhone);
        btnSubmit = btnSubmit.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initViews();
        return inflater.inflate(R.layout.fragment_order,null);

    }

    @Override
    public void onClick(View v) {

        Toast.makeText(getContext(),"You Clicked On Submit",Toast.LENGTH_LONG).show();
    }
}
