package com.javernaut.gquiz;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionViewHolder extends RecyclerView.ViewHolder {

    private final TextView questionView;
    private final CheckBox answerView;

    public QuestionViewHolder(@NonNull View itemView) {
        super(itemView);

        questionView = itemView.findViewById(R.id.question);
        answerView = itemView.findViewById(R.id.answer);
    }

    public void bindTo(Question question) {
        questionView.setText(question.getQuestionResId());
        answerView.setChecked(question.getCorrectAnswer());
    }
}
