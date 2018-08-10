package com.example.rasam.bookhubadmins.contactUs.view.history;

import com.example.rasam.bookhubadmins.contactUs.bussiness.ContactUsIntractorFacade;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactDependency;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactUsState;
import com.example.rasam.bookhubadmins.contactUs.view.contact_us.ContactUsView;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * Created by R.Arabzadeh Taktell on 8/8/2018.
 */

public class ReportHistoryPresenter extends MvpBasePresenter<ContactUsView> implements OnIntractor<ContactUsState>{
    ContactUsView contactUsView;
    ContactUsIntractorFacade contactUsIntractorFacade;

    public ReportHistoryPresenter(ContactUsView contactUsView, ContactUsIntractorFacade contactUsIntractorFacade) {
        this.contactUsView = contactUsView;
        this.contactUsIntractorFacade = contactUsIntractorFacade;
    }

    @Override
    public ContactUsView getView() {
        return contactUsView;
    }


    public void refreshList(){
        contactUsIntractorFacade.getMssagesHistory(this);
    }


    @Override
    public void onDone(ContactUsState viewState) {
        if (getView()!= null){
            getView().render(viewState);
        }
    }
}
