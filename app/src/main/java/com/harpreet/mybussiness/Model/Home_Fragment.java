package com.harpreet.mybussiness.Model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.harpreet.mybussiness.R;

public class Home_Fragment extends Fragment implements View.OnClickListener{

    static View view;
    CardView cardBrown;
    CardView cardSilver;
    CardView cardGold;

   void  initViews(){

       cardBrown = cardBrown.findViewById(R.id.CardBrown);
       cardSilver = cardSilver.findViewById(R.id.CardSilver);
       cardGold = cardGold.findViewById(R.id.CardGold);

       cardBrown.setOnClickListener(this);
       cardSilver.setOnClickListener(this);
       cardGold.setOnClickListener(this);

   }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
       // initViews();
        return view;
    }

    @Override
    public void onClick(View v) {

        if(v == cardBrown){

            Toast.makeText(getContext(),"You Selected Brown Package",Toast.LENGTH_LONG).show();
        }if(v== cardSilver) {

            Toast.makeText(getContext(),"You Selected Silver Package",Toast.LENGTH_LONG).show();
        }if (v== cardGold){

            Toast.makeText(getContext(),"You Selected Gold Package",Toast.LENGTH_LONG).show();
        }
    }
}
