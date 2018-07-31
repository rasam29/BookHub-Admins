package com.example.rasam.bookhubadmins.contactUs.presenter;

import com.example.rasam.bookhubadmins.contactUs.bussiness.ContactUsIntractor;
import com.example.rasam.bookhubadmins.contactUs.bussiness.ContactUsIntractorFacade;
import com.example.rasam.bookhubadmins.contactUs.entity.ContactUSRequest;
import com.example.rasam.bookhubadmins.contactUs.entity.ContactUsDAO;
import com.example.rasam.bookhubadmins.contactUs.view.ContactUsView;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * Created by R.Arabzadeh Taktell on 7/27/2018.
 */

public class ContactUsPresenter extends MvpBasePresenter<ContactUsView> implements OnIntractor<ContactUsState> {
    ContactUsView contactUsView;
    ContactUsIntractorFacade contactUsIntractor;


    public ContactUsPresenter(ContactUsView contactUsView, ContactUsIntractorFacade contactUsDAO) {
        this.contactUsView = contactUsView;
        this.contactUsIntractor = contactUsDAO;
    }

    @Override
    public ContactUsView getView() {
        return contactUsView;
    }

    public void sendMassageToServer(String massage) {
        contactUsIntractor.sendMassageToSupportTeam(massage,this);
    }

    @Override
    public void onDone(ContactUsState viewState) {
        if (getView()!= null){
            getView().render(viewState);
        }
    }
}
