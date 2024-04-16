package com.example.colorlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ColorDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_details);

        Intent intent  = getIntent();
        String colorName = (String)intent.getSerializableExtra("colorName");
        int colorCode = (int)intent.getSerializableExtra("colorCode");

        TextView textView = findViewById(R.id.color_details);
        textView.setText(colorName);
        textView.setBackgroundColor(colorCode);
    }
}