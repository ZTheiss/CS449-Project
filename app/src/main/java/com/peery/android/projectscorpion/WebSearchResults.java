package com.peery.android.projectscorpion;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WebSearchResults extends AppCompatActivity {

    WebView view;
    TextView mSearchedForResults;
    EditText mJob, mCity, mState;
    String job, city, state;
    String mDisplaySearchQueryAsString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_search_results);

        /* the following lines of code are to test that the search results page is working in as much
         as getting the query string and trying to search for it and return results. This will be removed
         once Mokito has been set up and completly removed from code once app is functional*/

       // get EditText info from JobSearchMain.class and convert to String
        mJob = findViewById(R.id.job_autoCompleteTextView_id);
       // job = mJob.getText().toString();
        mCity = findViewById(R.id.city_autoCompleteTextView_id);
       // city = mCity.getText().toString();
        mState = findViewById(R.id.State_autoCompleteTextView_id);
       // state = mState.getText().toString();
        // get search statement from JobSearchMain.class to display to user on WebSearchResults
        // activity page
       // mDisplaySearchQueryAsString = JobSearchMain.getSearchCriteria(job, city, state);

        mSearchedForResults = findViewById(R.id.searched_for_result_textView);

        // display results to user of what they searched for



        // todo : display at top of results screen the query user did for the search action
        // todo : get Mokito to return restAPI for webview display
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void webAction(String url, String searchStatement) {

        view = findViewById(R.id.view_id);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setAppCacheEnabled(true);
        // currently will not use search statement from user so that this can be tested that it opens
        // web page
        view.loadUrl(url);

    }
}
