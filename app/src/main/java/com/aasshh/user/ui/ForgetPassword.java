package com.aasshh.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aasshh.user.R;
import com.aasshh.user.services.RequestApi;
import com.aasshh.user.utils.Constant;
import com.aasshh.user.utils.ErrorAlert;
import com.aasshh.user.utils.Server;
import com.aasshh.user.utils.StringHandler;
import com.aasshh.user.widget.Loader;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgetPassword extends AppCompatActivity {
    Button resetPsw;
    TextInputEditText mobileInput;
    Toolbar toolbar;
    TextInputLayout mobileLayout;
    String mobileNumber;
    ErrorAlert alert;
    RequestApi requestApi;
    Loader loader;

    String TAG = ForgetPassword.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        //init views
        toolbar = findViewById(R.id.toolbar);

        mobileInput = findViewById(R.id.phone_input);
        mobileLayout = findViewById(R.id.phone_layout);
        resetPsw = findViewById(R.id.reset_password);
        //Step add custom toolbar
        try {
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeButtonEnabled(true);
            setTitle("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.requestApi = new RequestApi(this);
        alert = new ErrorAlert(this);
        this.loader = new Loader(this);


        //Mobile input change listener
        mobileInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mobileNumber = s.toString();
                if (mobileNumber.length() < 1) return;
                if (!StringHandler.isValidMobileNumber(mobileNumber)) {
                    mobileLayout.setError(getString(R.string.phone_error));
                } else {
                    mobileLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //ResetPassword on click
        resetPsw.setOnClickListener(v -> {
            if (mobileNumber == null || !StringHandler.isValidMobileNumber(mobileNumber)) {
                alert.showError(getString(R.string.phone_error));
                return;
            }
            JSONObject postData = new JSONObject();
            try {
                postData.put("phone", mobileNumber);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            this.loader.show();
            this.requestApi.postRequest(Server.FORGET_PSW, postData, response -> {
                this.loader.close();
                Log.d(TAG, "onCreate: " + response);
                try {
                    int status = response.getInt("status");
                    String message = response.getString("message");
                    if (status != 200) {
                        Log.d(TAG, "onCreate: Error Exist");

                    } else {
                        JSONObject object =response.getJSONObject("data");
                        String otp = object.getString("otp");
                        Constant.OTP = otp;
                        Constant.MOBILE =mobileNumber ;
                        Intent otpVerification = new Intent(getApplicationContext(), OtpVerification.class);
                        startActivity(otpVerification);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });


        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}