package com.harpreet.mybussiness.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.harpreet.mybussiness.R;
import com.harpreet.mybussiness.Model.User;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends ArrayAdapter<User> {


    Context context;
    int resource;
    ArrayList<User> objects;

    public UserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(resource, parent, false);

        TextView txtName = view.findViewById(R.id.txtName);

        User user = objects.get(position);

        txtName.setText(user.name);


        return view;
    }
}

