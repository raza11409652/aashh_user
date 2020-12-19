package com.aasshh.user.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.aasshh.user.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartView extends Fragment {


    public CartView() {
        // Required empty public constructor
    }


    public static CartView newInstance(String param1, String param2) {
        CartView fragment = new CartView();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart_view, container, false);
    }
}