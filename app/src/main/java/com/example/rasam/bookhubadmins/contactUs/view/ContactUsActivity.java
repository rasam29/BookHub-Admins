package com.example.rasam.bookhubadmins.contactUs.view;

import android.os.Bundle;
import android.widget.Toast;

import com.example.rasam.bookhubadmins.R;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactDependency;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactUsPresenter;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactUsState;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DAQ;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.RequestManager;
import com.example.rasam.bookhubadmins.maintanance.parent.ParentActivity;

/**
 * Created by R.Arabzadeh Taktell on 7/27/2018.
 */

public class ContactUsActivity extends ParentActivity<ContactUsView,ContactUsPresenter> implements ContactUsView {
    @Override
    protected ContactUsPresenter stablishPresenter() {
        return new ContactUsPresenter(this, ContactDependency.inject());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void render(ContactUsState contactUsState) {
        if (contactUsState instanceof ContactUsState.MassageSentState){
            Toast.makeText(this,"خطا در ارسال پیام",Toast.LENGTH_SHORT).show();
            finish();
        }else if (contactUsState instanceof ContactUsState.OnNetError){
            Toast.makeText(this,"خطا در ارسال پیام",Toast.LENGTH_SHORT).show();
        }else {
            throw new IllegalArgumentException("Wrong State Generated in Contact US");
        }
    }
}
