package com.aasshh.user.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aasshh.user.R;
import com.aasshh.user.services.RequestApi;
import com.aasshh.user.utils.ErrorAlert;
import com.aasshh.user.utils.StringHandler;
import com.aasshh.user.widget.OtpEditText;
import com.google.android.material.textfield.TextInputLayout;

public class NewPasswordSet extends AppCompatActivity {
    Toolbar toolbar;
    String TAG = OtpVerification.class.getSimpleName();
    ErrorAlert alert;
    OtpEditText passwordInput, confirmPasswordInput;
    TextInputLayout passwordLayout, confirmPasswordLayout;
    Button resetPassword;
    String password, confirmPassword;
    RequestApi requestApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password_set);
        //Init views
        toolbar = findViewById(R.id.toolbar);
        //step 1 add custom toolbar

        passwordLayout = findViewById(R.id.password_layout);
        confirmPasswordLayout = findViewById(R.id.conf_password_layout);
        passwordInput = findViewById(R.id.password_input);
        confirmPasswordLayout = findViewById(R.id.conf_password_input);
        resetPassword = findViewById(R.id.set_password);
        try {
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeButtonEnabled(true);
            setTitle("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        this.alert = new ErrorAlert(this);
        this.requestApi = new RequestApi(this);

        //Password input change listener
        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = s.toString();
                if (password.length() < 1) {
                    return;
                }
                if (StringHandler.isValidPassword(password) == false) {
                    passwordLayout.setError(getString(R.string.password_error));
                } else {
                    passwordLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //Confirm Password input change listener
        confirmPasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                confirmPassword = s.toString();
                if (confirmPassword == null || confirmPassword.length() < 1) return;
                if (password.equals(confirmPassword) == false) {
                    confirmPasswordLayout.setError(getString(R.string.confirm_password_error));
                } else {
                    confirmPasswordLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        resetPassword.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: Reset btn clicked");
        });


    }
}