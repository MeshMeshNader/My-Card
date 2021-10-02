package com.demo.mycard.Intro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.demo.mycard.Intro.IntroPages.FirstPage;
import com.demo.mycard.R;
import com.demo.mycard.User.UserHome;

public class Intro extends AppCompatActivity {


    TextView mSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        initViews();
        loadFragment(new FirstPage());
    }

    private void initViews() {

        mSkip = findViewById(R.id.intro_skip);
        mSkip.setOnClickListener(v -> skipToHome());
    }

    private void skipToHome() {
        Intent x = new Intent(Intro.this, UserHome.class);
        x.putExtra("occasion", 1);
        startActivity(x);
        finish();
    }


    public void loadOutFragment(Fragment fragment, Context context) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, 0, 0, 0);
        transaction.replace(R.id.intro_page_container, fragment).commit();
    }

    void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, 0, 0, 0);
        transaction.replace(R.id.intro_page_container, fragment).commit();
    }


}