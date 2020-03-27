package com.javernaut.gquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StatsActivity extends LoggingActivity {

    private static final String KEY_STATS_TEXT = "key_stats_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        TextView statsView = findViewById(R.id.textStats);
        statsView.setText(getIntent().getStringExtra(KEY_STATS_TEXT));
    }

    public static Intent makeIntent(Context context, int answeredCount, int questionCount, int answeredCorrectCount) {
        Intent intent = new Intent(context, StatsActivity.class);
        intent.putExtra(KEY_STATS_TEXT, String.format("Отвечено %d/%d вопросов\n Правильных ответов: %d", answeredCount, questionCount, answeredCorrectCount));
        return intent;
    }
}
