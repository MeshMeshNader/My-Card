package com.demo.mycard.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.demo.mycard.R;
import com.demo.mycard.User.UserHomeFragments.HappyOccasion;
import com.demo.mycard.User.UserHomeFragments.SadOccasion;

public class UserHome extends AppCompatActivity {

    private final String TAG = "UserHome" ;

    ImageView mLogo;
    Button mHappyBtn, mSadBtn;
    int cat ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);


        initViews();

        Intent x = getIntent();
        cat = x.getIntExtra("occasion", 1);
        Log.e(TAG, "onCreate: " + cat );
        if (cat == 2)
            loadSad();
        else
            loadHappy();





    }


    private void initViews() {
        mLogo = findViewById(R.id.user_home_logo);
        mHappyBtn = findViewById(R.id.occasion_cat_happy);
        mSadBtn = findViewById(R.id.occasion_cat_sad);
        mHappyBtn.setOnClickListener(v -> loadHappy());
        mSadBtn.setOnClickListener(v -> loadSad());

    }


    private void loadHappy() {

        mLogo.setImageResource(R.drawable.ic_logo_orange);
        mHappyBtn.setBackground(getResources().getDrawable(R.drawable.button_clicked_happy));
        mSadBtn.setBackground(getResources().getDrawable(R.drawable.button_unclicked));
        loadFragment(new HappyOccasion());
    }

    private void loadSad() {
        mLogo.setImageResource(R.drawable.ic_logo_gray);
        mSadBtn.setBackground(getResources().getDrawable(R.drawable.button_clicked_sad));
        mHappyBtn.setBackground(getResources().getDrawable(R.drawable.button_unclicked));
        loadFragment(new SadOccasion());
    }


    void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, 0, 0, 0);
        transaction.replace(R.id.user_home_container, fragment).commit();
    }


}