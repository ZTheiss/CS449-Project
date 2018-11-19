package com.peery.android.projectscorpion;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.peery.android.projectscorpion.R;
import com.peery.android.projectscorpion.User;

import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.AppCompatTextView;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {
    private List<User> Users;

    public UsersRecyclerAdapter(List<User> listusers){
        this.Users = listusers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_recycler, parent, false);
        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position){
        holder.viewTextName.setText(Users.get(position).getUserName());
        holder.viewTextEmail.setText(Users.get(position).getEmail());
        holder.viewTextPassword.setText(Users.get(position).getPassword());
    }

    @Override
    public int getItemCount(){
        Log.v(UsersRecyclerAdapter.class.getSimpleName(), ""+Users.size());
        return Users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        public AppCompatTextView viewTextName;
        public AppCompatTextView viewTextEmail;
        public AppCompatTextView viewTextPassword;

        public UserViewHolder(View view){
            super(view);
            viewTextName = (AppCompatTextView) view.findViewById(R.id.viewTextName);
            viewTextEmail = (AppCompatTextView) view.findViewById(R.id.viewTextEmail);
            viewTextPassword = (AppCompatTextView) view.findViewById(R.id.viewTextPassword);
        }
    }
}
