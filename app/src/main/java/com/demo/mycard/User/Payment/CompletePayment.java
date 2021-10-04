package com.demo.mycard.User.Payment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.demo.mycard.DataModels.OrderDataModel;
import com.demo.mycard.DataModels.UserDataModel;
import com.demo.mycard.R;
import com.demo.mycard.User.UserHome;
import com.demo.mycard.Utils.CustomProgress;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CompletePayment extends AppCompatActivity {

    private final String TAG = "CompletePayment";

    TextView mCardNumber, mCardDate, mID , mBackBtn;
    Button mPayButton;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/Y hh:mm a");

    UserDataModel userData;
    OrderDataModel orderDataModel;
    FirebaseAuth mAuth;
    DatabaseReference mMainDataBase;
    DatabaseReference mUsersRef;
    DatabaseReference mOrdersRef;
    String currentUserID;
    Integer lastOrderNumber;
    String orderKey;


    String font, text, imageURI;
    int color, image;
    float widthDP, heightDP;
    boolean isCustom;

    CustomProgress customProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_payment);

        Intent card = getIntent();
        isCustom = card.getBooleanExtra("cardType", false);
        text = card.getStringExtra("cardText");
        font = card.getStringExtra("cardFont");
        color = card.getIntExtra("cardColor", -16777216);
        image = card.getIntExtra("cardImage", 2131230855);
        widthDP = card.getIntExtra("cardWidth", 300);
        heightDP = card.getIntExtra("cardHeight", 150);
        imageURI = card.getStringExtra("imageURI");

        initViews();

    }

    private void initViews() {

        customProgress = CustomProgress.getInstance();
        orderDataModel = new OrderDataModel();
        userData = new UserDataModel();

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        mUsersRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUserID);
        mOrdersRef = FirebaseDatabase.getInstance().getReference("Orders");
        mMainDataBase = FirebaseDatabase.getInstance().getReference("lastOrderNumber");

        mCardNumber = findViewById(R.id.complete_payment_card_number);
        mCardDate = findViewById(R.id.complete_payment_card_date);
        mID = findViewById(R.id.complete_payment_id);
        mBackBtn = findViewById(R.id.complete_payment_back);
        mBackBtn.setOnClickListener(v -> onBackPressed());
        mPayButton = findViewById(R.id.complete_payment_pay_btn);
        mPayButton.setOnClickListener(v -> getData());

    }

    private void getData() {

        if (validate()) {
            customProgress.showProgress(this, "Loading..!", true);

            mUsersRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        userData = snapshot.getValue(UserDataModel.class);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            mMainDataBase.child("lastOrderNumber").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        lastOrderNumber = snapshot.getValue(Integer.class);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    saveDataAndCompletePayment();
                }
            }, 500);
        }

    }

    private boolean validate() {
        if (mCardNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter your Card Number", Toast.LENGTH_LONG).show();
            return false;
        } else if (mCardDate.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter your Card Date", Toast.LENGTH_LONG).show();
            return false;
        } else if (mID.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter your ID", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    private void saveDataAndCompletePayment() {
        orderKey = mOrdersRef.push().getKey();

        orderDataModel.setUserID(userData.getUserID());
        orderDataModel.setUserName(userData.getName());
        orderDataModel.setOrderNumber(lastOrderNumber++);
        orderDataModel.setUserCreditNumber(mCardNumber.getText().toString());
        orderDataModel.setUserCreditDate(mCardDate.getText().toString());
        orderDataModel.setUserCreditID(mID.getText().toString());
        orderDataModel.setOrderStatus("On Request");
        orderDataModel.setOrderDate(format.format(new Date()));
        orderDataModel.setOrderKey(orderKey);
        if (isCustom) {
            orderDataModel.setOrderFont(font);
            orderDataModel.setOrderImage(image + "");
            orderDataModel.setOrderColor(color + "");
            orderDataModel.setOrderWidth(widthDP + "");
            orderDataModel.setOrderHeight(heightDP + "");
            orderDataModel.setOrderText(text);
            orderDataModel.setOrderType("Custom");
        } else {
            orderDataModel.setOrderImage(imageURI);
            orderDataModel.setOrderType("Ready");
        }


        mMainDataBase.child("lastOrderNumber").setValue(lastOrderNumber++).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.e(TAG, "onComplete: lastOrderNumber Updated!");
            }
        });


        mOrdersRef.child(orderKey).setValue(orderDataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    customProgress.hideProgress();
                    Toast.makeText(CompletePayment.this, "Order Saved Successfully", Toast.LENGTH_LONG).show();
                    sendUserToHome();
                } else {
                    Log.e("TAG", "onComplete: " + task.getException().toString());
                }
            }
        });
    }


    void sendUserToHome() {
        Intent x = new Intent(CompletePayment.this, UserHome.class);
        x.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        x.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        x.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(x);
        finish();
    }


}