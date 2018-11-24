package com.example.jaha0025.cst8334_project;

import android.app.Application;

public class MyApplication extends Application {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
