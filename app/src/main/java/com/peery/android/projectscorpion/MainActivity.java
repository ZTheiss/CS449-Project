package com.peery.android.projectscorpion;

import android.Manifest;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Login screen will ask for user name and password. Currently the app does not write to the
 * phone. This activity will also ask for the users permission to read/write to the SD to store the
 * user name and password the user will create on first time use. As well as grant the permission to
 * store the profile they will create to auto fill resumes. The resume autofill method/activity will
 * be implemented in a later version of the app.
 */

public class MainActivity extends AppCompatActivity {

    //variables to store info about the permissions and if they are granted
    private static final int PERMISSION_READ_STORAGE_REQUEST_CODE = 1;
    private static final int PERMISSION_WRITE_STORAGE_REQUEST_CODE = 1;

    //declare variables for UI interface xml
    EditText mUserName;
    EditText mPassword;
    Button mLogin_Button;
    TextView mCreateAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set variables to xml id's
        mUserName = findViewById(R.id.username_id);
        mPassword = findViewById(R.id.password_id);
        mLogin_Button = findViewById(R.id.login_button);
        mCreateAccountButton = findViewById(R.id.create_account_button);

        //onclick listener for login button for user account.
        //currently set to bypass user account check and start the JobSearchMain activity
        mLogin_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //permission request will run on first time user logs into account. If user denies
                //permission, user will be warned that they will not be able to use the app
                readStoragePermissionRequest();
                /* todo : will check that a profile exists on phone. if not will show toast to let user
                know that an account needs to be created first
                */
                // will check that fields are not empty
                if (mUserName.getText().toString().isEmpty() || mPassword.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter both UserName and Password.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // String validPass = "test";
                    SQLiteDbHelper myDBHelper = new SQLiteDbHelper(MainActivity.this,
                            null, null, 1);
                    User validUser = null;
                    //= myDBHelper.findLogIn(mUserName.getText().toString());
                    if (validUser != null) {
                        String username = String.valueOf(validUser.getUserName());
                        String userpass = String.valueOf(validUser.getUserPassword());
                        if(username.equals(mUserName.getText().toString())) {
                            if (userpass.equals(mPassword.getText().toString())) {
                                startActivity(new Intent(MainActivity.this, JobSearchMain.class));
                            }
                        }
                    } else {
                        // if login is false, allow user to try again
                        Toast.makeText(MainActivity.this, "UserName or Password" +
                                " is not Correct.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //onclick listener to create new user account
        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //permission request to write to storage
                writeStoragePermissionRequest();
                //open up CreateUserAccount activity
                startActivity(new Intent(MainActivity.this, CreateNewUserAccount.class));
            }
        });
    }

    // method to ask for permission to read from storage
    // permission will be to read user account info for this app, profile information created for
    // the autofill, and resume saved to phone
    public void readStoragePermissionRequest() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(this)
                        .setTitle("Read Storage Permission Needed")
                        .setMessage("This permission is needed to read the profile you created for" +
                                " auto filling online job applications. It is also needed to read" +
                                " your account to verify username and password for login. Denying" +
                                " this permission means you will be unable to use the app.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]
                                                {Manifest.permission.READ_EXTERNAL_STORAGE},
                                        PERMISSION_READ_STORAGE_REQUEST_CODE);

                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]
                                {Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_READ_STORAGE_REQUEST_CODE);
            }
        }
    }

    // method to ask for permission to write to storage
    // permission will be to write user account info for this app when first created,
    // profile information created for the autofill, and resume saved to phone if the user needs to
    // edit it
    public void writeStoragePermissionRequest() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(this)
                        .setTitle("Read Storage Permission Needed")
                        .setMessage("This permission is needed to write the profile you create for" +
                                " auto filling online job applications. It is also needed to write " +
                                "username and password to storage. Denying this permission will mean" +
                                " you will not be able to use the app.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]
                                                {Manifest.permission.READ_EXTERNAL_STORAGE},
                                        PERMISSION_READ_STORAGE_REQUEST_CODE);

                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]
                                {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_WRITE_STORAGE_REQUEST_CODE);
            }
        }
    }
}
