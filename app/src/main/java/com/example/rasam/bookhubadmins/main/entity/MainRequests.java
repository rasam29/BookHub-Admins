package com.example.rasam.bookhubadmins.main.entity;

import com.example.rasam.bookhubadmins.SimpleAdvertismentAction;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

/**
 * Created by R.Arabzadeh on 7/18/2018.
 */
public interface MainRequests extends SimpleAdvertismentAction{



    void refreshList(String token,OnRequestDone<List<Ads>> onRequestDone);
    void getNextPsge(String token,int lastItem,OnRequestDone<List<Ads>> onRequestDone);
}
