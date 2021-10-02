package com.demo.mycard.Intro.IntroPages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.demo.mycard.Intro.Intro;
import com.demo.mycard.R;

public class SecondPage extends Fragment {

    View view , fPage , tPage;
    ImageView mNextBtn;
    Intro mIntro;

    public SecondPage() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_second_page, container, false);

        initViews();

        return view;
    }

    private void initViews() {
        mIntro = new Intro();

        fPage = view.findViewById(R.id.intro_first_box);
        tPage = view.findViewById(R.id.intro_second_box);

        fPage.setOnClickListener(v -> mIntro.loadOutFragment(new FirstPage() , getContext()));
        tPage.setOnClickListener(v -> mIntro.loadOutFragment(new ThirdPage() , getContext()));


        mNextBtn = view.findViewById(R.id.next_page_image);
        mNextBtn.setOnClickListener(v -> nextFragment());
    }


    private void nextFragment() {
        mIntro.loadOutFragment(new ThirdPage(), getActivity());
    }

}