package com.aasshh.user.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.aasshh.user.R;

public class Loader {
    Context context;
    AlertDialog alertDialog;

    public Loader(Context context) {
        this.context = context;
    }

    public void show() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this.context, R.style.customLoader);

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View view = layoutInflater.inflate(R.layout.loader_screen, null);
        alert.setView(view);


        alert.setCancelable(false);

        alertDialog = alert.create();
        alertDialog.show();


    }

    public void close() {
        try {
            alertDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
