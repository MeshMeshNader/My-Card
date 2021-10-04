package com.demo.mycard.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.demo.mycard.Adapters.OrderAdapter;
import com.demo.mycard.Auth.EditProfile;
import com.demo.mycard.DataModels.OrderDataModel;
import com.demo.mycard.R;
import com.demo.mycard.Utils.CustomProgress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AdminHome extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    ImageView mProfileBtn;
    RecyclerView mOrdersRecyclerView;
    OrderAdapter mOrderAdapter;

    SwipeRefreshLayout refreshLayout;


    DatabaseReference mOrdersRef;
    ArrayList<OrderDataModel> mOrders = new ArrayList<>();

    CustomProgress mCustomProgress = CustomProgress.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        initViews();

    }

    private void initViews() {
        mProfileBtn = findViewById(R.id.admin_profile_btn);
        mProfileBtn.setOnClickListener(v -> openEditProfile());


        mCustomProgress = CustomProgress.getInstance();

        mOrdersRef = FirebaseDatabase.getInstance().getReference("Orders");


        mOrdersRecyclerView = findViewById(R.id.admin_orders_rv);
        RecyclerView.LayoutManager orderLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mOrdersRecyclerView.setLayoutManager(orderLayoutManager);
        mOrderAdapter = new OrderAdapter(this, mOrders);

        refreshLayout = findViewById(R.id.admin_user_orders_swipe_down);
        refreshLayout.setOnRefreshListener(this);

        mCustomProgress.showProgress(this, "Loading..!", true);

        getAllOrders();

    }

    private void getAllOrders() {


        mOrdersRef.orderByChild("orderDate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mOrders.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot childSnap : snapshot.getChildren()) {
                        mOrders.add(childSnap.getValue(OrderDataModel.class));
                    }

                    Collections.sort(mOrders, new Comparator<OrderDataModel>() {
                        @Override
                        public int compare(OrderDataModel p1, OrderDataModel p2) {
                            return p1.getOrderNumber() - p2.getOrderNumber(); // Ascending
                        }
                    });


                    showOrders();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    public void showOrders() {
        mCustomProgress.hideProgress();

        mOrderAdapter = new OrderAdapter(this, mOrders);
        mOrderAdapter.setOnOrderClickListener(new OrderAdapter.OnOrderClickListener() {
            @Override
            public void OnOrderClicked(int position, String orderKey) {
                Intent openEditNote = new Intent(AdminHome.this, ViewOrderDetails.class);
                openEditNote.putExtra("viewOrderKey", mOrders.get(position).getOrderKey());
                startActivity(openEditNote);
            }
        });
        mOrdersRecyclerView.setAdapter(mOrderAdapter);
        mOrderAdapter.notifyDataSetChanged();

        mCustomProgress.hideProgress();
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllOrders();
    }

    @Override
    public void onRefresh() {
        getAllOrders();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    public void onBackPressed() {
        if (true) {
            new AlertDialog.Builder(this)
                    .setMessage("Exit Application?")
                    .setCancelable(false)
                    .setPositiveButton("Yes, Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            AdminHome.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("No, Stay here", null)
                    .show();
        }

    }

    private void openEditProfile() {
        Intent intent = new Intent(AdminHome.this, EditProfile.class);
        startActivity(intent);
    }


}