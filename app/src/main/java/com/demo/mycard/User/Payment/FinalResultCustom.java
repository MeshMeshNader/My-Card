package com.demo.mycard.User.Payment;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.demo.mycard.R;

public class FinalResultCustom extends AppCompatActivity {

    private final String TAG = "FinalResultCustom";

    CardView mCardDimCV;
    RelativeLayout mCardBackgroundRL;
    ImageView mImage1, mImage2, mImage3, mImage4;
    TextView mCardText, mBackBtn;
    Button mPayBtn;


    String font, text;
    int color, image, width, height;
    float widthDP, heightDP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);


        Intent card = getIntent();
        text = card.getStringExtra("cardText");
        font = card.getStringExtra("cardFont");
        color = card.getIntExtra("cardColor", -16777216);
        image = card.getIntExtra("cardImage", 2131230855);
        width = card.getIntExtra("cardWidth", 300);
        height = card.getIntExtra("cardHeight", 150);

        initViews();

    }

    private void initViews() {
        mCardDimCV = findViewById(R.id.final_result_custom_card_dim);
        mCardBackgroundRL = findViewById(R.id.final_result_custom_background);
        mImage1 = findViewById(R.id.final_result_custom_image1);
        mImage2 = findViewById(R.id.final_result_custom_image2);
        mImage3 = findViewById(R.id.final_result_custom_image3);
        mImage4 = findViewById(R.id.final_result_custom_image4);
        mCardText = findViewById(R.id.final_result_custom_center_text);
        mBackBtn = findViewById(R.id.final_result_custom_back);
        mPayBtn = findViewById(R.id.final_result_pay_btn);

        mBackBtn.setOnClickListener(v -> onBackPressed());
        mPayBtn.setOnClickListener(v -> goToPayActivity());

        Drawable backgroundDrawable = mCardBackgroundRL.getBackground();
        backgroundDrawable = DrawableCompat.wrap(backgroundDrawable);
        DrawableCompat.setTint(backgroundDrawable, color);
        mCardBackgroundRL.setBackground(backgroundDrawable);

        widthDP = width * this.getResources().getDisplayMetrics().density;
        heightDP = height * this.getResources().getDisplayMetrics().density;

        RelativeLayout.LayoutParams cardLayout = (RelativeLayout.LayoutParams) mCardDimCV.getLayoutParams();
        cardLayout.width = (int) widthDP;
        cardLayout.height = (int) heightDP;
        cardLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mCardDimCV.setLayoutParams(cardLayout);

        mImage1.setImageResource(image);
        mImage2.setImageResource(image);
        mImage3.setImageResource(image);
        mImage4.setImageResource(image);

        mCardText.setText(text);
        getFont();
    }

    private void getFont() {
        Typeface typeface;
        switch (font) {
            case "Gemunu":
                typeface = ResourcesCompat.getFont(this, R.font.gemunu_libre);
                break;
            case "Playfair":
                typeface = ResourcesCompat.getFont(this, R.font.player_fair);
                break;
            case "Ephesis":
                typeface = ResourcesCompat.getFont(this, R.font.ephisis);
                break;
            case "Yaldevi":
                typeface = ResourcesCompat.getFont(this, R.font.yaldevi);
                break;
            default:
                typeface = ResourcesCompat.getFont(this, R.font.roboto_medium);
                break;
        }

        mCardText.setTypeface(typeface);

    }

    private void goToPayActivity() {
        Intent card = new Intent(FinalResultCustom.this, CompletePayment.class);
        card.putExtra("cardFont", font);
        card.putExtra("cardColor", color);
        card.putExtra("cardImage", image);
        card.putExtra("cardWidth", widthDP);
        card.putExtra("cardHeight", heightDP);
        card.putExtra("cardText", text);
        card.putExtra("cardType" , true);
        startActivity(card);
    }
}