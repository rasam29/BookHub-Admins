package com.example.rasam.bookhubadmins.pojos;

/**
 * Created by R.Arabzadeh Taktell on 7/31/2018.
 */

public class AdminMassageReports {
    private String title;
    private String massage;
    private boolean isSeenFrom;
    private String massageFromAdmin;


    public AdminMassageReports(String title, String massage, boolean isSeenFrom, String massageFromAdmin) {
        this.title = title;
        this.massage = massage;
        this.isSeenFrom = isSeenFrom;
        this.massageFromAdmin = massageFromAdmin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public boolean isSeenFrom() {
        return isSeenFrom;
    }

    public void setSeenFrom(boolean seenFrom) {
        isSeenFrom = seenFrom;
    }

    public String getMassageFromAdmin() {
        return massageFromAdmin;
    }

    public void setMassageFromAdmin(String massageFromAdmin) {
        this.massageFromAdmin = massageFromAdmin;
    }

}
