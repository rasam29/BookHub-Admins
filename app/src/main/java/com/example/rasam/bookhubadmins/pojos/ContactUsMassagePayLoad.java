package com.example.rasam.bookhubadmins.pojos;

public class ContactUsMassagePayLoad {
    private String title;
    private String massage;

    public ContactUsMassagePayLoad(String title, String massage) {
        this.title = title;
        this.massage = massage;
    }

    public ContactUsMassagePayLoad() {
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
}
