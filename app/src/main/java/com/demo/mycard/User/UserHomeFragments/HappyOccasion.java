package com.demo.mycard.User.UserHomeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.demo.mycard.R;
import com.demo.mycard.User.Happy.Ready.HappyReady;

public class HappyOccasion extends Fragment {

    View view;
    CardView mReady , mCustom;

    public HappyOccasion() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_happy_occasion, container, false);

        initViews();

        return view;
    }

    private void initViews() {

        mReady = view.findViewById(R.id.ready_happy_occasion);
        mCustom = view.findViewById(R.id.custom_happy_occasion);

        mReady.setOnClickListener( v -> startActivity(new Intent(getContext() , HappyReady.class)));
        //mCustom.setOnClickListener( v -> startActivity(new Intent(getContext() , SadOccasion.class)));

    }
}