package com.example.rasam.bookhubadmins.main.presenter;

import com.example.rasam.bookhubadmins.main.Bussiness.MainIntractorImple;
import com.example.rasam.bookhubadmins.main.entity.MainCachManager;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DAQ;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.RequestManager;

/**
 * Created by R.Arabzadeh Taktell on 7/21/2018.
 */

public class MainDependency {

    private static DAQ daq;
    private static RequestManager requestManager;
    private static MainIntractorImple mainIntractorImple;
    private static MainCachManager mainCachManager;


    public static MainIntractorImple inject() {
        if (mainIntractorImple == null) {
            mainIntractorImple = new MainIntractorImple(getDataBAseManager(), getRequetManger(), getCachMager(),getDataBAseManager());
        }
        return mainIntractorImple;
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

    public static MainCachManager getCachMager() {
        if (mainCachManager == null) {
            mainCachManager = new MainCachManager();
        }

        return mainCachManager;
    }
}
