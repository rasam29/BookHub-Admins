package com.example.rasam.bookhubadmins.contactUs.bussiness;

import com.example.rasam.bookhubadmins.contactUs.presenter.ContactUsState;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public interface ContactUsIntractorFacade {
    void sendMassageToSupportTeam(String massage, OnIntractor<ContactUsState> onIntractor);
}
