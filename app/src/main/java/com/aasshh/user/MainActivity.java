package com.aasshh.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aasshh.user.ui.Home;
import com.aasshh.user.ui.Login;

/**
 * This will act as splash screen for App
 */
public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    String TAG = MainActivity.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent home = new Intent(getApplicationContext(), Home.class);
        startActivity(home);
        finish();



    }


}