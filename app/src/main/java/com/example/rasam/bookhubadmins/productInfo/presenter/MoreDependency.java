package com.example.rasam.bookhubadmins.productInfo.presenter;

import com.example.rasam.bookhubadmins.main.Bussiness.MainIntractorImple;
import com.example.rasam.bookhubadmins.main.entity.MainCachManager;
import com.example.rasam.bookhubadmins.main.presenter.MainDependency;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DAQ;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.RequestManager;
import com.example.rasam.bookhubadmins.productInfo.bussiness.MoreIntractorImple;

/**
 * Created by R.Arabzadeh Taktell on 7/24/2018.
 */

public class MoreDependency {
    private static DAQ daq;
    private static RequestManager requestManager;
    private static MoreIntractorImple moreIntractorImple;



    public static MoreIntractorImple inject() {
        if (moreIntractorImple == null) {
            moreIntractorImple = new MoreIntractorImple(getDataBAseManager(), getRequetManger(), getCachMager());
        }
        return moreIntractorImple;
    }

    private static RequestManager getRequetManger() {
        if (requestManager == null) {
            requestManager = new RequestManager();
        }

        return requestManager;
    }

    private static DAQ getDataBAseManager() {
        if (daq == null) {
            daq = new DAQ();
        }

        return daq;
    }

    private static MainCachManager getCachMager() {
        return MainDependency.getCachMager();
    }
}
