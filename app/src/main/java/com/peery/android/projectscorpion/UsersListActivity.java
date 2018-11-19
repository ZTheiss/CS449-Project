package com.peery.android.projectscorpion;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UsersListActivity extends AppCompatActivity{

    private AppCompatActivity activity = UsersListActivity.this;
    private AppCompatTextView viewTextName;
    private RecyclerView recyclerViewUsers;
    private List<User> users;
    private UsersRecyclerAdapter usersRecycler;
    private SQLiteDbHelper DBhelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_users_list);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();
    }

    private void initViews(){
        viewTextName = (AppCompatTextView) findViewById(R.id.viewTextName);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
    }

    private void initObjects(){
        users = new ArrayList<>();
        usersRecycler = new UsersRecyclerAdapter(users);

        RecyclerView.LayoutManager layManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(layManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(usersRecycler);
        DBhelper = new SQLiteDbHelper(activity);

        String emailIntent = getIntent().getStringExtra("EMAIL");
        viewTextName.setText(emailIntent);
        getDataSQL();
    }

    private void getDataSQL(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params){
                users.clear();
                users.addAll(DBhelper.getAllUser());
                return null;
            }

            @Override
            protected void onPostExecute(Void av){
                super.onPostExecute(av);
                usersRecycler.notifyDataSetChanged();
            }
        }.execute();
    }
}
