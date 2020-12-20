package com.noured.topquiz.model;

import java.util.Collections;
import java.util.List;
/*
 *  Created by Nour Eddine ZEKAOUI - OpenClassromProject on 17/12/2020
 */

public class QuestionBank {
    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> questionList){
        mQuestionList = questionList;

        // Shuffle the question List
        Collections.shuffle(mQuestionList);
        mNextQuestionIndex = 0;
    }

    public Question getQuestion(){
        // Ensure we loop over the questions
        if (mNextQuestionIndex == mQuestionList.size()){
            mNextQuestionIndex = 0;
        }

        // Please note the post-incrementation
        return mQuestionList.get(mNextQuestionIndex++);
    }
}
