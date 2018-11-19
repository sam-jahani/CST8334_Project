package com.example.jaha0025.cst8334_project;

import android.content.Context;

import java.util.ArrayList;

public class UserAct {
    int uaId;
    int uId;
    int aId;
    String drawFile;
    String answer;
    boolean complete;
    static final int NUMBER_OF_ACTS = 10;

    public UserAct() { }

    public UserAct(int uId, int aId) {
        this.uId = uId;
        this.aId = aId;
    }

    public int getUaId() {
        return uaId;
    }

    public int getuId() {
        return uId;
    }

    public int getaId() {
        return aId;
    }

    public String getDrawFile() {
        return drawFile;
    }

    public void setDrawFile(String drawFile) {
        this.drawFile = drawFile;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public static ArrayList<UserAct> insertUserActs(Context context, int uId){
        ArrayList<UserAct> userActs = new ArrayList<UserAct>();
        for(int aId = 1;aId <= NUMBER_OF_ACTS;aId++)
        {
            userActs.add(new UserAct(uId, aId));
        }
        return userActs;
    }
}
