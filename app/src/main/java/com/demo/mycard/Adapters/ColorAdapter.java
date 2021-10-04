package com.demo.mycard.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.mycard.R;

import java.util.ArrayList;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {

    OnColorClick onColorClick;
    ArrayList<Integer> mColorsList = new ArrayList<Integer>();
    Context context;

    public ColorAdapter(Context context , OnColorClick onColorClick) {
        this.context = context;
        this.onColorClick = onColorClick;
        String[] colorTxt = context.getApplicationContext().getResources().getStringArray(R.array.colors);
        for (String s : colorTxt) {
            int newColor = Color.parseColor(s);
            mColorsList.add(newColor);
        }

    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_color, parent, false);

        ColorViewHolder colorViewHolder = new ColorViewHolder(view);

        return colorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {

        Drawable buttonDrawable = holder.mColor.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        DrawableCompat.setTint(buttonDrawable, mColorsList.get(position));
        holder.mColor.setBackground(buttonDrawable);
        holder.mColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onColorClick.onColorItemClick(mColorsList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mColorsList.size();
    }

        public interface OnColorClick {
            void onColorItemClick(Integer color);
        }


    public class ColorViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout mColor;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);

            mColor = itemView.findViewById(R.id.color_item);

        }
    }
}
