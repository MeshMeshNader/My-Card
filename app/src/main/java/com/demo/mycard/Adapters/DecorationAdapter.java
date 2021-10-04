package com.demo.mycard.Adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.mycard.R;

import java.util.ArrayList;

public class DecorationAdapter extends RecyclerView.Adapter<DecorationAdapter.DecorationViewHolder> {

    OnDecorationClick onDecorationClick;
    ArrayList<Integer> mDecorationsList = new ArrayList<Integer>();
    Context context;
    int p;
    boolean isHappy;
    TypedArray decorationArray;

    public DecorationAdapter(Context context, boolean isHappy ,OnDecorationClick onDecorationClick) {
        this.context = context;
        this.onDecorationClick = onDecorationClick;
        this.isHappy = isHappy;
        if (isHappy)
            decorationArray = context.getApplicationContext().getResources().obtainTypedArray(R.array.happy_decorations);
        else
            decorationArray = context.getApplicationContext().getResources().obtainTypedArray(R.array.sad_decorations);

        for (int i = 0; i < decorationArray.length(); i++) {
            mDecorationsList.add(decorationArray.getResourceId(i, 0));
        }
    }

    @NonNull
    @Override
    public DecorationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_decoration, parent, false);

        DecorationViewHolder decorationViewHolder = new DecorationViewHolder(view);

        return decorationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DecorationViewHolder holder, int position) {

        holder.mDecorationImage.setImageResource(mDecorationsList.get(position));
        holder.mDecorationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p = position;
                notifyDataSetChanged();
                onDecorationClick.onDecorationItemClick(mDecorationsList.get(position));
                if (p == position)
                    holder.mDecorationImage.setBackground(context.getResources().getDrawable(R.drawable.button_clicked_happy));
            }
        });

        if (p != position)
            holder.mDecorationImage.setBackground(context.getResources().getDrawable(R.drawable.button_clicked_sad));
    }

    @Override
    public int getItemCount() {
        return mDecorationsList.size();
    }

    public interface OnDecorationClick {
        void onDecorationItemClick(Integer decoration);
    }


    public class DecorationViewHolder extends RecyclerView.ViewHolder {

        ImageView mDecorationImage;

        public DecorationViewHolder(@NonNull View itemView) {
            super(itemView);

            mDecorationImage = itemView.findViewById(R.id.decoration_item);

        }
    }
}
