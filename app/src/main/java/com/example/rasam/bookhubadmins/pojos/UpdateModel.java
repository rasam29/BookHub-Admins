package com.example.rasam.bookhubadmins.pojos;

public class UpdateModel {
    private boolean haveUpdate;
    /**
     * specify the reason why we have update
     */
    private String reason;

    private String newVersion;


    public UpdateModel(boolean haveUpdate, String reason, String newVersion) {
        this.haveUpdate = haveUpdate;
        this.reason = reason;
        this.newVersion = newVersion;
    }


    public boolean isHaveUpdate() {
        return haveUpdate;
    }

    public void setHaveUpdate(boolean haveUpdate) {
        this.haveUpdate = haveUpdate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }
}
