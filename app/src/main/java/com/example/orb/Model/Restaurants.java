package com.example.orb.Model;

public class Restaurants
{
    private String NAME, MANAGERNAME, LOCATION, PHONE, IMAGE, tekshir, PID;

    public Restaurants(){
    }

    public Restaurants(String NAME, String MANAGERNAME, String LOCATION, String PHONE, String IMAGE, String tekshir, String PID) {
        this.NAME = NAME;
        this.PID=PID;
        this.MANAGERNAME = MANAGERNAME;
        this.tekshir=tekshir;
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

    public String gettekshir() {
        return tekshir;
    }

    public void settekshir(String tekshir) {
        this.tekshir = tekshir;
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
        return IMAGE;
    }

    public void setIMAGE(String NAME) {
        this.IMAGE = IMAGE;
    }

    public String getPID() {
        return IMAGE;
    }

    public void setPID(String NAME) {
        this.PID = PID;
    }

}

