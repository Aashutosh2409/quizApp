package com.example.aashutoshsingh.quizapp;

//we have created customClass here for our app requirement
public class TrueFalse {
    private int qId;
    private boolean mAns;

    //now we have to make constructor
    public TrueFalse (int questionId, boolean TrueOrFalse){
    qId=questionId;
    mAns=TrueOrFalse;
    }
    //getter and setter
    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public boolean isAns() {
        return mAns;
    }

    public void setAns(boolean ans) {
        mAns = ans;
    }
}
