package com.example.rasam.bookhubadmins.splash.Bussiness.interactor;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AuthKeyDAO;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.SplashRequests;
import com.example.rasam.bookhubadmins.splash.Bussiness.SplashViewState;

public class SplashIntractor implements SplashIntractorFacade {

    private static final int OK_HTTP_CODE_UPDATE = 200;
    SplashRequests requestManager;
    AuthKeyDAO daq;


    public SplashIntractor(SplashRequests requestManager, AuthKeyDAO daq) {
        this.requestManager = requestManager;
        this.daq = daq;
    }

    @Override
    public void checkForUpdate(final OnIntractor<SplashViewState> onIntractor) {


        requestManager.checkForUpdate(responseModel -> {
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
            }else {
                onIntractor.onDone(new SplashViewState.OnNetError(new Throwable("response came from server is not 200")));
            }
        });
    }


    @Override
    public void checkForToken(final OnIntractor<SplashViewState> onIntractor) {
        daq.getAuthKey(dataBaseModel -> {
            if (dataBaseModel.getThrowable() == null && dataBaseModel.isOk()) {
                if (dataBaseModel.getData() == null || dataBaseModel.getData().getKey() == null || dataBaseModel.getData().getKey().isEmpty()) {
                    onIntractor.onDone(new SplashViewState.OnDoesntToken());
                } else onIntractor.onDone(new SplashViewState.OnHaveToken());
            } else {
                throw new IllegalArgumentException("Problem with DAQ");
            }
        });
    }


}

