package com.example.orb.Model;

public class Users {
    private String FIRST_NAME, LAST_NAME,  PIN, TELRAQAM;

    public Users(){

    }


    public Users(String FIRST_NAME, String LAST_NAME, String PIN, String TELRAQAM) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.PIN = PIN;
        this.TELRAQAM = TELRAQAM;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getTELRAQAM() {
        return TELRAQAM;
    }

    public void setTELRAQAM(String TELRAQAM) {
        this.TELRAQAM = TELRAQAM;
    }
}