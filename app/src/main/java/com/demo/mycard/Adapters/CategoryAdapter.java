package com.demo.mycard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.mycard.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CatViewHolder> {

    ArrayList<String> catItems = new ArrayList<>();
    Context context;
    OnCatClick onCatClick;
    int p;


    public CategoryAdapter(ArrayList<String> catItems, Context context, OnCatClick onCatClick) {
        this.catItems = catItems;
        this.context = context;
        this.onCatClick = onCatClick;
    }


    @NonNull
    @Override
    public CategoryAdapter.CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categories, parent, false);
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CatViewHolder holder, int position) {

        holder.mCatName.setText(catItems.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = position;
                notifyDataSetChanged();
                onCatClick.onItemClick(position);
                if (p == position)
                    holder.mBackground.setBackground(context.getResources().getDrawable(R.drawable.button_clicked_happy));
            }
        });

        if (p != position)
            holder.mBackground.setBackground(context.getResources().getDrawable(R.drawable.button_unclicked));
    }

    @Override
    public int getItemCount() {
        return catItems.size();
    }


    public interface OnCatClick {
        void onItemClick(int catNum);
    }


    public class CatViewHolder extends RecyclerView.ViewHolder {

        TextView mCatName;
        RelativeLayout mBackground;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);

            mCatName = itemView.findViewById(R.id.cat_name);
            mBackground = itemView.findViewById(R.id.cat_layout_background);

        }
    }
}
