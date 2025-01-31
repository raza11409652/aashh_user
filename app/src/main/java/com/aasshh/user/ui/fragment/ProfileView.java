package com.aasshh.user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.aasshh.user.R;
import com.aasshh.user.ui.Login;
import com.aasshh.user.utils.SessionHandler;

public class ProfileView extends Fragment {
    SessionHandler sessionHandler;
    ConstraintLayout notLoggedIn;
    Button loginIntoAccount;


    public ProfileView() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionHandler = new SessionHandler(getContext());


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notLoggedIn = view.findViewById(R.id.not_logged_in_layout);
        loginIntoAccount = view.findViewById(R.id.login_into_account);
        //If session found
        if (sessionHandler.getIsLoggedIn()) {
            notLoggedIn.setVisibility(View.GONE);
        }

        //On Click listener to login button
        loginIntoAccount.setOnClickListener(v -> {
            Intent loginView = new Intent(getContext(), Login.class);
            startActivity(loginView);
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_view, container, false);
    }

    //On Resume
    @Override
    public void onResume() {
        super.onResume();

        if (sessionHandler.getIsLoggedIn()) {
            notLoggedIn.setVisibility(View.GONE);
        }
    }
}