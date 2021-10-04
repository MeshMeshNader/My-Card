package com.demo.mycard.Admin;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.bumptech.glide.Glide;
import com.demo.mycard.DataModels.OrderDataModel;
import com.demo.mycard.R;
import com.demo.mycard.Utils.CustomProgress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewOrderDetails extends AppCompatActivity {

    //Views
    TextView mUserName, mOrderNumber, mOrderType, mOrderStatus, mBackBtn;

    //Ready
    ImageView mOrderImage;

    //Custom
    CardView mCardDimCV, mImageDim;
    RelativeLayout mCardBackgroundRL;
    ImageView mImage1, mImage2, mImage3, mImage4;
    TextView mCardText;

    //Firebase
    String orderKey;
    DatabaseReference mOrderRef;
    OrderDataModel orderDataModel;
    CustomProgress mCustomProgress;
    float widthDP, heightDP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_details);

        Intent order = getIntent();
        orderKey = order.getStringExtra("viewOrderKey");

        initViews();
    }

    private void initViews() {
        //Views
        mUserName = findViewById(R.id.order_details_user_name);
        mOrderNumber = findViewById(R.id.order_details_order_number);
        mOrderType = findViewById(R.id.order_details_order_type);
        mOrderStatus = findViewById(R.id.order_details_order_status);
        mBackBtn = findViewById(R.id.order_details_back);
        mBackBtn.setOnClickListener(v -> onBackPressed());

        //Ready
        mImageDim = findViewById(R.id.order_details_ready_card_dim);
        mOrderImage = findViewById(R.id.order_details_ready_image);

        //Custom
        mCardDimCV = findViewById(R.id.order_details_custom_card_dim);
        mCardBackgroundRL = findViewById(R.id.order_details_custom_background);
        mImage1 = findViewById(R.id.order_details_custom_image1);
        mImage2 = findViewById(R.id.order_details_custom_image2);
        mImage3 = findViewById(R.id.order_details_custom_image3);
        mImage4 = findViewById(R.id.order_details_custom_image4);
        mCardText = findViewById(R.id.order_details_custom_center_text);


        //Firebase
        mOrderRef = FirebaseDatabase.getInstance().getReference("Orders").child(orderKey);
        orderDataModel = new OrderDataModel();


        mCustomProgress = CustomProgress.getInstance();
        mCustomProgress.showProgress(this, "Loading..!", true);


        getDetails();
    }

    private void getDetails() {
        mOrderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    orderDataModel = snapshot.getValue(OrderDataModel.class);

                    mUserName.setText("User Name : " + orderDataModel.getUserName());
                    mOrderNumber.setText("Order Number : " + orderDataModel.getOrderNumber());
                    mOrderType.setText("Type : " + orderDataModel.getOrderType());
                    mOrderStatus.setText("Status : " + orderDataModel.getOrderStatus());

                    if (orderDataModel.getOrderType().equals("Custom")) {

                        mCardDimCV.setVisibility(View.VISIBLE);
                        mImageDim.setVisibility(View.GONE);

                        Drawable backgroundDrawable = mCardBackgroundRL.getBackground();
                        backgroundDrawable = DrawableCompat.wrap(backgroundDrawable);
                        DrawableCompat.setTint(backgroundDrawable,
                                Integer.parseInt(orderDataModel.getOrderColor()));
                        mCardBackgroundRL.setBackground(backgroundDrawable);

                        widthDP = Float.parseFloat(orderDataModel.getOrderWidth()) * ViewOrderDetails.this.getResources().getDisplayMetrics().density;
                        heightDP = Float.parseFloat(orderDataModel.getOrderHeight()) * ViewOrderDetails.this.getResources().getDisplayMetrics().density;


                        LinearLayout.LayoutParams cardLayout = (LinearLayout.LayoutParams) mCardDimCV.getLayoutParams();
                        cardLayout.width = (int) widthDP;
                        cardLayout.height = (int) heightDP;
                        cardLayout.gravity = Gravity.CENTER;
                        mCardDimCV.setLayoutParams(cardLayout);

                        mImage1.setImageResource(Integer.parseInt(orderDataModel.getOrderImage()));
                        mImage2.setImageResource(Integer.parseInt(orderDataModel.getOrderImage()));
                        mImage3.setImageResource(Integer.parseInt(orderDataModel.getOrderImage()));
                        mImage4.setImageResource(Integer.parseInt(orderDataModel.getOrderImage()));

                        mCardText.setText(orderDataModel.getOrderText());


                        Typeface typeface;
                        switch (orderDataModel.getOrderFont()) {
                            case "Gemunu":
                                typeface = ResourcesCompat.getFont(ViewOrderDetails.this, R.font.gemunu_libre);
                                break;
                            case "Playfair":
                                typeface = ResourcesCompat.getFont(ViewOrderDetails.this, R.font.player_fair);
                                break;
                            case "Ephesis":
                                typeface = ResourcesCompat.getFont(ViewOrderDetails.this, R.font.ephisis);
                                break;
                            case "Yaldevi":
                                typeface = ResourcesCompat.getFont(ViewOrderDetails.this, R.font.yaldevi);
                                break;
                            default:
                                typeface = ResourcesCompat.getFont(ViewOrderDetails.this, R.font.roboto_medium);
                                break;
                        }

                        mCardText.setTypeface(typeface);


                    } else if (orderDataModel.getOrderType().equals("Ready")) {
                        mCardDimCV.setVisibility(View.GONE);
                        mImageDim.setVisibility(View.VISIBLE);
                        try {
                            Glide.with(ViewOrderDetails.this).load(orderDataModel.getOrderImage()).into(mOrderImage);

                        } catch (Exception e) {

                        }
                    }

                    mCustomProgress.hideProgress();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}