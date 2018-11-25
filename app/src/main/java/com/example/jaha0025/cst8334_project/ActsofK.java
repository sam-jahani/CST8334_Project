package com.example.jaha0025.cst8334_project;

public class ActsofK {



    private String aokTitle;
    private String id;
    private String completed;

    public ActsofK(String id, String aokTitle, String completed){
        this.id=id;
        this.aokTitle=aokTitle;
        this.completed = completed;
    }
    public String getId() {
        return id;
    }

    public String getAokTitle() {
        return aokTitle;
    }

    public String getCompleted() {
        return completed;
    }
}
