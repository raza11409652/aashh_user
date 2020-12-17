package com.aasshh.user.ui.signup;

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

import com.aasshh.user.Model.User;
import com.aasshh.user.R;
import com.aasshh.user.services.RequestApi;
import com.aasshh.user.utils.ErrorAlert;
import com.aasshh.user.utils.Server;
import com.aasshh.user.utils.StringHandler;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupGetStarted extends AppCompatActivity {

    Toolbar toolbar;
    TextInputLayout phoneLayout;
    TextInputEditText phoneInput;
    String phone;
    Button createAccount;
    String TAG = SignupGetStarted.class.getSimpleName();
    RequestApi api;
    ErrorAlert alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_get_started);

        //Init views
        toolbar = findViewById(R.id.toolbar);
        phoneInput = findViewById(R.id.phone_input);
        phoneLayout = findViewById(R.id.phone_layout);
        createAccount = findViewById(R.id.create_account);

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

        //Step 2
        api = new RequestApi(this);
        alert = new ErrorAlert(this);


        //Mobile number input changer
        phoneInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.toString();
                if (phone.length() < 1) {
                    return;
                }
                if (StringHandler.isValidMobileNumber(phone) == false) {
                    phoneLayout.setError(getString(R.string.phone_error));
                } else {
                    phoneLayout.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Add on Create account click
        createAccount.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: " + phone);
            if (StringHandler.isEmpty(phone)) {
                alert.showError(getString(R.string.phone_required));
                return;
            }
            JSONObject data = new JSONObject();
            try {
                data.put("phone", phone);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            api.postRequest(Server.SIGN_UP_GET_STARTED, data, response -> {
                Log.d(TAG, "onCreate: " + response);
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
                    User user = new User(_id, _name, _email, _phone, _otp, _role, _token, _status, imageStr,
                            _updatedAt, _createdAt);

                    Intent otpVerification = new Intent(getApplicationContext(), VerifyOtp.class);
                    otpVerification.putExtra("user", user);
                    startActivity(otpVerification);
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