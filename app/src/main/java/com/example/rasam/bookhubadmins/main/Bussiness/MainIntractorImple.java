package com.example.rasam.bookhubadmins.main.Bussiness;


import com.example.rasam.bookhubadmins.main.entity.MainCachManager;
import com.example.rasam.bookhubadmins.main.entity.MainDataBaseActions;
import com.example.rasam.bookhubadmins.main.entity.MainRequests;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AuthKeyDAO;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.pojos.AuthKey;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

public class MainIntractorImple implements MainIntractorFacade {
    MainDataBaseActions dbOperations;
    MainRequests mainRequests;
    MainCachManager mainCachManager;
    AuthKeyDAO authKeyDAO;

    public MainIntractorImple(MainDataBaseActions dbOperations, MainRequests mainRequests, MainCachManager mainCachManager, AuthKeyDAO authKeyDAO) {
        this.dbOperations = dbOperations;
        this.mainRequests = mainRequests;
        this.mainCachManager = mainCachManager;
        this.authKeyDAO = authKeyDAO;
    }

    @Override
    public void deleteAdvertisment(final Ads advertisment, final OnIntractor<MainState> onIntractor) {
        authKeyDAO.getAuthKey(dataBaseModel -> {
            if (dataBaseModel.getThrowable() == null){
                mainRequests.deleteAds(dataBaseModel.getData().getKey(),advertisment.getAdvertismentID(), responseModel -> {
                    if (responseModel.getThrowable() != null) {
                        onIntractor.onDone(new MainState.NetError());
                    } else if (responseModel.getStatusCode() == 200) {
                        dbOperations.saveDeletedAds(advertisment, dataBaseModel1 -> {
                            if (dataBaseModel1.getThrowable() == null) {
                                onIntractor.onDone(new MainState.DeleteState(advertisment));
                            }
                        });
                    }
                });
            }else {
                throw new RuntimeException("dataBAseError");
            }
        });

    }


    @Override
    public void promoteAdvertisment(final Ads advertisment, final OnIntractor<MainState> onIntractor) {


        authKeyDAO.getAuthKey(dataBaseModel -> {
            if (dataBaseModel.getThrowable() == null){
                mainRequests.promoteAds(dataBaseModel.getData().getKey(),advertisment.getAdvertismentID(), responseModel -> {
                    if (responseModel.getThrowable() != null) {
                        onIntractor.onDone(new MainState.NetError());
                    } else if (responseModel.getStatusCode() == 200) {
                        dbOperations.savePromotedAds(advertisment, dataBaseModel2 -> {
                            if (dataBaseModel2.getThrowable() == null) {
                                onIntractor.onDone(new MainState.PromoteState(advertisment));
                            }
                        });
                    }
                });
            }else {
                throw new RuntimeException("dataBAseError");
            }
        });


    }

    @Override
    public void getMoreAds(final OnIntractor<MainState> onIntractor) {




        authKeyDAO.getAuthKey(dataBaseModel -> {
            if (dataBaseModel.getThrowable() == null){
                mainRequests.getNextPsge(dataBaseModel.getData().getKey(),mainCachManager.getLastItemNumber(), responseModel -> {
                    if (responseModel.getThrowable() != null) {
                        onIntractor.onDone(new MainState.NetError());
                    } else if (responseModel.getStatusCode() == 200) {
                        mainCachManager.incrementLIstItem(responseModel.getData().size());
                        onIntractor.onDone(new MainState.NextPaginationState(responseModel.getData()));
                    }
                });
            }else {
                throw new RuntimeException("dataBAseError");
            }
        });

    }

    @Override
    public void refreshList(final OnIntractor<MainState> onIntractor) {




        authKeyDAO.getAuthKey(dataBaseModel -> {
            if (dataBaseModel.getThrowable() == null){
                mainRequests.refreshList(dataBaseModel.getData().getKey(),responseModel -> {
                    if (responseModel.getThrowable() != null) {
                        onIntractor.onDone(new MainState.NetError());
                    } else if (responseModel.getStatusCode() == 200) {
                        onIntractor.onDone(new MainState.RefreshState(responseModel.getData()));
                        mainCachManager.overrideItemListCount(responseModel.getData().size());
                    }
                });
            }else {
                throw new RuntimeException("dataBAseError");
            }
        });

    }

    @Override
    public void cachAd(Ads ads) {
        mainCachManager.setSelectedAdvertismentID(ads);
    }
}
