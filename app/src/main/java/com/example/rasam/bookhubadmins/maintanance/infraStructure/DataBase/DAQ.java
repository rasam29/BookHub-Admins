package com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase;

import com.example.rasam.bookhubadmins.contactUs.entity.ContactUsDAO;
import com.example.rasam.bookhubadmins.historyManager.entity.HistoryDataBaseManger;
import com.example.rasam.bookhubadmins.main.entity.MainDataBaseActions;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.pojos.AuthKey;
import com.example.rasam.bookhubadmins.pojos.ContactUsMassagePayLoad;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class DAQ implements AuthKeyDAO, MainDataBaseActions, ContactUsDAO, HistoryDataBaseManger {


    @Override
    public void deleteAuthKey(OnDAOJobFinish<AuthKey> onDone) {

    }

    @Override
    public void getAuthKey(OnDAOJobFinish<AuthKey> onDone) {
        DataBaseModel<AuthKey> dataBaseModel;
        try {
            List<AuthKey> authKeys = SQLite.select().
                    from(AuthKey.class).queryList();
            if (authKeys.size() > 0) {
                onDone.onDone(new DataBaseModel<>(authKeys.get(0),true));
            } else if (authKeys.size() == 0) {
                onDone.onDone(new DataBaseModel<>(null,true));
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {


        }

    }

    @Override
    public void insertAuthKey(String token, OnDAOJobFinish<AuthKey> onDAO) {
        try {
            AuthKey authKey = new AuthKey(token);
            if (authKey.exists()){
                authKey.update();
            }else {
                authKey.save();
            }

            onDAO.onDone(new DataBaseModel<>(authKey, true));
        } catch (Exception e) {
            e.printStackTrace();
            onDAO.onDone(new DataBaseModel<>(e.getCause()));
        }
    }


    @Override
    public void saveDeletedAds(Ads ads, OnDAOJobFinish<Void> onDAOJobFinish) {
        try {
            ads.setPromoted(false);
            ads.save();
            onDAOJobFinish.onDone(new DataBaseModel<Void>(true));
        } catch (Exception e) {
            onDAOJobFinish.onDone(new DataBaseModel<Void>(e.getCause()));
        }
    }

    @Override
    public void savePromotedAds(Ads ads, OnDAOJobFinish<Void> onDAOJobFinish) {
        try {
            ads.setPromoted(true);
            ads.save();
            onDAOJobFinish.onDone(new DataBaseModel<Void>(true));
        } catch (Exception e) {
            onDAOJobFinish.onDone(new DataBaseModel<Void>(e.getCause()));
        }
    }


    @Override
    public void getPromotedAds(OnDAOJobFinish<List<Ads>> onDAOJobFinish) {

    }

    @Override
    public void getDeletedAds(OnDAOJobFinish<List<Ads>> onDAOJobFinish) {

    }

    @Override
    public void saveMassageToDataBase(ContactUsMassagePayLoad payLoad, OnDAOJobFinish<Void> onDAOJobFinish) {

    }
}
