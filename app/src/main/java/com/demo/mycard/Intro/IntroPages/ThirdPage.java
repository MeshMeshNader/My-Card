package com.demo.mycard.Intro.IntroPages;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.mycard.Auth.Login;
import com.demo.mycard.Auth.Signup;
import com.demo.mycard.Intro.Intro;
import com.demo.mycard.R;
import com.demo.mycard.User.UserHome;


public class ThirdPage extends Fragment {

    View view , fPage , sPage;
    CardView mHappyOccasion, mSadOccasion;
    ImageView mNextBtn;
    Intro mIntro;

    public ThirdPage() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third_page, container, false);

        initViews();

        return view;
    }

    private void initViews() {
        mIntro = new Intro();

        fPage = view.findViewById(R.id.intro_first_box);
        sPage = view.findViewById(R.id.intro_second_box);
        mHappyOccasion = view.findViewById(R.id.third_page_happy_occasion);
        mSadOccasion = view.findViewById(R.id.third_page_sad_occasion);
        mNextBtn = view.findViewById(R.id.next_page_image);

        fPage.setOnClickListener(v -> mIntro.loadOutFragment(new FirstPage() , getContext()));
        sPage.setOnClickListener(v -> mIntro.loadOutFragment(new SecondPage() , getContext()));

        mHappyOccasion.setOnClickListener(v ->openHomeHappy());
        mSadOccasion.setOnClickListener(v -> openHomeSad());
        mNextBtn.setOnClickListener(v -> nextFragment());
    }

    private void openHomeHappy() {
        Intent x = new Intent(getActivity(), UserHome.class);
        x.putExtra("occasion", 1);
        startActivity(x);
        getActivity().finish();
    }


    private void openHomeSad() {
        Intent x = new Intent(getActivity(), UserHome.class);
        x.putExtra("occasion", 2);
        startActivity(x);
        getActivity().finish();
    }



    private void nextFragment() {
        Intent x = new Intent(getActivity(), UserHome.class);
        x.putExtra("occasion", 1);
        startActivity(x);
        getActivity().finish();
    }

}