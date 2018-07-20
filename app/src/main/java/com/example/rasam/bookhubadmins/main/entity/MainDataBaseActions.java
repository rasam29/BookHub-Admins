package com.example.rasam.bookhubadmins.main.entity;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

/**
 * Created by R.Arabzadeh Taktell on 7/18/2018.
 */
public interface MainDataBaseActions {

    void saveDeletedAds(Ads ads, OnDAOJobFinish<Void> onDAOJobFinish);
    void savePromotedAds(Ads ads, OnDAOJobFinish<Void> onDAOJobFinish);

}
