package com.peery.android.projectscorpion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNewUserAccount extends AppCompatActivity {

    //SQLiteDbHelper mDbHelper = new SQLiteDbHelper(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user_account);

        final EditText mUserName = findViewById(R.id.username_editText);
        final EditText mPassword = findViewById(R.id.password_editText);
        final EditText mConfirmPassword = findViewById(R.id.confirm_password_editText);
        final Button mSave = findViewById(R.id.save_button);

        // will check that all fields have been filled and that password entries match
        // will then save data to phone
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userName = mUserName.getText().toString();
                final String userPassword = mPassword.getText().toString();
                final String confirmUserPassword = mConfirmPassword.getText().toString();

                if ( userName.isEmpty() || userPassword.isEmpty() || confirmUserPassword.isEmpty()) {
                    Toast.makeText(CreateNewUserAccount.this,
                            "Please make sure that all fields" +
                                    "are filled out. Only Middle Name may be left blank.",
                            Toast.LENGTH_LONG).show();
                } else {

                    if (userPassword.equals(confirmUserPassword)) {
                        //mDbHelper.insertUser(userName, userPassword);
                        startActivity(new Intent(CreateNewUserAccount.this,
                                MainActivity.class));
                    } else {
                        Toast.makeText(CreateNewUserAccount.this, "Password fields do not" +
                                        "match. Please retype password fields to continue",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
