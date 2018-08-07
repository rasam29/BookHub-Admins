package com.example.rasam.bookhubadmins.historyManager.bussiness.pureLogic;

import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public class NetHistoryLogic {
    private ResponseModel<List<Ads>> responseModel;

    public NetHistoryLogic(ResponseModel<List<Ads>> responseModel) {
        this.responseModel = responseModel;
    }


    private boolean isNetOk() {
        if (responseModel.getThrowable() == null) {
            return true;
        } else return false;
    }


    private boolean serverProprlyResponded() {
        if (responseModel.getStatusCode() == 200 || responseModel.getStatusCode() == 204) {
            return true;
        } else return false;
    }

     public boolean RequestSuccessFul(){
        if (isNetOk() && serverProprlyResponded()){
            return true;
        }else return false;
    }


    public boolean hasHistory() {
        if (responseModel.getStatusCode() != 204) {
            return true;
        } else {
            return false;
        }
    }
}
