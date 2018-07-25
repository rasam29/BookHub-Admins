package com.example.rasam.bookhubadmins.productInfo.bussiness;

import com.example.rasam.bookhubadmins.SimpleAdvertismentAction;
import com.example.rasam.bookhubadmins.main.entity.MainCachManager;
import com.example.rasam.bookhubadmins.main.entity.MainDataBaseActions;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;
import com.example.rasam.bookhubadmins.productInfo.presenter.MoreDetailsState;

/**
 * Created by R.Arabzadeh Taktell on 7/23/2018.
 */

public class MoreIntractorImple implements MoreDetailsIntractorFacade {

    private MainDataBaseActions mainDataBaseActions;
    private SimpleAdvertismentAction requestManger;
    private MainCachManager mainCachManager;


    public MoreIntractorImple(MainDataBaseActions mainDataBaseActions, SimpleAdvertismentAction requestManger,MainCachManager mainCachManager) {
        this.mainDataBaseActions = mainDataBaseActions;
        this.requestManger = requestManger;
        this.mainCachManager = mainCachManager;
    }


    @Override
    public void loadCach(OnIntractor<MoreDetailsState> onIntractor) {
        if (mainCachManager.getSelectedAdvertisment() == null) {
            throw new IllegalArgumentException("ads is null");
        } else
            onIntractor.onDone(new MoreDetailsState.LoadCachState(mainCachManager.getSelectedAdvertisment()));
    }


    @Override
    public void promote(final OnIntractor<MoreDetailsState> onIntractor) {
        final Ads ads = mainCachManager.getSelectedAdvertisment();
        requestManger.promoteAds(ads.getAdvertismentID(), new OnRequestDone<Void>() {
            @Override
            public void onResponse(ResponseModel<Void> responseModel) {
                if (responseModel.getThrowable() == null) {
                    if (responseModel.getStatusCode() == 200) {
                        mainDataBaseActions.savePromotedAds(ads, new OnDAOJobFinish<Void>() {
                            @Override
                            public void onDone(DataBaseModel<Void> dataBaseModel) {
                                if (dataBaseModel.getThrowable() == null) {
                                    onIntractor.onDone(new MoreDetailsState.PromoteState());
                                }
                            }
                        });
                    }
                } else {
                    onIntractor.onDone(new MoreDetailsState.NetError());
                }
            }
        });
    }

    @Override
    public void delete(final OnIntractor<MoreDetailsState> onIntractor) {
        final Ads ads = mainCachManager.getSelectedAdvertisment();
        requestManger.deleteAds(ads.getAdvertismentID(), new OnRequestDone<Void>() {
            @Override
            public void onResponse(ResponseModel<Void> responseModel) {
                if (responseModel.getThrowable() == null) {
                    if (responseModel.getStatusCode() == 200) {
                        mainDataBaseActions.saveDeletedAds(ads, new OnDAOJobFinish<Void>() {
                            @Override
                            public void onDone(DataBaseModel<Void> dataBaseModel) {
                                if (dataBaseModel.getThrowable() == null) {
                                    onIntractor.onDone(new MoreDetailsState.DeleteState());
                                }
                            }
                        });
                    }
                } else {
                    onIntractor.onDone(new MoreDetailsState.NetError());
                }
            }
        });

    }
}
