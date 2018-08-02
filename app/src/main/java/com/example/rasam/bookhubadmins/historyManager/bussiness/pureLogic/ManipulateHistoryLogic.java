package com.example.rasam.bookhubadmins.historyManager.bussiness.pureLogic;

import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public class ManipulateHistoryLogic {


    private ResponseModel<Void> responseModel;

    public ManipulateHistoryLogic(ResponseModel<Void> responseModel) {
        this.responseModel = responseModel;
    }


    private boolean isNetOk() {
        if (responseModel.getThrowable() == null) {
            return true;
        } else return false;
    }


    private boolean serverProprlyResponded() {
        if (responseModel.getStatusCode() == 200) {
            return true;
        } else return false;
    }

    public boolean isAdvertismentDeleted() {
        if (isNetOk() && serverProprlyResponded()) {
            return true;
        } else return false;
    }

    public boolean isExpired(){
        if (responseModel.getStatusCode() == 205){
            return true;
        }else return false;
    }




}
