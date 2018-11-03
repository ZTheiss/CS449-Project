package com.peery.android.projectscorpion;

import android.app.Activity;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ValidInput {
    private Context context;

    public ValidInput(Context context){
        this.context = context;
    }

    public boolean isText(TextInputEditText inputEditText, TextInputLayout textInputLayout, String message){
        String value = inputEditText.getText().toString().trim();

        if(value.isEmpty()){
            textInputLayout.setError(message);
            hideKeyboardFrom(inputEditText);
            return false;
        }
        else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isTextEmail(TextInputEditText inputEditText, TextInputLayout textInputLayout, String message){
        String value = inputEditText.getText().toString().trim();

        if(value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            textInputLayout.setError(message);
            hideKeyboardFrom(inputEditText);
            return false;
        }
        else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean textMatch(TextInputEditText inputEditText1, TextInputEditText inputEditText2, TextInputLayout textInputLayout, String message){
        String value1 = inputEditText1.getText().toString().trim();
        String value2 = inputEditText2.getText().toString().trim();

        if(value1.contentEquals(value2)){
            textInputLayout.setError(message);
            hideKeyboardFrom(inputEditText2);
            return false;
        }
        else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private void hideKeyboardFrom(View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
