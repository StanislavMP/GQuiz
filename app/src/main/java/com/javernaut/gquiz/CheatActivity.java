package com.javernaut.gquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CheatActivity extends LoggingActivity {

    private static final String KEY_CORRECT_ANSWER = "key_correct_answer";
    private static final String KEY_CHEAT_USED = "key_cheat_used";

    private boolean cheatUsed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null) {
            cheatUsed = savedInstanceState.getBoolean(KEY_CHEAT_USED);
            if (cheatUsed) {
                setResult(Activity.RESULT_OK);
            }
        }

        findViewById(R.id.show_correct_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean correctAnswer = getIntent().getBooleanExtra(KEY_CORRECT_ANSWER, false);

                TextView correctAnswerView = findViewById(R.id.correct_answer);
                correctAnswerView.setText(String.valueOf(correctAnswer));
                cheatUsed = true;
                setResult(Activity.RESULT_OK);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_CHEAT_USED, cheatUsed);
    }

    public static Intent makeIntent(Context context, boolean correctAnswer) {
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(KEY_CORRECT_ANSWER, correctAnswer);
        return intent;
    }
}
