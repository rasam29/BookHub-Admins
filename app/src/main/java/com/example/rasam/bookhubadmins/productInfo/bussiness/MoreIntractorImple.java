package com.example.rasam.bookhubadmins.productInfo.bussiness;

import com.example.rasam.bookhubadmins.SimpleAdvertismentAction;
import com.example.rasam.bookhubadmins.main.entity.MainCachManager;
import com.example.rasam.bookhubadmins.main.entity.MainDataBaseActions;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AuthKeyDAO;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.pojos.AuthKey;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;
import com.example.rasam.bookhubadmins.productInfo.presenter.MoreDetailsState;

/**
 * Created by R.Arabzadeh Taktell on 7/23/2018.
 */

public class MoreIntractorImple implements MoreDetailsIntractorFacade {

    private MainDataBaseActions mainDataBaseActions;
    private SimpleAdvertismentAction requestManger;
    private MainCachManager mainCachManager;
    private AuthKeyDAO authKeyDAO;

    public MoreIntractorImple(MainDataBaseActions mainDataBaseActions, SimpleAdvertismentAction requestManger, MainCachManager mainCachManager, AuthKeyDAO authKey) {
        this.mainDataBaseActions = mainDataBaseActions;
        this.requestManger = requestManger;
        this.mainCachManager = mainCachManager;
        this.authKeyDAO = authKey;
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
       authKeyDAO.getAuthKey(dataBaseModel -> {
           if (dataBaseModel.getThrowable() == null){
               requestManger.promoteAds(dataBaseModel.getData().getKey(),ads.getAdvertismentID(), responseModel -> {
                   if (responseModel.getThrowable() == null) {
                       if (responseModel.getStatusCode() == 200) {
                           mainDataBaseActions.savePromotedAds(ads, dataBaseModel1 -> {
                               if (dataBaseModel1.getThrowable() == null) {
                                   onIntractor.onDone(new MoreDetailsState.PromoteState());
                               }
                           });
                       }
                   } else {
                       onIntractor.onDone(new MoreDetailsState.NetError());
                   }
               });
           }else {
               throw new IllegalArgumentException("dataBaseError");
           }
       });
    }

    @Override
    public void delete(final OnIntractor<MoreDetailsState> onIntractor) {
        final Ads ads = mainCachManager.getSelectedAdvertisment();


        authKeyDAO.getAuthKey(dataBaseModel -> {
            if (dataBaseModel.getThrowable() == null){
                requestManger.deleteAds(dataBaseModel.getData().getKey(),ads.getAdvertismentID(), responseModel -> {
                    if (responseModel.getThrowable() == null) {
                        if (responseModel.getStatusCode() == 200) {
                            mainDataBaseActions.saveDeletedAds(ads, dataBaseModel1 -> {
                                if (dataBaseModel1.getThrowable() == null) {
                                    onIntractor.onDone(new MoreDetailsState.DeleteState());
                                }
                            });
                        }
                    } else {
                        onIntractor.onDone(new MoreDetailsState.NetError());
                    }
                });
            }else {
                throw new IllegalArgumentException("dataBaseError");
            }
        });

    }
}
