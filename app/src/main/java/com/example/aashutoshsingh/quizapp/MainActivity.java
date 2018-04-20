package com.example.aashutoshsingh.quizapp;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mTButton;
    Button mFButton;
    TextView mQuestionTextView;
    int mIndex;
    int mQuest;
    TextView mScoreTextView;
    ProgressBar mProgressBar;
    int mScore;

    //creating question bank...Array.
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true)
    };
    final int PROGRESS_INC = (int) Math.ceil(100.00 / mQuestionBank.length);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //casting of view to button
        mTButton = findViewById(R.id.true_button);
        mFButton = (Button) findViewById(R.id.false_button);

        mScoreTextView = findViewById(R.id.score);
        mProgressBar = findViewById(R.id.progress_bar);

        mQuestionTextView = findViewById((R.id.question_text_view));
//        mQuestionTextView.setText("Birds");//this will set bird on app start....

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
            mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
        } else {
            mScore = 0;
            mIndex = 0;
        }

        //we need to extract the first element of the QuestionBank...
        //TrueFalse firstQuestion=mQuestionBank[mIndex];
        //int quest=firstQuestion.getqId();
        //above 2 line has been depreciated to one line...

        mQuest = mQuestionBank[mIndex].getqId();
        mQuestionTextView.setText(mQuest);

//        mScoreTextView.setText("Score" + mScore + "/" + mQuestionBank.length);

        //2 ways for assigning onclickerListner
        //FIRST WAY...
        //TYPE...............NAME....
        View.OnClickListener myListener = new View.OnClickListener() {
            //callback
            @Override
            public void onClick(View v) {
                Log.d("msg", "True button pressed");
                //First Way of creating toast msg
//                Toast myToast = Toast.makeText(getApplicationContext(), "True pressed", Toast.LENGTH_SHORT);
//                myToast.show();
//                updateQuest();
                checkAnswer(true);
                updateQuest();
            }
        };
        //now set the listener on the true button with the set on click listener method
        mTButton.setOnClickListener(myListener);
        //mFButton.setOnClickListener(myListener);
//--------------------------------------------------------------------------
        //SECOND WAY...this is kinda java way of programming...you will see such kinda code often...
        //so here the listener on the false button is anonymous...
        mFButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("msg", "False button Pressed..");
                //second way of creating toast msg...like anonymous button was created without name...
                //it us vey very handy  for components that are used only once in your project...
//                Toast.makeText(getApplicationContext(), "False pressed", Toast.LENGTH_SHORT).show();
                checkAnswer(false);
                updateQuest();

            }
        });
//
//        //now we have to make new question...
//        //to create new object
//        //TYPE V-NAME=NEW callinTheConstructor(2 parameters)
//        TrueFalse eq1 = new TrueFalse(R.string.question_1, true);

    }

    private void updateQuest() {
        mIndex = (mIndex + 1) % mQuestionBank.length; //13...modulus will work like infinte loop...repeating itself..
        if (mIndex == 0) {
//            AlertDialog.Builder a=new AlertDialog.Builder(getApplicationContext());

            //new way og getting context...which we will mainly find on stack overflow...
            //keyword this refers to the current object...which over here is our main activity....
            AlertDialog.Builder a = new AlertDialog.Builder(this);

            a.setTitle("Game Over");
            a.setCancelable(false);
            a.setMessage("You scored " + mScore + " points");
            a.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            a.show();
        }

        mQuest = mQuestionBank[mIndex].getqId();
        mQuestionTextView.setText(mQuest);
        //now our progress bar will fill up a little bit every time the user presses a button....
        mProgressBar.incrementProgressBy(PROGRESS_INC);
        //for Score
        mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
    }

    private void checkAnswer(boolean userChoice) {
        boolean correctAns = mQuestionBank[mIndex].isAns();
        if (userChoice == correctAns) {
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mScore = mScore + 1;
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState: for saving the state of the app when activity is being restated due to screen rotation
        //that way that information is sored inside the bundle is as a key-value-pair...
        outState.putInt("ScoreKey", mScore);
        //here we are storing the information in the bundle under a key called index key..
        outState.putInt("IndexKey", mIndex);
    }
}
