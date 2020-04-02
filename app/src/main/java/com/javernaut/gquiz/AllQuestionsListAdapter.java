package com.javernaut.gquiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllQuestionsListAdapter extends RecyclerView.Adapter<QuestionViewHolder>  {
    private Question[] QUESTION_BANK;

    public AllQuestionsListAdapter(Question[] QUESTION_BANK) {
        this.QUESTION_BANK = QUESTION_BANK;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.bindTo(QUESTION_BANK[position]);
    }

    @Override
    public int getItemCount() {
        return QUESTION_BANK.length;
    }
}
