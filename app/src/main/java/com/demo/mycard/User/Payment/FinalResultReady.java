package com.demo.mycard.User.Payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.demo.mycard.R;
import com.demo.mycard.User.Happy.Ready.HappyReady;

public class FinalResultReady extends AppCompatActivity {


    ImageView mImage;
    Button mPayBtn;
    String imageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result_ready);

        Intent image = getIntent();
        imageURI = image.getStringExtra("imageURI");

        initViews();
    }

    private void initViews() {
        mImage = findViewById(R.id.final_result_ready_image);
        mPayBtn = findViewById(R.id.final_result_ready_pay_btn);

        Glide.with(this).load(imageURI).into(mImage);

        mPayBtn.setOnClickListener(v -> goToPayActivity());
    }

    private void goToPayActivity() {
        Intent image = new Intent(FinalResultReady.this, CompletePayment.class);
        image.putExtra("imageURI", imageURI);
        image.putExtra("cardType", false);
        startActivity(image);
    }
}