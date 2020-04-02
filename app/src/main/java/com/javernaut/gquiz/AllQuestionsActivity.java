package com.javernaut.gquiz;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.Nullable;
import android.os.Parcelable;
import java.util.Arrays;

public class AllQuestionsActivity extends LoggingActivity {

    private Question[] QUESTION_BANK;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allquestions);

        Parcelable[] parcelableArray = getIntent().getParcelableArrayExtra(Question.class.getSimpleName());
        QUESTION_BANK = Arrays.copyOf(parcelableArray, parcelableArray.length, Question[].class);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AllQuestionsListAdapter(QUESTION_BANK));


        // TODO set layout with RecyclerView inside

        // TODO set LayoutManager to the RecyclerView

        // TODO pass an Adapter to the RecyclerView. Use MainActivity.QUESTION_BANK as an argument
    }
}
