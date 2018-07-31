package com.example.rasam.bookhubadmins.contactUs.bussiness;

import com.example.rasam.bookhubadmins.contactUs.entity.ContactUSRequest;
import com.example.rasam.bookhubadmins.contactUs.entity.ContactUsDAO;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactUsState;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AuthKeyDAO;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public class ContactUsIntractor implements ContactUsIntractorFacade {
    ContactUsDAO contactUsDAO;
    ContactUSRequest contactUSRequest;
    AuthKeyDAO authKeyDAO;

    public ContactUsIntractor(ContactUsDAO contactUsDAO, ContactUSRequest contactUSRequest, AuthKeyDAO authKeyDAO) {
        this.contactUsDAO = contactUsDAO;
        this.contactUSRequest = contactUSRequest;
        this.authKeyDAO = authKeyDAO;
    }

    @Override
    public void sendMassageToSupportTeam(String massage, final OnIntractor<ContactUsState> onIntractor) {
        authKeyDAO.getAuthKey(dataBaseModel -> {
            if (dataBaseModel.getThrowable() == null) {
                contactUSRequest.sendMassageToServer(dataBaseModel.getData().getKey(),massage, responseModel -> {
                    if (responseModel.getThrowable() != null) {
                        onIntractor.onDone(new ContactUsState.OnNetError());
                    } else if (responseModel.getStatusCode() == 200) {
                        contactUsDAO.saveMassageToDataBase(dataBaseModel1 -> {
                            if (dataBaseModel1.getThrowable() == null) {
                                onIntractor.onDone(new ContactUsState.MassageSentState());
                            } else {
                                throw new IllegalArgumentException();
                            }
                        });
                    } else {
                        onIntractor.onDone(new ContactUsState.OnNetError());
                    }
                });
            } else {
                throw new RuntimeException();
            }
        });
    }
}
