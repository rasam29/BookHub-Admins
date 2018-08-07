package com.example.rasam.bookhubadmins;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;

/**
 * Created by R.Arabzadeh Taktell on 7/23/2018.
 */

public interface SimpleAdvertismentAction {
    void deleteAds(String token,String adsId, OnRequestDone<Void> onRequestDone);
    void promoteAds(String token,String adsId, OnRequestDone<Void> onRequestDone);
}
