package com.example.rasam.bookhubadmins.main.Bussiness;


import com.example.rasam.bookhubadmins.main.entity.MainCachManager;
import com.example.rasam.bookhubadmins.main.entity.MainDataBaseActions;
import com.example.rasam.bookhubadmins.main.entity.MainRequests;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

public class MainIntractorImple implements MainIntractorFacade {
    MainDataBaseActions dbOperations;
    MainRequests mainRequests;
    MainCachManager mainCachManager;

    public MainIntractorImple(MainDataBaseActions dbOperations, MainRequests mainRequests, MainCachManager mainCachManager) {
        this.dbOperations = dbOperations;
        this.mainRequests = mainRequests;
        this.mainCachManager = mainCachManager;
    }


    @Override
    public void deleteAdvertisment(Ads advertismentID, OnIntractor<MainState> onIntractor) {

    }

    @Override
    public void promoteAdvertisment(Ads advertismentID, OnIntractor<MainState> onIntractor) {

    }

    @Override
    public void getMoreAds(final OnIntractor<MainState> onIntractor) {

        mainRequests.getNextPsge(mainCachManager.getLastItemNumber(), new OnRequestDone<List<Ads>>() {
            @Override
            public void onResponse(ResponseModel<List<Ads>> responseModel) {
                if (responseModel.getThrowable() != null) {
                    onIntractor.onDone(new MainState.NetError());
                }else if (responseModel.getStatusCode() == 200){
                    onIntractor.onDone(new MainState.NextPaginationState(responseModel.getData()));
                }
            }
        });
    }

    @Override
    public void refreshList(final OnIntractor<MainState> onIntractor) {
        mainRequests.refreshList(new OnRequestDone<List<Ads>>() {
            @Override
            public void onResponse(ResponseModel<List<Ads>> responseModel) {
                if (responseModel.getThrowable()!= null){
                    onIntractor.onDone(new MainState.NetError());
                }else if (responseModel.getStatusCode() == 200){
                    onIntractor.onDone(new MainState.RefreshState(responseModel.getData()));
                }
            }
        });
    }
}
