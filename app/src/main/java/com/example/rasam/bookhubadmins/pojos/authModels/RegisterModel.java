package com.example.rasam.bookhubadmins.pojos.authModels;

public class RegisterModel extends BaseAuthModel {

    private String notificationToken;

    public RegisterModel(String uuid, String deviceType, String notificationToken) {
        super(deviceType, uuid);

        this.notificationToken = notificationToken;
    }

}
