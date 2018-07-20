package com.example.rasam.bookhubadmins.main.entity;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

/**
 * Created by R.Arabzadeh on 7/18/2018.
 */
public interface MainRequests {


    void deleteAds(int adsId, OnRequestDone<Void> onRequestDone);
    void promoteAds(int adsId, OnRequestDone<Void> onRequestDone);
    void refreshList(OnRequestDone<List<Ads>> onRequestDone);
    void getNextPsge(int lastItem,OnRequestDone<List<Ads>> onRequestDone);
}
