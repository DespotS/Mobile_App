package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class playActivity extends AppCompatActivity {

    // Arrays containing questions, choices, and correct answers
    String[] question_list = {"Calculate the derivative of the function f(x) = 3x^2 + 2x + 1 with respect to x.",
            "Evaluate the definite integral from 0 to 1 of (2x + 3) dx.",
            "Find the limit as x approaches 2 of (x^2 - 4)/(x - 2).",
            "Determine the critical points of the function g(x) = x^3 - 6x^2 + 9x.",
            "Compute the second derivative of h(x) = sin(x) + cos(x)."};
    String[] choose_list = {"f'(x) = 0","f'(x) = 6x + 2"," f'(x) = 3x+2",
            "4","6","1",
            "2","infinite","4",
            "x = 1, 3","x = 0","x = 2, 5",
            "h''(x) = sin(x) - cos(x)","h''(x) = -sin(x) - cos(x)","h''(x) = sin(x) + cos(x)"};

    String[] correct_list = {"f'(x) = 6x + 2",
            "4",
            "4",
            "x = 1, 3",
            "h''(x) = -sin(x) - cos(x)"};


    TextView text_question;
    android.widget.Button btn_choose1, btn_choose2, btn_choose3, btn_next;
    int currentQuestion = 0;
    boolean isClicked =false;
    String valueChoose = "";
    android.widget.Button btn_click;
    private static final int REQUEST_CODE_PLAY_ACTIVITY = 123;

    // List to store user's answers
   static List<String> userAnswers = new ArrayList<>(); // To store user's answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Initialize UI components
        text_question = findViewById(R.id.text_question);
        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_next = findViewById(R.id.btn_next);

        // Set up the back button click listener
        findViewById(R.id.image_back).setOnClickListener(
                a -> finish()
        );
        // Load questions and choices
        remplirData();
    }

    // Method to populate questions and choices
    void remplirData() {
        text_question.setText(question_list[currentQuestion]);

        btn_choose1.setText(choose_list[currentQuestion * 3]);
        btn_choose2.setText(choose_list[currentQuestion * 3 + 1]);
        btn_choose3.setText(choose_list[currentQuestion * 3 + 2]);

        // Set up the click listener for the next button
        btn_next.setOnClickListener(
                view -> {
                    if (isClicked) {
                        // Check if the selected answer is correct
                        if (valueChoose.equals(correct_list[currentQuestion])) {
                            Toast.makeText(playActivity.this, "correct", Toast.LENGTH_LONG).show();
                            userAnswers.add("Correct: " + question_list[currentQuestion]);
                            btn_click.setBackgroundResource(R.drawable.background_btn_correct);
                        } else {
                            Toast.makeText(playActivity.this, "wrong", Toast.LENGTH_LONG).show();
                            userAnswers.add("Wrong: " + question_list[currentQuestion]);
                            btn_click.setBackgroundResource(R.drawable.background_btn_wrong);
                        }
                        // Delay to show feedback, then move to the next question
                        new Handler().postDelayed(() -> {
                            if (currentQuestion < question_list.length - 1) {
                                currentQuestion = currentQuestion + 1;
                                remplirData();
                                valueChoose = "";
                                btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);

                            }else{
                                // Save userAnswers and set a flag to indicate completion
                                saveUserAnswers();
                                Intent resultIntent = new Intent();
                                resultIntent.putStringArrayListExtra("userAnswers", (ArrayList<String>) userAnswers);
                                setResult(RESULT_OK, resultIntent);
                                finish();

                            }

                        }, 2000);
                    }
                }
        );
    }

    // Method to handle button clicks for answer choices
    public void ClickChoose(View view) {
        btn_click = (android.widget.Button) view;

        if (!isClicked) {
            chooseBtn();
        }else{
            btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
            chooseBtn();
        }

    }
    // Helper method to handle the selection of an answer choice
    void chooseBtn(){
        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isClicked = true;
        valueChoose = btn_click.getText().toString();
    }

    @Override
    protected void onPause() {
        // Save userAnswers when the activity is paused
        saveUserAnswers();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        saveUserAnswers();
        super.onDestroy();
    }

    private void saveUserAnswers() {
        // Save userAnswers in SharedPreferences
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userAnswers", TextUtils.join(",", userAnswers));
        editor.apply();
    }
}
