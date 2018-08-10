package com.example.rasam.bookhubadmins.splash.Bussiness.interactor;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DAQ;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.SplashRequests;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.pojos.AuthKey;
import com.example.rasam.bookhubadmins.pojos.UpdateModel;
import com.example.rasam.bookhubadmins.splash.Bussiness.SplashViewState;

import org.junit.Test;

import java.net.SocketTimeoutException;

import static org.junit.Assert.*;

public class SplashIntractorTest {

    /**
     * These are Intractors dependencies
     */
    private SplashRequests splashRequests;
    private DAQ dao;





    private SplashIntractor splashIntractor;


    @Test
    public void checkForUpdate_OnHaveUpdate() {


        splashRequests = new SplashRequests() {
            @Override
            public void checkForUpdate(OnRequestDone<UpdateModel> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<UpdateModel>(200, new UpdateModel(true, "problem in delete solved", "2.0.1")));
            }
        };
        splashIntractor = new SplashIntractor(splashRequests, null);


        splashIntractor.checkForUpdate(new OnIntractor<SplashViewState>() {
            @Override
            public void onDone(SplashViewState splashViewState) {
                assertTrue(splashViewState instanceof SplashViewState.OnForceUpdate);


            }
        });


    }

    @Test
    public void checkForUpdate_NoUpdate() {
        splashRequests = new SplashRequests() {
            @Override
            public void checkForUpdate(OnRequestDone<UpdateModel> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<UpdateModel>(200, new UpdateModel(false, null, null)));
            }
        };
        splashIntractor = new SplashIntractor(splashRequests, null);


        splashIntractor.checkForUpdate(new OnIntractor<SplashViewState>() {
            @Override
            public void onDone(SplashViewState splashViewState) {
                assertTrue(splashViewState instanceof SplashViewState.NoUpdateState);


            }
        });
    }

    @Test
    public void checkForUpdate_OnNetError() {
        splashRequests = new SplashRequests() {
            @Override
            public void checkForUpdate(OnRequestDone<UpdateModel> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<UpdateModel>(new Throwable(new SocketTimeoutException())));
            }
        };
        splashIntractor = new SplashIntractor(splashRequests, null);


        splashIntractor.checkForUpdate(new OnIntractor<SplashViewState>() {
            @Override
            public void onDone(SplashViewState splashViewState) {
                assertTrue(splashViewState instanceof SplashViewState.OnNetError);


            }
        });
    }

    @Test
    public void checkForToken_TokenExists() {

        dao = new DAQ() {
            @Override
            public void deleteAuthKey(OnDAOJobFinish<AuthKey> onDone) {
                //todo interface segregated manner (ISP)
            }

            @Override
            public void getAuthKey(OnDAOJobFinish<AuthKey> onDone) {
                onDone.onDone(new DataBaseModel<AuthKey>(new AuthKey("KJDLKJNSKJSN"), true));
            }

            @Override
            public void insertAuthKey(String authKey,OnDAOJobFinish<AuthKey> onDone) {
                //todo interface segregated manner (ISP)

            }
        };
        splashIntractor = new SplashIntractor(null, (DAQ) dao);
        splashIntractor.checkForToken(new OnIntractor<SplashViewState>() {
            @Override
            public void onDone(SplashViewState splashViewState) {
                assertTrue(splashViewState instanceof SplashViewState.OnHaveToken);
            }
        });
    }

    @Test
    public void checkForToken_TokenNotFound() {

        dao = new DAQ() {
            @Override
            public void deleteAuthKey(OnDAOJobFinish<AuthKey> onDone) {
                //todo interface segregated manner (ISP)
            }

            @Override
            public void getAuthKey(OnDAOJobFinish<AuthKey> onDone) {
                onDone.onDone(new DataBaseModel<AuthKey>(null, true));
            }

            @Override
            public void insertAuthKey(String authKey, OnDAOJobFinish<AuthKey> onDone) {
                //todo interface segregated manner (ISP)

            }
        };
        splashIntractor = new SplashIntractor(null, (DAQ) dao);
        splashIntractor.checkForToken(new OnIntractor<SplashViewState>() {
            @Override
            public void onDone(SplashViewState splashViewState) {
                assertTrue(splashViewState instanceof SplashViewState.OnDoesntToken);
            }
        });

    }


}