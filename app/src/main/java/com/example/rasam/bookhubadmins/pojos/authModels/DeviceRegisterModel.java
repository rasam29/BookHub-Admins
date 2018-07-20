package com.example.rasam.bookhubadmins.pojos.authModels;

public class DeviceRegisterModel extends BaseAuthModel {

    private String deviceModel;
    private int androidOsVersion;

    public DeviceRegisterModel(String deviceModel, String deviceType, String uuid, int androidOsVersion) {
        super(deviceType, uuid);
        this.deviceModel = deviceModel;
        this.androidOsVersion = androidOsVersion;
    }


}
