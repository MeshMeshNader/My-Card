package com.demo.mycard.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.mycard.DataModels.OrderDataModel;
import com.demo.mycard.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Context context;
    ArrayList<OrderDataModel> ordersList = new ArrayList<>();
    OnOrderClickListener mListener;

    public OrderAdapter(Context context, ArrayList<OrderDataModel> ordersList) {
        this.context = context;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);

        OrderViewHolder orderViewHolder = new OrderViewHolder(view, mListener);

        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.mUserName.setText("Name : " + ordersList.get(position).getUserName());
        holder.mOrderNumber.setText("Order Number : " + ordersList.get(position).getOrderNumber());

        switch (ordersList.get(position).getOrderStatus()) {
            case "On Request":
                holder.spinner.setSelection(0);
                break;
            case "Delivery":
                holder.spinner.setSelection(1);
                break;
            case "Delivered":
                holder.spinner.setSelection(2);
                break;
            default:
                holder.spinner.setSelection(2);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }


    public void setOnOrderClickListener(OnOrderClickListener listener) {
        mListener = listener;
    }


    public interface OnOrderClickListener {
        void OnOrderClicked(int position, String orderKey);

    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView mUserName, mOrderNumber;

        String[] status = {"On Request", "Delivery", "Delivered"};
        Spinner spinner;
        ArrayAdapter arrayAdapter;

        public OrderViewHolder(@NonNull View itemView, final OnOrderClickListener listener) {
            super(itemView);

            mUserName = itemView.findViewById(R.id.order_item_user_name);
            mOrderNumber = itemView.findViewById(R.id.order_item_order_number);
            spinner = itemView.findViewById(R.id.order_item_status_spinner);


            arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, status);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.OnOrderClicked(position, ordersList.get(position).getOrderKey());
                        }
                    }
                }
            });


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    DatabaseReference mOrderRef;
                    mOrderRef = FirebaseDatabase.getInstance().getReference("Orders");
                    mOrderRef.child(ordersList.get(getAdapterPosition()).getOrderKey()).child("orderStatus")
                            .setValue(status[position]).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.e("OrderAdapter", "onComplete: Doneeeeeeeeee");
                        }
                    });

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }
    }
}
