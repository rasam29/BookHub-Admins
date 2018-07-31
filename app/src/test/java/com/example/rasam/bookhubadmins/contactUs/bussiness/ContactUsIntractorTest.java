package com.example.rasam.bookhubadmins.contactUs.bussiness;

import com.example.rasam.bookhubadmins.contactUs.entity.ContactUSRequest;
import com.example.rasam.bookhubadmins.contactUs.entity.ContactUsDAO;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactUsState;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AuthKeyDAO;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.pojos.AdminMassageReports;
import com.example.rasam.bookhubadmins.pojos.AuthKey;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */
public class ContactUsIntractorTest {


    ContactUsDAO contactUsDAO;
    ContactUSRequest contactUSRequest;
    ContactUsIntractor contactUsIntractor;
    AuthKeyDAO authKeyDAO;

    @Before
    public void setUp() throws Exception {
        contactUsDAO = onDAOJobFinish -> onDAOJobFinish.onDone(new DataBaseModel<Void>(true));
        authKeyDAO = new AuthKeyDAO() {
            @Override
            public void deleteAuthKey(OnDAOJobFinish<AuthKey> onDone) {

            }

            @Override
            public void getAuthKey(OnDAOJobFinish<AuthKey> onDone) {
                onDone.onDone(new DataBaseModel<AuthKey>(new AuthKey("Bearer sdlfj'sjrenfbg"),true));
            }

            @Override
            public void insertAuthKey(String authKey, OnDAOJobFinish<AuthKey> onDone) {

            }
        };
    }

    void onOk() {
        contactUSRequest = new ContactUSRequest() {
            @Override
            public void sendMassageToServer(String token,String massage, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(200, null));
            }

            @Override
            public void getMassagesHistoryFromBookHub(String token, OnIntractor<List<AdminMassageReports>> onIntractor) {
                onIntractor.onResponse(new ResponseModel<Void>(200, null));
            }
        };

        contactUsIntractor = new ContactUsIntractor(contactUsDAO, contactUSRequest,authKeyDAO);
    }

    void stressCondition() {
        contactUSRequest = new ContactUSRequest() {
            @Override
            public void sendMassageToServer(String massage, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(new Throwable()));
            }
        };
        contactUsIntractor = new ContactUsIntractor(contactUsDAO, contactUSRequest,authKeyDAO);

    }

    @Test
    public void sendMassageToSupportTeam_okCondition() throws Exception {
        onOk();
        contactUsIntractor.sendMassageToSupportTeam("", new OnIntractor<ContactUsState>() {
            @Override
            public void onDone(ContactUsState viewState) {
                assertTrue(viewState instanceof ContactUsState.MassageSentState);
            }
        });


    }

    @Test
    public void sendMassageToSupportTeam_NetError() throws Exception {
        stressCondition();
        contactUsIntractor.sendMassageToSupportTeam("", new OnIntractor<ContactUsState>() {
            @Override
            public void onDone(ContactUsState viewState) {
                assertTrue(viewState instanceof ContactUsState.OnNetError);
            }
        });
    }

}