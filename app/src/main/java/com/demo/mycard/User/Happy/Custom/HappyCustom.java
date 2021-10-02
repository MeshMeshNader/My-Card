package com.demo.mycard.User.Happy.Custom;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.mycard.R;

public class HappyCustom extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    Button mCreateBtn;
    String[] fonts = {"Roboto", "Playfair", "Ephesis", "Yaldevi", "Gemunu"};
    Spinner spinner;
    ArrayAdapter arrayAdapter;

    String font, text , image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_custom);

        initViews();


    }

    private void initViews() {

        mCreateBtn = findViewById(R.id.happy_custom_create);
        spinner = findViewById(R.id.happy_custom_spinner);
        spinner.setOnItemSelectedListener(this);

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,fonts);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        font = fonts[position];
        Toast.makeText(getApplicationContext(),fonts[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}