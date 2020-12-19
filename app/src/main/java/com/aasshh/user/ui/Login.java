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
import com.aasshh.user.ui.signup.SignupGetStarted;
import com.aasshh.user.utils.Constant;
import com.aasshh.user.utils.ErrorAlert;
import com.aasshh.user.utils.Server;
import com.aasshh.user.utils.StringHandler;
import com.aasshh.user.widget.Loader;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Login Screen
 */
public class Login extends AppCompatActivity {
    Toolbar toolbar;
    String TAG = Login.class.getSimpleName();
    Button loginBtn, forgetBtn, signUpBtn;
    TextInputEditText emailInput, pswInput, phoneInput;
    TextInputLayout emailLayout, pswLayout, phoneLayout;
    String email, password, phone;
    ErrorAlert alert;
    Loader loader;
    RequestApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Init views
        toolbar = findViewById(R.id.toolbar);
        loginBtn = findViewById(R.id.login_btn);
        forgetBtn = findViewById(R.id.forget_btn);
//        emailInput = findViewById(R.id.email_input);
        pswInput = findViewById(R.id.password_input);
//        emailLayout = findViewById(R.id.email_layout);
        pswLayout = findViewById(R.id.password_layout);
        phoneLayout = findViewById(R.id.phone_layout);
        phoneInput = findViewById(R.id.phone_input);
        signUpBtn = findViewById(R.id.sign_up_btn);
        //Step 1 Add custom toolbar
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
        alert = new ErrorAlert(this);
        api = new RequestApi(this);
        loader = new Loader(this);

        //Email input change handler
//        emailInput.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.toString().length() < 1) return;
//                email = s.toString();
//                if (!StringHandler.isValidEmail(email)) {
//                    emailLayout.setError("Invalid Email");
//                } else {
//                    emailLayout.setErrorEnabled(false);
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        //On Phone change
        phoneInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.toString();
                if (phone.length() < 1) return;
                if (!StringHandler.isValidMobileNumber(phone)) {
                    phoneLayout.setError(getString(R.string.phone_error));
                } else {
                    phoneLayout.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //Password input change handler
        pswInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = s.toString();
                if (password.length() < 1) return;
                if (StringHandler.isValidPassword(password) == false) {
                    pswLayout.setError(getString(R.string.password_error));
                } else {
                    pswLayout.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //sign up button click handler
        signUpBtn.setOnClickListener(v -> {
            //open Register Page
            Intent signUpGetStarted = new Intent(getApplicationContext(), SignupGetStarted.class);
            startActivity(signUpGetStarted);

        });

        //login button click handler


        loginBtn.setOnClickListener(v -> {
            if (phone == null || phone.length() < 1 || !StringHandler.isValidMobileNumber(phone)) {
                alert.showError(getString(R.string.phone_error));
                return;
            }
            if (password == null || password.length() < 1) {
                alert.showError(getString(R.string.password_error));
                return;
            }

            loader.show();
            JSONObject postData = new JSONObject();
            try {
                postData.put("phone", phone);
                postData.put("password", password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            api.postRequest(Server.LOGIN, postData, response -> {
                Log.d(TAG, "onCreate: " + response);
                loader.close();
                try {
                    int status = response.getInt("status");
                    String message = response.getString("message");
                    if (status != 200) {
                        alert.showError(message);
                    } else {
                        //Login Success
                        Intent nextScreen = Constant.nextView;
                        if (nextScreen == null) {
                            finish();
                        } else {
                            //redirect to that particular screen
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });

        });
        forgetBtn.setOnClickListener(v -> {
            Intent forgetView = new Intent(getApplicationContext(), ForgetPassword.class);
            startActivity(forgetView);
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