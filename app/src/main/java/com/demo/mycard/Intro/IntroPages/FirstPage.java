package com.demo.mycard.Intro.IntroPages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.demo.mycard.Intro.Intro;
import com.demo.mycard.R;


public class FirstPage extends Fragment {

    View view, sPage, tPage;
    ImageView mNextBtn;
    Intro mIntro;

    public FirstPage() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_first_page, container, false);

        initViews();

        return view;
    }

    private void initViews() {

        mIntro = new Intro();

        sPage = view.findViewById(R.id.intro_second_box);
        tPage = view.findViewById(R.id.intro_third_box);


        sPage.setOnClickListener(v -> mIntro.loadOutFragment(new SecondPage() , getContext()));
        tPage.setOnClickListener(v -> mIntro.loadOutFragment(new ThirdPage() , getContext()));


        mNextBtn = view.findViewById(R.id.next_page_image);
        mNextBtn.setOnClickListener(v -> nextFragment());

    }

    private void nextFragment() {
        mIntro.loadOutFragment(new SecondPage(), getActivity());
    }
}