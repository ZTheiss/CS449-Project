package com.peery.android.projectscorpion;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class JobSearchMain extends AppCompatActivity {

    private static final int PERMISSION_NETWORK_STATUS_REQUEST_CODE = 1;
    private static final int PERMISSION_INTERNET_REQUEST_CODE = 1;
    private static final int PERMISSION_COARSE_LOCATION_REQUEST_CODE = 1;
    private static final int PERMISSION_FINE_LOCATION_REQUEST_CODE = 1;

    WebSearchResults WebSearch;

    TextView mInfo;
    EditText mJob, mCity, mState;
    RadioButton mGPS;
    Button mSearch;

    public String job, city, state, searchStatement, url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search_main);

        mInfo = findViewById(R.id.searchInfo_textView_id);
        mJob = findViewById(R.id.job_autoCompleteTextView_id);
        mCity = findViewById(R.id.city_autoCompleteTextView_id);
        mState = findViewById(R.id.State_autoCompleteTextView_id);
        mGPS = findViewById(R.id.gps_radioButton_id);
        mSearch = findViewById(R.id.search_button_id);

        //check network status and request permission for checking
        CheckNetWork();

        // methods called here are only to show that we know how to do the TDD. they will be replaced
        // in final version to a getText().toString() call on the EditText variables.
        job = getJobSearchString(mJob);
        city = getCitySearchString(mCity);
        state = getStateSearchString(mState);
        searchStatement = getSearchCriteria(job, city, state);
        url = "www.google.com/";

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //request permission for internet
                internetPermissionRequest();
                //get internet connection
                //if available, open web search activity
                //WebSearch.webAction(url, searchStatement);
                //else warn user
                startActivity(new Intent(JobSearchMain.this, WebSearchResults.class));
            }
        });


    }

    public void CheckNetWork() {

        networkStatusPermissionRequest();

        ConnectivityManager connectionAvailable = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectionAvailable.getActiveNetworkInfo();
        if (netInfo == null) {
            Toast.makeText(this, "Network Connection is not Available", Toast.LENGTH_LONG).show();
        }

    }

    public void networkStatusPermissionRequest() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_NETWORK_STATE)) {
                // todo : create alert dialog fro reason persmission is needed to display to the user
            } else {
                ActivityCompat.requestPermissions(this, new String[]
                                {Manifest.permission.ACCESS_NETWORK_STATE},
                        PERMISSION_NETWORK_STATUS_REQUEST_CODE);
            }
        }
    }

    public void internetPermissionRequest() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.INTERNET)) {
                // todo : create alert dialog fro reason persmission is needed to display to the user
            } else {
                ActivityCompat.requestPermissions(this, new String[]
                                {Manifest.permission.INTERNET},
                        PERMISSION_INTERNET_REQUEST_CODE);
            }
        }
    }

    // the following methods are not really needed. they where written so we could show TDD for the
    // project. These will be deleted before teh final version of the app and the test will go from host
    // to android
    public static String getJobSearchString(EditText mJob) {
        String job;
        job = mJob.getText().toString();
        return job;
    }

    public static String getCitySearchString(EditText mCity){
        String city;
        city = mCity.getText().toString();
        return city;
    }

    public static String getStateSearchString(EditText mState){
        String state;
        state = mState.getText().toString();
        return state;
    }

    public static String getSearchCriteria(String job, String city, String state){
        String searchStatement;
        searchStatement= job + " " + city + " " + state;
        return searchStatement;
    }

}

