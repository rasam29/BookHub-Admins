package com.example.rasam.bookhubadmins.contactUs.view.contact_us;

import com.example.rasam.bookhubadmins.contactUs.presenter.ContactUsState;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by R.Arabzadeh Taktell on 7/27/2018.
 */

public interface ContactUsView extends MvpView{

    void render(ContactUsState contactUsState);
}
