package com.demo.mycard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.mycard.R;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {

    ArrayList<String> imagesList = new ArrayList<>();
    Context context;


    public ImagesAdapter(ArrayList<String> imagesList, Context context) {
        this.imagesList = imagesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ImagesAdapter.ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ready_images, parent, false);

        ImagesViewHolder imagesViewHolder = new ImagesViewHolder(view);

        return imagesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesAdapter.ImagesViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(imagesList.get(position)).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;

        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.ready_image_item);

        }

    }
}
