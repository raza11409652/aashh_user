package com.aasshh.user.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aasshh.user.Model.User;
import com.aasshh.user.R;
import com.aasshh.user.services.RequestApi;
import com.aasshh.user.ui.Home;
import com.aasshh.user.utils.ErrorAlert;
import com.aasshh.user.utils.Server;
import com.aasshh.user.utils.SessionHandler;
import com.aasshh.user.utils.StringHandler;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * phone”: “8591553277",
 * “email”: “sdvds@df.dfdvcd”,
 * “fullName”: “567322",
 * “password”: “1234567890"
 */
public class CreateProfile extends AppCompatActivity {
    Toolbar toolbar;
    TextInputLayout emailLayout, nameLayout, passwordLayout, confPasswordLayout;
    TextInputEditText emailInput, nameInput, passwordInput, confPasswordInput;
    User user;
    String TAG = CreateProfile.class.getSimpleName();
    Button createAccount;
    String phone, email, fullName, password, confirmPassword;
    ErrorAlert alert;
    RequestApi api;
    SessionHandler sessionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        //Step 1 init views
        toolbar = findViewById(R.id.toolbar);
        emailLayout = findViewById(R.id.email_layout);
        nameLayout = findViewById(R.id.name_layout);
        passwordLayout = findViewById(R.id.password_layout);
        confPasswordLayout = findViewById(R.id.conf_password_layout);
        nameInput = findViewById(R.id.name_input);
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        confPasswordInput = findViewById(R.id.conf_password_input);
        createAccount = findViewById(R.id.create_account);


        //step 2 add toolbar
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
            phone = user.getPhone();


        } catch (Exception e) {
            e.printStackTrace();
        }
        //Step 3
        alert = new ErrorAlert(this);
        api = new RequestApi(this);
        sessionHandler = new SessionHandler(this);


        //name changed handler
        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fullName = s.toString();
                if (StringHandler.isEmpty(fullName)) return;
                if (StringHandler.isValidName(fullName) == false) {
                    nameLayout.setError(getString(R.string.name_error));
                } else {
                    nameLayout.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //Email changed handler
        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                email = s.toString();
                if (StringHandler.isValidEmail(email) == false) {
                    emailLayout.setError(getString(R.string.email_invalid));
                } else {
                    emailLayout.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //password handler
        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                password = s.toString();
                if (password.length() < 1) return;
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
        //confirm password handler
        confPasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                confirmPassword = s.toString();
                if (password.equals(confirmPassword) == false) {
                    confPasswordLayout.setError(getString(R.string.confirm_password_error));
                } else {
                    confPasswordLayout.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        createAccount.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: Create Account Clicked");
            if (StringHandler.isEmpty(fullName)) return;
            if (StringHandler.isEmpty(email)) return;
            if (StringHandler.isValidEmail(email) == false) {
                return;
            }
            if (password.equals(confirmPassword) == false) {
                return;
            }
            JSONObject postData = new JSONObject();
            try {
                postData.put("phone", phone);
                postData.put("fullName", fullName);
                postData.put("email", email);
                postData.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            api.postRequest(Server.SIGN_UP, postData, response -> {
                Log.d(TAG, "onCreate: " + response);
                try {
                    int status = response.getInt("status");
                    String message = response.getString("message");
                    if (status != 200) {
                        alert.showError(message);
                        return;
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
                        user = new User(_id, _name, _email, _phone, _otp, _role, _token, _status, imageStr,
                                _updatedAt, _createdAt);

                        //session has been modified
                        sessionHandler.setIsLoggedIn(true);
                        sessionHandler.setLoggedInUser(user.getId());
                        sessionHandler.setLoggedToken(user.getAccessToken());
                        sessionHandler.setLoggedInMobile(user.getPhone());
                        sessionHandler.setLoggedUserName(user.getName());
                        //TODO we need to check from where the request has been made

                        //Home is started
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });

        });


    }
}