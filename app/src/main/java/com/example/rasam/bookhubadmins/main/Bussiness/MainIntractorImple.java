package com.example.rasam.bookhubadmins.main.Bussiness;


import com.example.rasam.bookhubadmins.main.entity.MainCachManager;
import com.example.rasam.bookhubadmins.main.entity.MainDataBaseActions;
import com.example.rasam.bookhubadmins.main.entity.MainRequests;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;
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
    public void deleteAdvertisment(final Ads advertisment, final OnIntractor<MainState> onIntractor) {
        mainRequests.deleteAds(advertisment.getAdvertismentID(), new OnRequestDone<Void>() {
            @Override
            public void onResponse(ResponseModel<Void> responseModel) {
                if (responseModel.getThrowable() != null) {
                    onIntractor.onDone(new MainState.NetError());
                } else if (responseModel.getStatusCode() == 200) {
                    dbOperations.saveDeletedAds(advertisment, new OnDAOJobFinish<Void>() {
                        @Override
                        public void onDone(DataBaseModel<Void> dataBaseModel) {
                            if (dataBaseModel.getThrowable() == null) {
                                onIntractor.onDone(new MainState.DeleteState(advertisment));
                            }
                        }
                    });
                }
            }
        });

    }


    @Override
    public void promoteAdvertisment(final Ads advertisment, final OnIntractor<MainState> onIntractor) {
        mainRequests.promoteAds(advertisment.getAdvertismentID(), new OnRequestDone<Void>() {
            @Override
            public void onResponse(ResponseModel<Void> responseModel) {
                if (responseModel.getThrowable() != null) {
                    onIntractor.onDone(new MainState.NetError());
                } else if (responseModel.getStatusCode() == 200) {
                    dbOperations.savePromotedAds(advertisment, new OnDAOJobFinish<Void>() {
                        @Override
                        public void onDone(DataBaseModel<Void> dataBaseModel) {
                            if (dataBaseModel.getThrowable() == null) {
                                onIntractor.onDone(new MainState.PromoteState(advertisment));
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void getMoreAds(final OnIntractor<MainState> onIntractor) {

        mainRequests.getNextPsge(mainCachManager.getLastItemNumber(), new OnRequestDone<List<Ads>>() {
            @Override
            public void onResponse(ResponseModel<List<Ads>> responseModel) {
                if (responseModel.getThrowable() != null) {
                    onIntractor.onDone(new MainState.NetError());
                } else if (responseModel.getStatusCode() == 200) {
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
                if (responseModel.getThrowable() != null) {
                    onIntractor.onDone(new MainState.NetError());
                } else if (responseModel.getStatusCode() == 200) {
                    onIntractor.onDone(new MainState.RefreshState(responseModel.getData()));
                }
            }
        });
    }
}
