package com.example.rasam.bookhubadmins.main.Bussiness;

import com.example.rasam.bookhubadmins.main.entity.MainCachManager;
import com.example.rasam.bookhubadmins.main.entity.MainDataBaseActions;
import com.example.rasam.bookhubadmins.main.entity.MainRequests;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AuthKeyDAO;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.pojos.AuthKey;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;
import com.example.rasam.bookhubadmins.pojos.ads.Book;
import com.example.rasam.bookhubadmins.pojos.ads.User;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class MoreIntractorImpleTest {

    AuthKeyDAO authKeyDAO;
    private MainRequests mainRequests;
    private MainDataBaseActions mainDataBaseActions;
    private MainIntractorImple mainIntractorImple;

    @Before
    public void setUp() throws Exception {
        authKeyDAO = new AuthKeyDAO() {
            @Override
            public void deleteAuthKey(OnDAOJobFinish<AuthKey> onDone) {

            }

            @Override
            public void getAuthKey(OnDAOJobFinish<AuthKey> onDone) {
                onDone.onDone(new DataBaseModel<>(new AuthKey("Salam"), true));
            }

            @Override
            public void insertAuthKey(String authKey, OnDAOJobFinish<AuthKey> onDone) {

            }
        };
    }

    public void okConditions() {
        mainRequests = new MainRequests() {
            @Override
            public void deleteAds(String token, String adsId, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(200, null));
            }

            @Override
            public void promoteAds(String token, String adsId, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(200, null));

            }

            @Override
            public void refreshList(String token, OnRequestDone<List<Ads>> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<List<Ads>>(200, Collections.<Ads>emptyList()));
            }

            @Override
            public void getNextPsge(String token, int lastItem, OnRequestDone<List<Ads>> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<List<Ads>>(200, Collections.<Ads>emptyList()));
            }
        };


    }

    public void stressCondittion() {
        mainRequests = new MainRequests() {
            @Override
            public void deleteAds(String token, String adsId, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(new Throwable()));
            }

            @Override
            public void promoteAds(String token, String adsId, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(new Throwable()));

            }

            @Override
            public void refreshList(String token, OnRequestDone<List<Ads>> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<List<Ads>>(new Throwable()));
            }

            @Override
            public void getNextPsge(String token, int lastItem, OnRequestDone<List<Ads>> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<List<Ads>>(new Throwable()));
            }
        };
    }


    @Before
    public void before() {
        mainDataBaseActions = new MainDataBaseActions() {
            @Override
            public void saveDeletedAds(Ads ads, OnDAOJobFinish<Void> onDAOJobFinish) {
                onDAOJobFinish.onDone(new DataBaseModel<Void>(true));
            }

            @Override
            public void savePromotedAds(Ads ads, OnDAOJobFinish<Void> onDAOJobFinish) {
                onDAOJobFinish.onDone(new DataBaseModel<Void>(true));

            }
        };


    }


    @Test
    public void deleteAdvertisment_NetError() throws Exception {
        stressCondittion();
        mainIntractorImple = new MainIntractorImple(mainDataBaseActions, mainRequests, new MainCachManager(), authKeyDAO);
        mainIntractorImple.deleteAdvertisment(new Ads(new Book(), "", new User(), 1000), new OnIntractor<MainState>() {
            @Override
            public void onDone(MainState viewState) {
                assertTrue(viewState instanceof MainState.NetError);
            }
        });
    }

    @Test
    public void deleteAdvertisment_okCondittions() throws Exception {
        okConditions();
        mainIntractorImple = new MainIntractorImple(mainDataBaseActions, mainRequests, new MainCachManager(), authKeyDAO);
        mainIntractorImple.deleteAdvertisment(new Ads(null, null, null, 10), new OnIntractor<MainState>() {
            @Override
            public void onDone(MainState viewState) {
                assertTrue(viewState instanceof MainState.DeleteState);
            }
        });
    }


    @Test
    public void promoteAdvertisment_okConditions() throws Exception {
        okConditions();
        mainIntractorImple = new MainIntractorImple(mainDataBaseActions, mainRequests, new MainCachManager(), authKeyDAO);
        mainIntractorImple.promoteAdvertisment(new Ads(null, null, null, 10), new OnIntractor<MainState>() {
            @Override
            public void onDone(MainState viewState) {
                assertTrue(viewState instanceof MainState.PromoteState);
            }
        });

    }

    @Test
    public void promoteAdvertisment_NetError() throws Exception {
        stressCondittion();
        mainIntractorImple = new MainIntractorImple(mainDataBaseActions, mainRequests, new MainCachManager(), authKeyDAO);
        mainIntractorImple.promoteAdvertisment(new Ads(null, null, null, 10), viewState -> assertTrue(viewState instanceof MainState.NetError));

    }


    @Test
    public void getMoreAds_NextPaginateState() throws Exception {
        okConditions();
        mainIntractorImple = new MainIntractorImple(mainDataBaseActions, mainRequests, new MainCachManager(), authKeyDAO);
        mainIntractorImple.getMoreAds(viewState -> assertTrue(viewState instanceof MainState.NextPaginationState));


    }

    @Test
    public void getMoreAds_NetError() throws Exception {
        stressCondittion();
        mainIntractorImple = new MainIntractorImple(mainDataBaseActions, mainRequests, new MainCachManager(), authKeyDAO);
        mainIntractorImple.getMoreAds(viewState -> assertTrue(viewState instanceof MainState.NetError));
    }

    @Test
    public void refreshList_refreshState() throws Exception {
        okConditions();
        mainIntractorImple = new MainIntractorImple(mainDataBaseActions, mainRequests, new MainCachManager(), authKeyDAO);


        mainIntractorImple.refreshList(viewState -> assertTrue(viewState instanceof MainState.RefreshState));
    }

    @Test
    public void refreshList_stressCondition() throws Exception {
        stressCondittion();
        mainIntractorImple = new MainIntractorImple(mainDataBaseActions, mainRequests, new MainCachManager(), authKeyDAO);

        mainIntractorImple.refreshList(viewState -> assertTrue(viewState instanceof MainState.NetError));
    }

}