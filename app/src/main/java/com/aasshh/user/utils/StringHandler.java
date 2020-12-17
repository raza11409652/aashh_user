package com.aasshh.user.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class StringHandler {
    public static boolean isEmpty(String string) {
        if (TextUtils.isEmpty(string)) return true;

        return false;
    }

    public static boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;

        }
        return false;
    }

    /**
     * Rule must not be blank
     * Must be of at least 4 character long
     *
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        if (password.length() < 4) {
            return false;

        }
        return true;
    }

    public static boolean isValidMobileNumber(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            return false;
        }
        if (TextUtils.isDigitsOnly(mobile) && mobile.length() == 10
                && Patterns.PHONE.matcher(mobile).matches()) {
            return true;
        }
        return false;
    }
}
