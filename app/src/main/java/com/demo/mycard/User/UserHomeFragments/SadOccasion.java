package com.demo.mycard.User.UserHomeFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.mycard.R;
import com.demo.mycard.User.Happy.Ready.HappyReady;
import com.demo.mycard.User.Sad.Custom.SadCustom;
import com.demo.mycard.User.Sad.Ready.SadReady;

public class SadOccasion extends Fragment {

    View view;
    CardView mReady , mCustom;

    public SadOccasion() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sad_occasion, container, false);

        initViews();

        return view;
    }

    private void initViews() {

        mReady = view.findViewById(R.id.ready_sad_occasion);
        mCustom = view.findViewById(R.id.custom_sad_occasion);

        mReady.setOnClickListener( v -> startActivity(new Intent(getContext() , SadReady.class)));
        mCustom.setOnClickListener( v -> startActivity(new Intent(getContext() , SadCustom.class)));

    }
}