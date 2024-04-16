package com.example.mathquiz;

import static com.example.mathquiz.playActivity.userAnswers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class resultsA extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String userAnswersString = preferences.getString("userAnswers", "");

        // Display the results in a TextView or any other view
        TextView resultTextView = findViewById(R.id.resultTextView);

        if (!userAnswersString.isEmpty()) {
            // Split the stored answers and display them
            String[] userAnswersArray = userAnswersString.split(",");
            StringBuilder resultStringBuilder = new StringBuilder();
            for (String answer : userAnswersArray) {
                resultStringBuilder.append(answer).append("\n");
            }
            resultTextView.setText(resultStringBuilder.toString());
        } else {
            // Handle the case where there are no user answers
            resultTextView.setText("No results available.");
        }
        // Find the "Back to Main Menu" button
        android.widget.Button btnBackToMainMenu = findViewById(R.id.button_mm);

        // Set OnClickListener for the button
        btnBackToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the button click, for example, navigate back to the main menu
                Intent intent = new Intent(resultsA.this, MainActivity.class);
                startActivity(intent);
                finish();  // Optional: finish the current activity if needed
            }
        });
    }
}