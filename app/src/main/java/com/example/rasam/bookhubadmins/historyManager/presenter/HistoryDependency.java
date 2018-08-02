package com.example.rasam.bookhubadmins.historyManager.presenter;

import com.example.rasam.bookhubadmins.contactUs.bussiness.ContactUsIntractor;
import com.example.rasam.bookhubadmins.historyManager.bussiness.HistoryIntractor;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DAQ;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.RequestManager;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public class HistoryDependency {
    private static DAQ daq;
    private static RequestManager requestManager;
    private static HistoryIntractor historyIntractor;

    public static HistoryIntractor inject() {
        if (historyIntractor == null)
            historyIntractor = new HistoryIntractor(getRequetManger(),getRequetManger(),getDataBAseManager());

        return historyIntractor;
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
}
