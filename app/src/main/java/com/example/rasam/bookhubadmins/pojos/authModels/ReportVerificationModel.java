package com.example.rasam.bookhubadmins.pojos.authModels;

public class ReportVerificationModel extends BaseAuthModel {
    /**
     * the code that has been sen to you by sms
     */
    private String code;

    public ReportVerificationModel(String deviceType, String UUID, String verifyCode) {
        super(deviceType, UUID);
        this.code = verifyCode;
    }

    @Override
    public String toString() {
        return code;
    }
}
