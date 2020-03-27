package com.javernaut.gquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import androidx.annotation.Nullable;

public class MainActivity extends LoggingActivity {

    private static final int REQUEST_CODE_CHEAT = 42;
    private static final int REQUEST_CODE_CHEAT_2 = 423;

    private static final String KEY_CURRENT_QUESTION_INDEX = "key_current_question_index";
    private static final String KEY_ANSWERED_QUESTION_ARRAY = "key_answeredQuestion_Array";
    private static final String KEY_ANSWERED_QUESTION_COUNT = "KEY_ANSWERED_QUESTION_COUNT";
    private static final String KEY_ANSWERED_QUESTION_CORRECT_COUNT = "KEY_ANSWERED_QUESTION_CORRECT_COUNT";

    private Button trueButton;
    private Button falseButton;
    private Button cheatButton;
    private Button statsButton;
    private Button nextButton;
    private TextView questionView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int currentQuestionIndex = 0;
    private int answeredCount = 0;
    private int answeredCorrectCount = 0;
    private int[] answeredQuestion = new int[mQuestionBank.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt(KEY_CURRENT_QUESTION_INDEX);
            answeredQuestion = savedInstanceState.getIntArray(KEY_ANSWERED_QUESTION_ARRAY);
            answeredCount = savedInstanceState.getInt(KEY_ANSWERED_QUESTION_COUNT);
            answeredCorrectCount = savedInstanceState.getInt(KEY_ANSWERED_QUESTION_CORRECT_COUNT);
        }

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        statsButton = findViewById(R.id.stats_button);
        cheatButton = findViewById(R.id.cheat_button);
        questionView = findViewById(R.id.question);

        applyCurrentQuestion();

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(false);
            }
        });

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        CheatActivity.makeIntent(MainActivity.this, getCurrentQuestion().getCorrectAnswer()),
                        REQUEST_CODE_CHEAT
                );
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        StatsActivity.makeIntent(MainActivity.this,
                        String.format("Отвечено %d/%d вопросов\n Правильных ответов: %d", answeredCount, answeredQuestion.length, answeredCorrectCount))
                );
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to next question
                if (currentQuestionIndex == mQuestionBank.length - 1) {
                    currentQuestionIndex = 0;
                } else {
                    currentQuestionIndex++;
                }

                // apply question
                applyCurrentQuestion();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_QUESTION_INDEX, currentQuestionIndex);
        outState.putIntArray(KEY_ANSWERED_QUESTION_ARRAY, answeredQuestion);
        outState.putInt(KEY_ANSWERED_QUESTION_COUNT, answeredCount);
        outState.putInt(KEY_ANSWERED_QUESTION_CORRECT_COUNT, answeredCorrectCount);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (resultCode == Activity.RESULT_OK) {
                showToast(R.string.judgment_toast);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void applyCurrentQuestion() {
        questionView.setText(getCurrentQuestion().getQuestionResId());
    }

    private Question getCurrentQuestion() {
        return mQuestionBank[currentQuestionIndex];
    }

    private void onAnswerSelected(boolean currentAnswer) {
        boolean wasTheAnswerCorrect = currentAnswer == getCurrentQuestion().getCorrectAnswer();
        if (answeredQuestion[currentQuestionIndex] == 0 ) {
            answeredCount++;
        }
        if (wasTheAnswerCorrect && answeredQuestion[currentQuestionIndex] < 1){
            answeredCorrectCount++;
        }
        if (!wasTheAnswerCorrect && answeredQuestion[currentQuestionIndex] == 1){
            answeredCorrectCount--;
        }
        answeredQuestion[currentQuestionIndex] = (wasTheAnswerCorrect ? 1 : -1);
        showToast(wasTheAnswerCorrect ? R.string.correct_toast : R.string.incorrect_toast);
    }

    private void showToast(int textId) {
        Toast.makeText(MainActivity.this, textId, Toast.LENGTH_SHORT).show();
    }
}
