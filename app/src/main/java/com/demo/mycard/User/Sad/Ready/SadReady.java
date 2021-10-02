package com.demo.mycard.User.Sad.Ready;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.mycard.Adapters.CategoryAdapter;
import com.demo.mycard.Adapters.ImagesAdapter;
import com.demo.mycard.R;
import com.demo.mycard.Utils.CustomProgress;
import com.demo.mycard.Utils.TinyDB;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class SadReady extends AppCompatActivity implements CategoryAdapter.OnCatClick {

    TinyDB tinyDB;

    private final String TAG = "SadReady";
    private final String DEATH_KEY = "Death";
    private final String SICK_KEY = "Sick";

    Handler handler = new Handler();
    int delay = 1500;

    TextView mBackBtn;

    RecyclerView imagesRecyclerView, categoryRecyclerView;
    ArrayList<String> categoryItems = new ArrayList<>();
    ArrayList<String> imagesItems = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    ImagesAdapter imagesAdapter;
    CustomProgress customProgress;

    StorageReference mImagesRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sad_ready);

        initViews();

    }


    private void initViews() {
        mImagesRef = FirebaseStorage.getInstance().getReference().child("Sad");
        customProgress = CustomProgress.getInstance();
        customProgress.showProgress(this, "Loading...!", true);

        tinyDB = new TinyDB(getApplicationContext());

        mBackBtn = findViewById(R.id.sad_ready_back);
        mBackBtn.setOnClickListener(v -> finish());

        categoryItems = new ArrayList<>();
        categoryItems.add("Death");
        categoryItems.add("Sick");


        imagesRecyclerView = findViewById(R.id.sad_ready_images_rv);
        RecyclerView.LayoutManager imageLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        imagesRecyclerView.setLayoutManager(imageLayoutManager);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                customProgress.hideProgress();
                getImages(DEATH_KEY);
            }
        }, delay);

        categoryRecyclerView = findViewById(R.id.sad_ready_cat_rv);
        categoryAdapter = new CategoryAdapter(categoryItems, this, this);

        categoryRecyclerView.setAdapter(categoryAdapter);
        RecyclerView.LayoutManager categoryLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);


    }

    private void getImages(String folder) {
        imagesItems.clear();
        customProgress.showProgress(this, "Loading...!", true);
        imagesItems = tinyDB.getListString(folder);

        if (imagesItems.isEmpty()) {
            mImagesRef.child(folder).listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                @Override
                public void onSuccess(ListResult listResult) {
                    for (StorageReference file : listResult.getItems()) {
                        file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imagesItems.add(uri.toString());
                            }
                        });
                    }

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!imagesItems.isEmpty()) {
                                tinyDB.putListString(folder, imagesItems);
                                showImages(imagesItems);
                                Log.e(TAG, "getAllImages: " + imagesItems.size());

                            } else
                                handler.postDelayed(this, delay);
                        }
                    }, delay);
                }
            });
        } else {
            showImages(imagesItems);
        }
    }

    private void showImages(ArrayList<String> list) {
        imagesAdapter = new ImagesAdapter(list, this);
        try {
            imagesRecyclerView.setAdapter(imagesAdapter);
        } catch (Exception e) {
            Log.e(TAG, "showImages: " + e.getMessage().toString());
        }

        customProgress.hideProgress();
    }


    @Override
    public void onItemClick(int catNum) {
        switch (catNum) {
            case 0:
                getImages(DEATH_KEY);
                break;
            case 1:
                getImages(SICK_KEY);
                break;
        }
    }
}