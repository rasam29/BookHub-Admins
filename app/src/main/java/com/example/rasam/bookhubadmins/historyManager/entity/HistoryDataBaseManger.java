package com.example.rasam.bookhubadmins.historyManager.entity;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public interface HistoryDataBaseManger {
    void getPromotedAds(OnDAOJobFinish<List<Ads>> onDAOJobFinish);
    void getDeletedAds(OnDAOJobFinish<List<Ads>> onDAOJobFinish);
}
