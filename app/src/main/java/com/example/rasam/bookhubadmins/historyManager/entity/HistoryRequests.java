package com.example.rasam.bookhubadmins.historyManager.entity;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public interface HistoryRequests {
    void getDeletedAddHistory(String token, OnRequestDone<List<Ads>> onRequestDone);
    void getPromotedAdsHistory(String token, OnRequestDone<List<Ads>> onRequestDone);
}
