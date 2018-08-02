package com.example.rasam.bookhubadmins.productInfo.presenter;

import com.example.rasam.bookhubadmins.pojos.ads.Ads;
import com.example.rasam.bookhubadmins.productInfo.view.MoreDetailsView;

/**
 * Created by R.Arabzadeh Taktell on 7/23/2018.
 */

public interface MoreDetailsState {

    class LoadCachState implements MoreDetailsState{
        Ads ads;

        public LoadCachState(Ads ads) {
            this.ads = ads;
        }

        public Ads getAds() {
            return ads;
        }
    }

    class PromoteState implements MoreDetailsState{}


    class DeleteState implements MoreDetailsState{}

    class NetError implements MoreDetailsState{}

}
