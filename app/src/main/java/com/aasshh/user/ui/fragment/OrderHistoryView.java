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

public class OrderHistoryView extends Fragment {
    ConstraintLayout notLoggedInLayout;
    SessionHandler sessionHandler;
    Button loginBtn ;

    public OrderHistoryView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionHandler = new SessionHandler(getContext());
        if (sessionHandler.getIsLoggedIn()) {
            notLoggedInLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notLoggedInLayout = view.findViewById(R.id.not_logged_in_layout);
        loginBtn  =view.findViewById(R.id.login_into_account);


        loginBtn.setOnClickListener(v->{
            //Start login activity
            Intent login = new Intent(getContext() , Login.class);
            startActivity(login);
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_history_view, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sessionHandler.getIsLoggedIn()) {
            notLoggedInLayout.setVisibility(View.GONE);
        }
    }
}