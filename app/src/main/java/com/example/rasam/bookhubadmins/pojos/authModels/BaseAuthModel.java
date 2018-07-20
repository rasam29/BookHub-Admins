package com.example.rasam.bookhubadmins.pojos.authModels;

public class BaseAuthModel {
    private String deviceType;
    private String UUID;


    public BaseAuthModel(String deviceType, String UUID) {

        this.deviceType = deviceType;
        this.UUID = UUID;
    }


}
