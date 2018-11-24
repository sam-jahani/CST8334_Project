package com.example.jaha0025.cst8334_project;

public class User {


    int uId;
    String uLogin;
    String uPass;
    String uName;
    int uAge;
    String uGrade;
    String uAbout;
    private int uHead;
    private int uShirt;
    private int uPants;
    public User(){ }

    public User(String login, String pass){
        this.uLogin = login;
        this.uPass = pass;
    }
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public int getuAge() {
        return uAge;
    }

    public void setuAge(int uAge) {
        this.uAge = uAge;
    }

    public String getuGrade() {
        return uGrade;
    }

    public void setuGrade(String uGrade) {
        this.uGrade = uGrade;
    }

    public String getuAbout() {
        return uAbout;
    }

    public void setuAbout(String uAbout) {
        this.uAbout = uAbout;
    }
    public int getuId() {
        return uId;
    }

    public String getuLogin() {
        return uLogin;
    }

    public String getuPass() {
        return uPass;
    }

    public int getuHead() {
        return uHead;
    }

    public void setuHead(int uHead) {
        this.uHead = uHead;
    }

    public int getuShirt() {
        return uShirt;
    }

    public void setuShirt(int uShirt) {
        this.uShirt = uShirt;
    }

    public int getuPants() {
        return uPants;
    }

    public void setuPants(int uPants) {
        this.uPants = uPants;
    }
}
