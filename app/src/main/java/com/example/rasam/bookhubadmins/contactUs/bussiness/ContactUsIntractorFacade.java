package com.example.rasam.bookhubadmins.contactUs.bussiness;

import com.example.rasam.bookhubadmins.contactUs.presenter.ContactUsState;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.pojos.AdminMassageReports;
import com.example.rasam.bookhubadmins.pojos.ContactUsMassagePayLoad;

import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public interface ContactUsIntractorFacade {
    void sendMassageToSupportTeam(ContactUsMassagePayLoad payLoad, OnIntractor<ContactUsState> onIntractor);

    void getMssagesHistory(OnIntractor<ContactUsState> onIntractor);
}
