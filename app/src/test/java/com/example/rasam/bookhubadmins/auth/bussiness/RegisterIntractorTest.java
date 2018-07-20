package com.example.rasam.bookhubadmins.auth.bussiness;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.RegisterRequest;
import com.example.rasam.bookhubadmins.pojos.authModels.DeviceRegisterModel;
import com.example.rasam.bookhubadmins.pojos.authModels.RegisterModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterIntractorTest {

    private RegisterRequest registerRequest;
    private RegisterIntractor registerIntractor;

    @Before
    public void before_test() {
        registerRequest = new RegisterRequest() {
            @Override
            public void userRegister(RegisterModel registerModel, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(200, null));
            }

            @Override
            public void deviceRegister(DeviceRegisterModel registerModel, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(200, null));
            }
        };

        registerIntractor = new RegisterIntractor(registerRequest);
    }

    private void stressConditions() {
        registerRequest = new RegisterRequest() {
            @Override
            public void userRegister(RegisterModel registerModel, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(new Throwable()));

            }

            @Override
            public void deviceRegister(DeviceRegisterModel registerModel, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(new Throwable()));

            }
        };
        registerIntractor = new RegisterIntractor(registerRequest);


    }

    private void userBlockInitialization() {
        registerRequest = new RegisterRequest() {
            @Override
            public void userRegister(RegisterModel registerModel, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(203, null));
            }

            @Override
            public void deviceRegister(DeviceRegisterModel registerModel, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(203, null));

            }
        };
        registerIntractor = new RegisterIntractor(registerRequest);

    }

    @Test
    public void registerUser_ok() {
        registerIntractor.registerUser(new RegisterModel("sdsd", "Andorid", null), new OnIntractor<RegisterViewState>() {
            @Override
            public void onDone(RegisterViewState viewState) {
                assertTrue(viewState instanceof RegisterViewState.UserRegisterState);
            }
        });
    }

    @Test
        public void registerDevice_ok() {

        registerIntractor.regiterDevice(new DeviceRegisterModel("LG_XPOWER", "Andorid", "DJNDJS", 16), new OnIntractor<RegisterViewState>() {
            @Override
            public void onDone(RegisterViewState viewState) {
                assertTrue(viewState instanceof RegisterViewState.DeviceRegisterState);
            }
        });
    }


    @Test
    public void registerDevice_userBlocked() {
        userBlockInitialization();
        registerIntractor.registerUser(null, new OnIntractor<RegisterViewState>() {
            @Override
            public void onDone(RegisterViewState viewState) {
                assertTrue(viewState instanceof RegisterViewState.OnUUidBlockedState);
            }
        });
    }

    @Test
    public void registerDevice_netError() {
        stressConditions();
        registerIntractor.regiterDevice(null, new OnIntractor<RegisterViewState>() {
            @Override
            public void onDone(RegisterViewState viewState) {
                assertTrue(viewState instanceof RegisterViewState.ONetError);

            }
        });
    }



    @Test
    public void registerUser_netError() {
        stressConditions();
        registerIntractor.registerUser(null, new OnIntractor<RegisterViewState>() {
            @Override
            public void onDone(RegisterViewState viewState) {
                assertTrue(viewState instanceof RegisterViewState.ONetError);

            }
        });
    }



}