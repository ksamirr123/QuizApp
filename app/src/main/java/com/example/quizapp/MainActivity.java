package com.example.quizapp;

import android.os.Bundle;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView questionstextview;
    TextView totalQuestiontextview;
    Button OPA, OPB, OPC, OPD;
    Button btn_submit;

    int score = 0;
    int totalQuestion = questionsanswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionstextview = findViewById(R.id.questions);
        totalQuestiontextview = findViewById(R.id.total_questions);
        OPA = findViewById(R.id.ans_a);
        OPB = findViewById(R.id.ans_b);
        OPC = findViewById(R.id.ans_c);
        OPD = findViewById(R.id.ans_d);
        btn_submit = findViewById(R.id.submit);

        OPA.setOnClickListener(this);
        OPB.setOnClickListener(this);
        OPC.setOnClickListener(this);
        OPD.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        totalQuestiontextview.setText("Total questions: " + totalQuestion);
        loadNewQuestion();
    }

    private void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }

        // Reset button colors to default
        OPA.setBackgroundColor(Color.WHITE);
        OPB.setBackgroundColor(Color.WHITE);
        OPC.setBackgroundColor(Color.WHITE);
        OPD.setBackgroundColor(Color.WHITE);

        // Load new question and choices
        questionstextview.setText(questionsanswer.question[currentQuestionIndex]);
        OPA.setText(questionsanswer.choices[currentQuestionIndex][0]);
        OPB.setText(questionsanswer.choices[currentQuestionIndex][1]);
        OPC.setText(questionsanswer.choices[currentQuestionIndex][2]);
        OPD.setText(questionsanswer.choices[currentQuestionIndex][3]);

        selectedAnswer = "";
    }

    private void finishQuiz() {
        String passStatus;
        if (score >= totalQuestion * 0.6) {
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your score is " + score + " out of " + totalQuestion)
                .setPositiveButton("Restart", (dialog, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.submit) {
            // Check if an answer has been selected
            if (!selectedAnswer.isEmpty()) {
                // Check if the selected answer is correct
                if (selectedAnswer.equals(questionsanswer.correctAnswers[currentQuestionIndex])) {
                    score++;
                }

                currentQuestionIndex++;
                loadNewQuestion();
            } else {
                // Show a message or handle when no answer is selected
                new AlertDialog.Builder(this)
                        .setTitle("No answer selected")
                        .setMessage("Please select an answer before submitting.")
                        .setPositiveButton("OK", null)
                        .show();
            }
        } else {
            // Handle answer selection
            selectedAnswer = clickedButton.getText().toString();
            OPA.setBackgroundColor(Color.WHITE);
            OPB.setBackgroundColor(Color.WHITE);
            OPC.setBackgroundColor(Color.WHITE);
            OPD.setBackgroundColor(Color.WHITE);

            // Highlight the selected answer
            clickedButton.setBackgroundColor(Color.YELLOW);
        }
    }
}
