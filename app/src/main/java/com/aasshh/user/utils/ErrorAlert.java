package com.aasshh.user.utils;

import android.app.Activity;

import www.sanju.motiontoast.MotionToast;

public class ErrorAlert {
    Activity activity;

    public ErrorAlert(Activity activity) {
        this.activity = activity;
    }


    /**
     * @param msg Error Message
     */
    public void showError(String msg) {
        MotionToast.Companion.createColorToast(activity, "Error",
                msg, MotionToast.TOAST_ERROR, MotionToast.GRAVITY_BOTTOM, MotionToast.SHORT_DURATION, null);
    }

    /**
     * @param msg
     */
    public void showSuccess(String msg) {
        MotionToast.Companion.createColorToast(activity, "Success",
                msg, MotionToast.TOAST_SUCCESS, MotionToast.GRAVITY_BOTTOM, MotionToast.SHORT_DURATION, null);
    }

    /**
     * @param msg
     */
    public void showWarning(String msg) {
        MotionToast.Companion.createColorToast(activity, "Warning",
                msg, MotionToast.TOAST_WARNING, MotionToast.GRAVITY_BOTTOM, MotionToast.SHORT_DURATION, null);
    }

    public void showDarkToast(String msg) {
        MotionToast.Companion.darkToast(activity, "Warning",
                msg, MotionToast.TOAST_ERROR, MotionToast.GRAVITY_BOTTOM, MotionToast.SHORT_DURATION, null);
    }


}
