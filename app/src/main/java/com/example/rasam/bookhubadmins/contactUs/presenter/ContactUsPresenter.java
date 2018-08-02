package com.example.rasam.bookhubadmins.contactUs.presenter;

import com.example.rasam.bookhubadmins.contactUs.bussiness.ContactUsIntractorFacade;
import com.example.rasam.bookhubadmins.contactUs.view.contact_us.ContactUsView;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.pojos.ContactUsMassagePayLoad;
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

    public void sendMassageToServer(String massage,String title) {
        ContactUsMassagePayLoad payLoad = new ContactUsMassagePayLoad(title,massage);
        contactUsIntractor.sendMassageToSupportTeam(payLoad,this);
    }

    public void getListOfMassagesHistory(){
        contactUsIntractor.getMssagesHistory(this);
    }

    @Override
    public void onDone(ContactUsState viewState) {
        if (getView()!= null){
            getView().render(viewState);
        }
    }
}
