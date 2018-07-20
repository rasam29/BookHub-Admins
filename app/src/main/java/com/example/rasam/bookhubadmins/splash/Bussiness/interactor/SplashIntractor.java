package com.example.rasam.bookhubadmins.splash.Bussiness.interactor;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AuthKeyDAO;


import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.SplashRequests;
import com.example.rasam.bookhubadmins.pojos.AuthKey;
import com.example.rasam.bookhubadmins.pojos.UpdateModel;
import com.example.rasam.bookhubadmins.splash.Bussiness.SplashViewState;

public class SplashIntractor implements SplashIntractorFacade {

    SplashRequests requestManager;
    AuthKeyDAO daq;


    private static final int OK_HTTP_CODE_UPDATE = 200;


    public SplashIntractor(SplashRequests requestManager, AuthKeyDAO daq) {
        this.requestManager = requestManager;
        this.daq = daq;
    }

    @Override
    public void checkForUpdate(final OnIntractor<SplashViewState> onIntractor) {


        requestManager.checkForUpdate(new OnRequestDone<UpdateModel>() {
            @Override
            public void onResponse(ResponseModel<UpdateModel> responseModel) {
                if (responseModel.getThrowable() != null) {
                    onIntractor.onDone(new SplashViewState.OnNetError(responseModel.getThrowable()));
                } else if (responseModel.getStatusCode() == OK_HTTP_CODE_UPDATE) {
                    if (responseModel.getData() != null) {
                        if (responseModel.getData().isHaveUpdate()) {
                            onIntractor.onDone(new SplashViewState.OnForceUpdate(responseModel.getData(), true));
                        } else {
                            onIntractor.onDone(new SplashViewState.NoUpdateState());
                        }
                    } else {
                        onIntractor.onDone(new SplashViewState.OnNetError(null));
                    }
                }
            }
        });
    }


    @Override
    public void checkForToken(final OnIntractor<SplashViewState> onIntractor) {
        daq.getAuthKey(new OnDAOJobFinish<AuthKey>() {
            @Override
            public void onDone(DataBaseModel<AuthKey> dataBaseModel) {
                if (dataBaseModel.getThrowable() == null && dataBaseModel.isOk()) {
                    if (dataBaseModel.getData() == null || dataBaseModel.getData().getKey() == null || dataBaseModel.getData().getKey().isEmpty()) {
                        onIntractor.onDone(new SplashViewState.OnDoesntToken());
                    } else onIntractor.onDone(new SplashViewState.OnHaveToken());
                }
            }
        });
    }


}

