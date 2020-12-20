package com.noured.topquiz.model;

import java.util.List;

/*
 *  Created by Nour Eddine ZEKAOUI - OpenClassromProject on 17/12/2020
 */

public class Question {

    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question(String question, List<String> choiceList, int answerIndex){
        this.setQuestion(question);
        this.setChoiceList(choiceList);
        this.setAnswerIndex(answerIndex);
    }

    public String getQuestion() {
        return mQuestion;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public void setChoiceList(List<String> choiceList) {
        mChoiceList = choiceList;
    }

    public void setAnswerIndex(int answerIndex) {
        mAnswerIndex = answerIndex;
    }
}
