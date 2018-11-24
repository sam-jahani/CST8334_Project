package com.example.jaha0025.cst8334_project;

import android.content.Context;

import java.util.ArrayList;

public class UserAct {
    private int uaId;
    private int uId;
    private int aId;
    private String drawFile;
    private String answer;
    boolean complete;
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

    public void setUaId(int uaId) {
        this.uaId = uaId;
    }

}
