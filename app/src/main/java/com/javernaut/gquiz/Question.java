package com.javernaut.gquiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    private int questionResId;
    private boolean correctAnswer;

    public Question(int questionResId, boolean correctAnswer) {
        this.questionResId = questionResId;
        this.correctAnswer = correctAnswer;
    }

    protected Question(Parcel in) {
        questionResId = in.readInt();
        correctAnswer = in.readByte() != 0;
    }

    public int getQuestionResId() {
        return questionResId;
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(questionResId);
        dest.writeByte((byte) (correctAnswer ? 1 : 0));
    }
}