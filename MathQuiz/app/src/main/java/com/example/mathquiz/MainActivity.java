package com.example.mathquiz;

import static com.example.mathquiz.R.id.btn_play;
import static com.example.mathquiz.playActivity.userAnswers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mathquiz.playActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method triggered when a button is clicked
    public void main_btn(View view) {
        int viewId = view.getId();

        // Check which button is clicked based on its ID
        if (viewId == R.id.btn_play) {
            // Start the playActivity when the "Play" button is clicked
            startActivity(new Intent(MainActivity.this, playActivity.class));
        } else if (viewId == R.id.btn_results) {
            // Start the resultsA activity only if the quiz is completed
            startActivity(new Intent(MainActivity.this, resultsA.class));
        } else if (viewId == R.id.btn_exit) {
            // Finish the activity and exit the application when the "Exit" button is clicked
            this.finishAffinity();
        }
    }

    // Request code to identify the result from the playActivity
    private static final int REQUEST_CODE_PLAY_ACTIVITY = 123;

    // Method to handle the result from activities started with startActivityForResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the result is from the playActivity with the correct request code
        if (requestCode == REQUEST_CODE_PLAY_ACTIVITY && resultCode == RESULT_OK) {
            // Handle the result from playActivity
            ArrayList<String> userAnswers = data.getStringArrayListExtra("userAnswers");
            // Perform any further processing with the user's answers
        }
    }
}
