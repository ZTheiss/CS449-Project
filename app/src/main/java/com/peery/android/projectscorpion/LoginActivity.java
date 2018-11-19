package com.peery.android.projectscorpion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;
    private TextInputLayout textLayoutEmail;
    private TextInputLayout textLayoutPassword;

    private TextInputEditText textEditEmail;
    private TextInputEditText textEditPassword;

    private AppCompatButton loginButton;
    private AppCompatTextView registerText;

    private ValidInput validInput;
    private SQLiteDbHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedState){
        super.onCreate(savedState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textLayoutEmail = (TextInputLayout) findViewById(R.id.emailInputLayout);
        textLayoutPassword = (TextInputLayout) findViewById(R.id.passwordInputLayout);

        textEditEmail = (TextInputEditText) findViewById(R.id.emailInput);
        textEditPassword = (TextInputEditText) findViewById(R.id.passwordInput);

        loginButton = (AppCompatButton) findViewById(R.id.loginButton);
        registerText = (AppCompatTextView) findViewById(R.id.register);
    }

    private void initListeners(){
        loginButton.setOnClickListener(this);
        registerText.setOnClickListener(this);
    }

    private void initObjects(){
        DBHelper = new SQLiteDbHelper(activity);
        validInput = new ValidInput(activity);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.loginButton:
                verifyFromSQLite();
                break;
            case R.id.register:
                Intent registerIntent = new Intent(getApplicationContext(), CreateNewUserAccount.class);
                startActivity(registerIntent);
                break;
        }
    }

    private void verifyFromSQLite(){
        if (!validInput.isText(textEditEmail, textLayoutEmail, "Enter Valid Email")){
            return;
        }
        if (!validInput.isTextEmail(textEditEmail, textLayoutEmail, "Enter Valid Email")){
            return;
        }
        if (!validInput.isText(textEditPassword, textLayoutPassword, "Enter Valid Email")){
            return;
        }
        if (DBHelper.doesUserExist(textEditEmail.getText().toString().trim(), textEditPassword.getText().toString().trim())){
            Intent accountsIntent = new Intent(activity, User.class);
            accountsIntent.putExtra("EMAIL", textEditEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        }
        else{
            Snackbar.make(nestedScrollView, "Please enter valid email or password.", Snackbar.LENGTH_LONG).show();
        }

    }

    private void emptyInputEditText(){
        textEditEmail.setText(null);
        textEditPassword.setText(null);
    }
}
