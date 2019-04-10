package com.example.orb.Model;

public class Restaurants
{
    private String NAME, MANAGERNAME, LOCATION, PHONE, IMAGE;

    public Restaurants(){
    }

    public Restaurants(String NAME, String MANAGERNAME, String LOCATION, String PHONE, String IMAGE) {
        this.NAME = NAME;
        this.MANAGERNAME = MANAGERNAME;
        this.LOCATION = LOCATION;
        this.PHONE = PHONE;
        this.IMAGE = IMAGE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getMANAGERNAME() {
        return MANAGERNAME;
    }

    public void setMANAGERNAME(String MANAGERNAME) {
        this.MANAGERNAME = MANAGERNAME;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }
    public String getIMAGE() {
        return NAME;
    }

    public void setIMAGE(String NAME) {
        this.NAME = NAME;
    }
}

