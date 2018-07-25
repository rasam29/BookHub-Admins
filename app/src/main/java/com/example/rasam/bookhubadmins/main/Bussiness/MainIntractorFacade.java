package com.example.rasam.bookhubadmins.main.Bussiness;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/18/2018.
 */
public interface MainIntractorFacade {

    void deleteAdvertisment(Ads advertismentID, OnIntractor<MainState> onIntractor);

    void promoteAdvertisment(Ads advertismentID, OnIntractor<MainState> onIntractor);



    void getMoreAds(OnIntractor<MainState> onIntractor);

    void refreshList(OnIntractor<MainState> onIntractor);

    void cachAd(Ads ads);
}
