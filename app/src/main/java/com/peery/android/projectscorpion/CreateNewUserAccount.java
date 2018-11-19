package com.peery.android.projectscorpion;

import android.os.Bundle;
import android.view.View;
import android.support.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.peery.android.projectscorpion.ValidInput;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import com.peery.android.projectscorpion.R;
import com.peery.android.projectscorpion.ValidInput;
import com.peery.android.projectscorpion.User;
import com.peery.android.projectscorpion.SQLiteDbHelper;

public class CreateNewUserAccount extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = CreateNewUserAccount.this;
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
        setContentView(R.layout.activity_create_new_user_account);
        getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        layoutName = (TextInputLayout) findViewById(R.id.nameInputLayout);
        layoutEmail = (TextInputLayout) findViewById(R.id.emailInputLayout);
        layoutPassword = (TextInputLayout) findViewById(R.id.passwordInputLayout);
        layoutConfirmPassword = (TextInputLayout) findViewById(R.id.passwordConfirmInputLayout);

        textName = (TextInputEditText) findViewById(R.id.nameInput);
        textEmail = (TextInputEditText) findViewById(R.id.emailInput);
        textPassword = (TextInputEditText) findViewById(R.id.passwordInput);
        textCofirmPassword = (TextInputEditText) findViewById(R.id.passwordConfirmInput);

        registerButton = (AppCompatButton) findViewById(R.id.registerButton);

        loginLink = (AppCompatTextView) findViewById(R.id.loginLink);
    }

    private void initListeners(){
        registerButton.setOnClickListener(this);
        loginLink.setOnClickListener(this);
    }

    private void initObjects(){
        validInput = new ValidInput(activity);
        DBHelper = new SQLiteDbHelper(activity);
        user = new User();
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.registerButton:
                postDataToSQLite();
                break;
            case R.id.loginLink:
                finish();
                break;
        }
    }

    private void postDataToSQLite() {
        if (!validInput.isText(textName, layoutName, "Error: Enter Full Name"))
            return;
        if (!validInput.isText(textEmail, layoutEmail, "Error: Enter Valid Email"))
            return;
        if (!validInput.isTextEmail(textEmail, layoutEmail, "Error: Enter Valid Email"))
            return;
        if (!validInput.isText(textPassword, layoutPassword, "Error: Enter Valid Email"))
            return;
        if (!validInput.textMatch(textPassword, textCofirmPassword, layoutConfirmPassword, "Error: Passwords do not match."))
            return;

        if (!DBHelper.doesUserExist(textEmail.getText().toString().trim())) {
            user.setUsername(textName.getText().toString().trim());
            user.setEmail(textEmail.getText().toString().trim());
            user.setPassword(textPassword.getText().toString().trim());

            DBHelper.insertUser(user);
            Snackbar.make(nestedScrollView, "Created Successfully.", Snackbar.LENGTH_LONG).show();

            emptyInputText();

        } else {
            Snackbar.make(nestedScrollView, "Error: Email already exists.", Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputText(){
        textName.setText(null);
        textEmail.setText(null);
        textPassword.setText(null);
        textCofirmPassword.setText(null);
    }

}
