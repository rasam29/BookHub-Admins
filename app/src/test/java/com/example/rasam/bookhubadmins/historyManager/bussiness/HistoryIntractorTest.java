package com.example.rasam.bookhubadmins.historyManager.bussiness;

import com.example.rasam.bookhubadmins.SimpleAdvertismentAction;
import com.example.rasam.bookhubadmins.historyManager.entity.HistoryDataBaseManger;
import com.example.rasam.bookhubadmins.historyManager.entity.HistoryRequests;
import com.example.rasam.bookhubadmins.historyManager.presenter.HistoryState;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by R.Arabzadeh Taktell on 7/29/2018.
 */
public class HistoryIntractorTest {
    private HistoryIntractor historyIntractor;


    /*
        HistoryIntractor.Class dependencies
     */
    private SimpleAdvertismentAction simpleAdvertismentAction;
    private HistoryRequests historyRequests;

    private AuthKeyDAO authKeyDAO;

    /*
    building dependencies
     */
    @Before
    public void setUp()  {
        authKeyDAO = new AuthKeyDAO() {
            @Override
            public void deleteAuthKey(OnDAOJobFinish<AuthKey> onDone) {

            }

            @Override
            public void getAuthKey(OnDAOJobFinish<AuthKey> onDone) {
                onDone.onDone(new DataBaseModel<AuthKey>(new AuthKey("Bearer lsjkhgkl;sgjsdglsdkfjg'dghkdf]g"), true));
            }

            @Override
            public void insertAuthKey(String authKey, OnDAOJobFinish<AuthKey> onDone) {

            }
        };


    }

    private void setUp_ok()  {
        simpleAdvertismentAction = new SimpleAdvertismentAction() {
            @Override
            public void deleteAds(String adsId, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(200, null));
            }

            @Override
            public void promoteAds(String adsId, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(200, null));
            }
        };
        historyRequests = new HistoryRequests() {
            @Override
            public void getDeletedAddHistory(String token, OnRequestDone<List<Ads>> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<List<Ads>>(200, getMockAds()));
            }

            @Override
            public void getPromotedAdsHistory(String token, OnRequestDone<List<Ads>> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<List<Ads>>(200, getMockAds()));
            }
        };
    }

    private void setUp_NoHistory(){
        historyRequests = new HistoryRequests() {
            @Override
            public void getDeletedAddHistory(String token, OnRequestDone<List<Ads>> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<>(204,Collections.emptyList()));
            }

            @Override
            public void getPromotedAdsHistory(String token, OnRequestDone<List<Ads>> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<>(204,Collections.emptyList()));
            }
        };


    }

    private void setUp_netError() {
        simpleAdvertismentAction = new SimpleAdvertismentAction() {
            @Override
            public void deleteAds(String adsId, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(new Throwable()));
            }

            @Override
            public void promoteAds(String adsId, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(new Throwable()));
            }
        };
        historyRequests = new HistoryRequests() {
            @Override
            public void getDeletedAddHistory(String token, OnRequestDone<List<Ads>> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<List<Ads>>(new Throwable()));
            }

            @Override
            public void getPromotedAdsHistory(String token, OnRequestDone<List<Ads>> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<List<Ads>>(new Throwable()));
            }
        };

    }

    private void setUp_addExpired(){
        simpleAdvertismentAction = new SimpleAdvertismentAction() {
            @Override
            public void deleteAds(String adsId, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(205,null));
            }

            @Override
            public void promoteAds(String adsId, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(205,null));

            }
        };
    }



    @Test
    public void getDeletedAds_OkCondition() throws Exception {
        setUp_ok();
        historyIntractor = new HistoryIntractor(simpleAdvertismentAction, historyRequests, authKeyDAO);
        historyIntractor.getDeletedAds( new OnIntractor<HistoryState>() {
            @Override
            public void onDone(HistoryState viewState) {
                assertTrue(viewState instanceof HistoryState.OnHistory);
                HistoryState.OnHistory onHistory = (HistoryState.OnHistory) viewState;

            }
        });
    }

    @Test
    public void getDeletedAds_NetError() throws Exception {
        setUp_netError();
        historyIntractor = new HistoryIntractor(simpleAdvertismentAction, historyRequests, authKeyDAO);
        historyIntractor.getDeletedAds( new OnIntractor<HistoryState>() {
            @Override
            public void onDone(HistoryState viewState) {
                assertTrue(viewState instanceof HistoryState.OnNetError);
            }
        });

    }

    @Test
    public void getDeletedAds_NoHistory() throws Exception {
        setUp_NoHistory();
        historyIntractor = new HistoryIntractor(simpleAdvertismentAction, historyRequests, authKeyDAO);
        historyIntractor.getDeletedAds(viewState -> assertTrue(viewState instanceof HistoryState.OnEmptyHistory));
    }





    @Test
    public void getPromotedAds_OkCondition() throws Exception {
        setUp_ok();
        historyIntractor = new HistoryIntractor(simpleAdvertismentAction, historyRequests, authKeyDAO);
        historyIntractor.getPromotedAds(viewState -> {
            assertTrue(viewState instanceof HistoryState.OnHistory);
        });

    }

    @Test
    public void getPromotedAds_NetError() throws Exception {
        setUp_netError();
        historyIntractor = new HistoryIntractor(simpleAdvertismentAction, historyRequests, authKeyDAO);
        historyIntractor.getPromotedAds(new OnIntractor<HistoryState>() {
            @Override
            public void onDone(HistoryState viewState) {
                assertTrue(viewState instanceof HistoryState.OnNetError);
            }
        });
    }

    @Test
    public void getPromotedAds_NoHistory() throws Exception {
        setUp_NoHistory();
        historyIntractor = new HistoryIntractor(simpleAdvertismentAction, historyRequests, authKeyDAO);
        historyIntractor.getPromotedAds(viewState -> {
            assertTrue(viewState instanceof HistoryState.OnEmptyHistory);
        });
    }




    @Test
    public void promote_NetError(){
        setUp_netError();
        historyIntractor = new HistoryIntractor(simpleAdvertismentAction,historyRequests,authKeyDAO);
        historyIntractor.promoteAdd(getUnrealAdvertisment(), viewState -> assertTrue(viewState instanceof HistoryState.OnNetError));
    }

    @Test
    public void promote_OkCondittion(){
        setUp_ok();
        historyIntractor = new HistoryIntractor(simpleAdvertismentAction,historyRequests,authKeyDAO);
        historyIntractor.promoteAdd(getUnrealAdvertisment(), viewState -> assertTrue(viewState instanceof HistoryState.PromoteState));
    }

    @Test
    public void promote_AdsExpired(){
        setUp_addExpired();
        historyIntractor = new HistoryIntractor(simpleAdvertismentAction,historyRequests,authKeyDAO);
        historyIntractor.promoteAdd(getUnrealAdvertisment(), viewState -> assertTrue(viewState instanceof HistoryState.AdExpired));

    }




    @Test
    public void delete_NetError(){
        setUp_netError();
        historyIntractor = new HistoryIntractor(simpleAdvertismentAction,historyRequests,authKeyDAO);
        historyIntractor.deleteAdd(getUnrealAdvertisment(), viewState -> assertTrue(viewState instanceof HistoryState.OnNetError));
    }

    @Test
    public void delete_OkCondittion(){
        setUp_ok();
        historyIntractor = new HistoryIntractor(simpleAdvertismentAction,historyRequests,authKeyDAO);
        historyIntractor.deleteAdd(getUnrealAdvertisment(), viewState -> assertTrue(viewState instanceof HistoryState.DeleteState));
    }

    @Test
    public void delete_AdsExpired(){
        setUp_addExpired();
        historyIntractor = new HistoryIntractor(simpleAdvertismentAction,historyRequests,authKeyDAO);
        historyIntractor.deleteAdd(getUnrealAdvertisment(), viewState -> assertTrue(viewState instanceof HistoryState.AdExpired));
    }




    private List<Ads> getMockAds() {
        List<Ads> adsList = new ArrayList<>();
        Book book = new Book.BookBuilder()
                .setAuthor("Robert C Martin")
                .setName("Clean code")
                .setNumberOfPages(350)
                .setPrice(3500)
                .setShabak("ldfksnndfjdvnd")
                .build();

        User user = new User.UserBuilder()
                .Name("Rasam")
                .FamilyName("Ararbzadeh")
                .Location("tehran")
                .PhoneNumber("09193078323")
                .ProfilePicture("")
                .UserId("sjnflkdfjns")
                .build();
        for (int i = 0; i < 5; i++) {
            adsList.add(new Ads(book, "تهران خیابان عشق", user, 1000));
        }

        return adsList;
    }

    private Ads getUnrealAdvertisment(){
        Ads ads = new Ads(null,null,null,0);
        ads.setAdvertismentID("salam");
        return ads;
    }
}