package com.example.rasam.bookhubadmins.contactUs.presenter;

import com.example.rasam.bookhubadmins.contactUs.bussiness.ContactUsIntractor;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DAQ;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.RequestManager;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public class ContactDependency {

    private static DAQ daq;
    private static RequestManager requestManager;
    private static ContactUsIntractor contactUsIntractor;

    public static ContactUsIntractor inject() {
        if (contactUsIntractor == null)
            contactUsIntractor = new ContactUsIntractor(getDataBAseManager(), getRequetManger(),getDataBAseManager());

        return contactUsIntractor;
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
