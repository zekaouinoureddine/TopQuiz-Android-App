package com.noured.topquiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.noured.topquiz.R;
import com.noured.topquiz.model.QuestionBank;
import com.noured.topquiz.model.Question;

import java.util.Arrays;

import static java.lang.System.out;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestion;
    private Button mAnswer1;
    private Button mAnswer2;
    private Button mAnswer3;
    private Button mAnswer4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;
    private int mNumberOfQuestion;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "BUNDLE_STATE_SCORE";
    public static final String BUNDLE_STATE_QUESTION = "BUNDLE_STATE_QUESTION";

    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity::onCreate");

        // All questions will be stocked in the variable below
        mQuestionBank = this.generateQuestions();

        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestion = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        }else{
            mScore = 0;
            mNumberOfQuestion = 4;
        }

        mEnableTouchEvents = true;

        // Wire widgets
        mQuestion = (TextView) findViewById(R.id.activity_game_question_text);
        mAnswer1 = (Button) findViewById(R.id.activity_game_answer1_btn);
        mAnswer2 = (Button) findViewById(R.id.activity_game_answer2_btn);
        mAnswer3 = (Button) findViewById(R.id.activity_game_answer3_btn);
        mAnswer4 = (Button) findViewById(R.id.activity_game_answer4_btn);

        // Use the tag property to 'name' the buttons
        mAnswer1.setTag(0);
        mAnswer2.setTag(1);
        mAnswer3.setTag(2);
        mAnswer4.setTag(3);

        mAnswer1.setOnClickListener(this);
        mAnswer2.setOnClickListener(this);
        mAnswer3.setOnClickListener(this);
        mAnswer4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestion);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if(responseIndex == mCurrentQuestion.getAnswerIndex()){
            // Good answer
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
        }else{
            // Wrong answer
            Toast.makeText(this, "Wrong Answer !", Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents  = false;

        // Wait a little bit, then show the next question to the user.
        // So, the user can have a look on what he did 'wrong' or 'correct'.
        new Handler().postDelayed(new Runnable() {

            // For demanding the system to wait 2 second.
            @Override
            public void run() {
                mEnableTouchEvents = true;

                // If this is the last question, ends the Quiz.
                if(--mNumberOfQuestion == 0){
                    // End the Game
                    endGame();

                // Else, Display the next question.
                }else{
                    mCurrentQuestion  = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        }, 2000); // LENGTH_SHORT is usually 2000 ms (2 second long).
    }

    // Once the Used touched the screen, this method will be executed.
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setMessage("Your score is " + mScore)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // End the activity
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    private void displayQuestion(final Question question){
        mQuestion.setText(question.getQuestion());
        mAnswer1.setText(question.getChoiceList().get(0));
        mAnswer2.setText(question.getChoiceList().get(1));
        mAnswer3.setText(question.getChoiceList().get(2));
        mAnswer4.setText(question.getChoiceList().get(3));
    }

    public QuestionBank generateQuestions(){
        Question question1 = new Question("What is the name of the current french president?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        Question question2 = new Question("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Question question3 = new Question("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));
    }

    @Override
    protected void onStart() {
        super.onStart();

        out.println("GameActivity::onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("GameActivity::onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("GameActivity::onDestroy");
    }
}