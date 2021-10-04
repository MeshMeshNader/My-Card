package com.demo.mycard.User.Sad.Custom;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.mycard.Adapters.ColorAdapter;
import com.demo.mycard.Adapters.DecorationAdapter;
import com.demo.mycard.R;
import com.demo.mycard.User.Payment.FinalResultCustom;

public class SadCustom extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener, ColorAdapter.OnColorClick, DecorationAdapter.OnDecorationClick {


    private final String TAG = "SadCustom";
    Button mCreateBtn;
    String[] fonts = {"Roboto", "Playfair", "Ephesis", "Yaldevi", "Gemunu"};
    Spinner spinner;
    ArrayAdapter arrayAdapter;

    RelativeLayout mSelectedColor;
    String font, text;
    Integer color = null, image = null;

    EditText mWidth, mHeight, mText;
    TextView mSelectedColorText, mBackBtn;

    RecyclerView colorRecyclerView;
    ColorAdapter colorAdapter;

    RecyclerView decorationRecyclerView;
    DecorationAdapter decorationAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sad_custom);

        initViews();

    }

    private void initViews() {

        mSelectedColor = findViewById(R.id.sad_custom_color_selected);
        mWidth = findViewById(R.id.sad_custom_width_et);
        mHeight = findViewById(R.id.sad_custom_height_et);
        mText = findViewById(R.id.sad_custom_text_et);
        mSelectedColorText = findViewById(R.id.sad_custom_color_selected_text);

        mCreateBtn = findViewById(R.id.sad_custom_create);
        mCreateBtn.setOnClickListener(v -> createCard());
        spinner = findViewById(R.id.sad_custom_spinner);
        spinner.setOnItemSelectedListener(this);

        mBackBtn = findViewById(R.id.sad_custom_back);
        mBackBtn.setOnClickListener(v -> finish());

        colorRecyclerView = findViewById(R.id.sad_custom_color_rv);
        colorAdapter = new ColorAdapter(this, this);
        colorRecyclerView.setAdapter(colorAdapter);
        RecyclerView.LayoutManager colorLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        colorRecyclerView.setLayoutManager(colorLayoutManager);


        decorationRecyclerView = findViewById(R.id.sad_custom_decoration_rv);
        decorationAdapter = new DecorationAdapter(this, false, this);
        decorationRecyclerView.setAdapter(decorationAdapter);
        RecyclerView.LayoutManager decorationLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        decorationRecyclerView.setLayoutManager(decorationLayoutManager);


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, fonts);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

    }

    private void createCard() {
        if (validate()) {

            Intent card = new Intent(SadCustom.this, FinalResultCustom.class);
            card.putExtra("cardFont", font);
            card.putExtra("cardColor", color);
            card.putExtra("cardImage", image);
            card.putExtra("cardWidth", Integer.parseInt(mWidth.getText().toString()));
            card.putExtra("cardHeight", Integer.parseInt(mHeight.getText().toString()));
            card.putExtra("cardText", mText.getText().toString());
            startActivity(card);
        }
    }

    private boolean validate() {
        if (color == null) {
            Toast.makeText(this, "Please select the color", Toast.LENGTH_LONG).show();
            return false;
        } else if (mWidth.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter the width", Toast.LENGTH_LONG).show();
            return false;
        } else if (mHeight.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter the height", Toast.LENGTH_LONG).show();
            return false;
        } else if (mText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter the text", Toast.LENGTH_LONG).show();
            return false;
        } else if (image == null) {
            Toast.makeText(this, "Please select the decoration image", Toast.LENGTH_LONG).show();
            return false;

        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        font = fonts[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onColorItemClick(Integer color) {
        this.color = color;
        Drawable buttonDrawable = mSelectedColor.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        DrawableCompat.setTint(buttonDrawable, color);
        mSelectedColor.setBackground(buttonDrawable);
        mSelectedColorText.setVisibility(View.VISIBLE);
        mSelectedColor.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDecorationItemClick(Integer decoration) {
        this.image = decoration;
    }


}