package com.peery.android.projectscorpion;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = RegisterActivity.this;
    private NestedScrollView nestedScrollView;

    private TextInputLayout layoutName;
    private TextInputLayout layoutEmail;
    private TextInputLayout layoutPassword;
    private TextInputLayout layoutConfirmPassword;

    private TextInputEditText textName;
    private TextInputEditText textEmail;
    private TextInputEditText textPassword;
    private TextInputEditText textCofirmPassword;

    private AppCompatButton registerButton;
    private AppCompatTextView loginLink;

    private ValidInput validInput;
    private SQLiteDbHelper DBHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedState){
        super.onCreate(savedState);
        setContentView(R.layout.activity_create_new_user);
        getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();
    }












}
