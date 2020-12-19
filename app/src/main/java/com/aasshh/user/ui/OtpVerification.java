package com.aasshh.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aasshh.user.Model.User;
import com.aasshh.user.R;
import com.aasshh.user.services.RequestApi;
import com.aasshh.user.utils.Constant;
import com.aasshh.user.utils.ErrorAlert;
import com.aasshh.user.utils.Server;
import com.aasshh.user.widget.OtpEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class OtpVerification extends AppCompatActivity {
    Toolbar toolbar;
    String TAG = OtpVerification.class.getSimpleName();
    ErrorAlert alert;
    OtpEditText otpInput;
    Button verifyBtn, resendOtp;
    String otp, serverOtp = Constant.OTP;
    RequestApi requestApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        //Init views
        toolbar = findViewById(R.id.toolbar);
        otpInput = findViewById(R.id.otp_input);
        verifyBtn = findViewById(R.id.continue_btn);
        resendOtp = findViewById(R.id.resent_otp_btn);
        //step 1 add custom toolbar

        try {
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeButtonEnabled(true);
            setTitle("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.alert = new ErrorAlert(this);
        this.requestApi = new RequestApi(this);

        if (serverOtp != null) {
            this.alert.showDarkToast(serverOtp);
        }

        verifyBtn.setOnClickListener(v -> {
            otp = otpInput.getText().toString();
            if (otp == null || otp.length() < 1) {
                this.alert.showError(getString(R.string.otp_required));
                return;
            }
            JSONObject postData = new JSONObject();
            try {
                postData.put("phone", Constant.MOBILE);
                postData.put("verificationCode", otp);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            this.requestApi.postRequest(Server.VERIFY_OTP, postData, response -> {
                Log.d(TAG, "onCreate: " + response);
                try {
                    int status = response.getInt("status");
                    String message = response.getString("message");
                    if (status != 200) {

                    } else {

                        JSONObject profile = response.getJSONObject("data");
                        String _id = profile.getString("id");
                        String _name = profile.getString("fullName");
                        String _email = profile.getString("email");
                        String _phone = profile.getString("phone");
                        String _otp = profile.getString("verificationCode");
                        String _role = profile.getString("role");
                        String _token = profile.getString("accessToken");
                        String _status = profile.getString("status");
                        String imageStr = profile.getString("imageStr");
                        String _updatedAt = profile.getString("updatedAt");
                        String _createdAt = profile.getString("createdAt");
                        User user = new User(_id, _name, _email, _phone, _otp, _role, _token, _status, imageStr,
                                _updatedAt, _createdAt);
                        Constant.USER = user;
//                        finish();
                        Intent intent = new Intent(getApplicationContext(), NewPasswordSet.class);
                        startActivity(intent);
                        finish();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        });

        resendOtp.setOnClickListener(v -> {
            JSONObject data = new JSONObject();
            try {
                data.put("phone", Constant.MOBILE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.requestApi.postRequest(Server.FORGET_PSW, data, response -> {
                Log.d(TAG, "onCreate: " + response);
                try {
                    int status = response.getInt("status");
                    String message = response.getString("message");
                    if (status != 200) {
                        Log.d(TAG, "onCreate: Error Exist");

                    } else {
                        JSONObject object = response.getJSONObject("data");
                        String otp = object.getString("otp");
                        //TODO need to remove in production
                        alert.showDarkToast("New OTP " + otp);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });

        });


    }
}