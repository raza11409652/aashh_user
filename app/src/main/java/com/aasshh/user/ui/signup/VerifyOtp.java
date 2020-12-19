package com.aasshh.user.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aasshh.user.Model.User;
import com.aasshh.user.R;
import com.aasshh.user.services.RequestApi;
import com.aasshh.user.utils.ErrorAlert;
import com.aasshh.user.utils.Server;
import com.aasshh.user.utils.StringHandler;
import com.aasshh.user.widget.OtpEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class VerifyOtp extends AppCompatActivity {
    User user;

    Toolbar toolbar;
    String TAG = VerifyOtp.class.getSimpleName();
    ErrorAlert alert;
    OtpEditText otpInput;
    Button verifyBtn, resendOtp;
    String otp;
    RequestApi requestApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

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
            user = (User) getIntent().getSerializableExtra("user");
            if (user == null) {
                Log.d(TAG, "onCreate: User not found ");
                return;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        //init views
        alert = new ErrorAlert(this);
        requestApi = new RequestApi(this);

        //Show OTP till development
        alert.showDarkToast("Your OTP " + user.getVerificationCode());

        verifyBtn.setOnClickListener(v -> {
            otp = otpInput.getText().toString();
            if (StringHandler.isEmpty(otp)) {
                alert.showError(getString(R.string.otp_required));
                return;
            }
            Log.d(TAG, "onCreate: " + otp);
            JSONObject verifyObj = new JSONObject();
            try {
                verifyObj.put("phone", user.getPhone());
                verifyObj.put("verificationCode", otp);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Request to server for validation of OTP
            requestApi.postRequest(Server.VERIFY_OTP, verifyObj, response -> {
                Log.d(TAG, "onCreate: " + response);
                try {
                    int status = response.getInt("status");
                    String message = response.getString("message");
                    if (status == 200) {
                        Intent profileCreate = new Intent(getApplicationContext(), CreateProfile.class);
                        profileCreate.putExtra("user", user);
                        startActivity(profileCreate);
                        finish();
                    } else {
                        alert.showError(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            });


        });

        //On Resend Otp Click
        resendOtp.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: Resend Otp init hit");
            JSONObject data = new JSONObject();
            try {
                data.put("phone", user.getPhone());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            requestApi.postRequest(Server.SIGN_UP_GET_STARTED, data, response -> {
                try {
                    int status = response.getInt("status");
                    String message = response.getString("message");
                    if (status != 200) {
                        alert.showError(message);
                        return;
                    }

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
                    user = new User(_id, _name, _email, _phone, _otp, _role, _token, _status, imageStr,
                            _updatedAt, _createdAt);
                    /**
                     * Otp will display for Development Time
                     */
                    alert.showDarkToast("Your new OTP is " + _otp);


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