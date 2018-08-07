package com.example.rasam.bookhubadmins.historyManager.bussiness;

import android.view.View;

import com.example.rasam.bookhubadmins.SimpleAdvertismentAction;
import com.example.rasam.bookhubadmins.historyManager.bussiness.pureLogic.ManipulateHistoryLogic;
import com.example.rasam.bookhubadmins.historyManager.bussiness.pureLogic.NetHistoryLogic;
import com.example.rasam.bookhubadmins.historyManager.entity.HistoryRequests;
import com.example.rasam.bookhubadmins.historyManager.presenter.HistoryState;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AuthKeyDAO;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.pojos.AuthKey;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public class HistoryIntractor implements HistoryIntractorFacade {

    private SimpleAdvertismentAction simpleAdvertismentAction;
    private HistoryRequests historyRequests;

    private AuthKeyDAO authKeyDAO;


    private ManipulateHistoryLogic manipulateHistoryLogic;
    private NetHistoryLogic netHistoryLogic;


    public HistoryIntractor(SimpleAdvertismentAction simpleAdvertismentAction
            , HistoryRequests historyRequests
            , AuthKeyDAO authKeyDAO) {

        this.simpleAdvertismentAction = simpleAdvertismentAction;
        this.historyRequests = historyRequests;

        this.authKeyDAO = authKeyDAO;
    }

    @Override
    public void getDeletedAds(final OnIntractor<HistoryState> onIntractor) {

        authKeyDAO.getAuthKey(dataBaseModel -> {
            if (dataBaseModel.getThrowable() == null) {
                historyRequests.getDeletedAddHistory(dataBaseModel.getData().getKey(), responseModel -> {
                    netHistoryLogic = new NetHistoryLogic(responseModel);
                    if (netHistoryLogic.RequestSuccessFul()){
                        if (netHistoryLogic.hasHistory()){
                            onIntractor.onDone(new HistoryState.OnHistory(responseModel.getData()));
                        }else {
                            onIntractor.onDone(new HistoryState.OnEmptyHistory());
                        }
                    }else {
                        onIntractor.onDone(new HistoryState.OnNetError());
                    }
                });
            } else {
                throw new RuntimeException();
            }
        });
    }

    @Override
    public void getPromotedAds(OnIntractor<HistoryState> onIntractor) {
        authKeyDAO.getAuthKey(dataBaseModel -> {
            if (dataBaseModel.getThrowable() == null) {
                historyRequests.getPromotedAdsHistory(dataBaseModel.getData().getKey(), responseModel -> {
                    netHistoryLogic = new NetHistoryLogic(responseModel);
                    if (netHistoryLogic.RequestSuccessFul()){
                        if (netHistoryLogic.hasHistory()){
                            onIntractor.onDone(new HistoryState.OnHistory(responseModel.getData()));
                        }else {
                            onIntractor.onDone(new HistoryState.OnEmptyHistory());
                        }
                    }else {
                        onIntractor.onDone(new HistoryState.OnNetError());
                    }
                });
            } else {
                throw new RuntimeException();
            }
        });

    }







    public void promoteAdd(Ads ads, OnIntractor<HistoryState> onIntractor) {
        authKeyDAO.getAuthKey(dataBaseModel -> {
            if (dataBaseModel.getThrowable() == null){
                simpleAdvertismentAction.promoteAds(dataBaseModel.getData().getKey(),ads.getAdvertismentID(), responseModel -> {
                    manipulateHistoryLogic = new ManipulateHistoryLogic(responseModel);
                    if (manipulateHistoryLogic.isAdvertismentDeleted()){

                        onIntractor.onDone(new HistoryState.PromoteState());
                    }else if(manipulateHistoryLogic.isExpired()){
                        onIntractor.onDone(new HistoryState.AdExpired());
                    }else {
                        onIntractor.onDone(new HistoryState.OnNetError());
                    }
                });
            }else {
                throw new RuntimeException("dataBAse error here");
            }
        });

    }

    public void deleteAdd(Ads ads, OnIntractor<HistoryState> onIntractor) {


        authKeyDAO.getAuthKey(dataBaseModel -> {
            if (dataBaseModel.getThrowable() == null){
                simpleAdvertismentAction.deleteAds(dataBaseModel.getData().getKey(),ads.getAdvertismentID(), responseModel -> {
                    manipulateHistoryLogic = new ManipulateHistoryLogic(responseModel);
                    if (manipulateHistoryLogic.isAdvertismentDeleted()){

                        onIntractor.onDone(new HistoryState.DeleteState());
                    }else if(manipulateHistoryLogic.isExpired()){
                        onIntractor.onDone(new HistoryState.AdExpired());
                    }else {
                        onIntractor.onDone(new HistoryState.OnNetError());
                    }
                });
            }else {
                throw new RuntimeException("dataBAse error here");
            }
        });


    }


}
