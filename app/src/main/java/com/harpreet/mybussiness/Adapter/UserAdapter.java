package com.harpreet.mybussiness.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.harpreet.mybussiness.R;
import com.harpreet.mybussiness.Model.User;

import java.util.ArrayList;

    /*
    public class UserAdapter extends ArrayAdapter<UserAdapter> {

        Context context;
        int resource;
        ArrayList<UserAdapter> objects;

        public UserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<UserAddapter> objects) {
            super(context, resource, objects);

            this.context = context;
            this.resource = resource;
            this.objects = objects;
        }*/

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    Context context;
    int resource;
    ArrayList<User> objects;

    public UserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {

        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resource, parent, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        User user = objects.get(position);

        holder.txtName.setText(user.name);
        holder.txtEmail.setText(user.email);
        holder.txtPassword.setText(user.password);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtEmail;
        TextView txtPassword;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.textViewName);
            txtEmail = itemView.findViewById(R.id.textViewEmail);
            txtPassword = itemView.findViewById(R.id.textViewPassword);
        }
    }
}
