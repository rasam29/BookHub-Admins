package com.example.rasam.bookhubadmins.pojos.authModels;

public class AuthKeyVerify extends BaseAuthModel{
    private String authKey;

    public AuthKeyVerify(String deviceType, String UUID,String authKey) {
        super(deviceType, UUID);
        this.authKey = authKey;
    }

    public String getAuthKey() {
        return authKey;
    }
}
