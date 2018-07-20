package com.example.rasam.bookhubadmins.auth.bussiness;

import com.example.rasam.bookhubadmins.maintanance.Constants;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.RegisterRequest;
import com.example.rasam.bookhubadmins.pojos.authModels.DeviceRegisterModel;
import com.example.rasam.bookhubadmins.pojos.authModels.RegisterModel;

public class RegisterIntractor implements RegisterIntractorFacade {

    private RegisterRequest requestManager;


    public RegisterIntractor(RegisterRequest requestManager) {

        this.requestManager = requestManager;
    }


    @Override
    public void registerUser(final RegisterModel registerModel, final OnIntractor<RegisterViewState> onDone) {
        requestManager.userRegister(registerModel, new OnRequestDone<Void>() {
            @Override
            public void onResponse(ResponseModel<Void> responseModel) {

                if (responseModel.getThrowable() == null) {
                    if (responseModel.getStatusCode() == 200) {
                        onDone.onDone(new RegisterViewState.UserRegisterState());
                    }
                } else
                    onDone.onDone(new RegisterViewState.ONetError(new Throwable(Constants.USER_REGISTER)));

            }
        });
    }

    @Override
    public void regiterDevice(DeviceRegisterModel deviceModel, final OnIntractor<RegisterViewState> onDone) {
        //todo not implemented yet
        requestManager.deviceRegister(deviceModel, new OnRequestDone<Void>() {
            @Override
            public void onResponse(ResponseModel<Void> responseModel) {


                if (responseModel.getThrowable() == null) {
                    if (responseModel.getStatusCode() == 200) {
                        onDone.onDone(new RegisterViewState.DeviceRegisterState());
                    } else if (responseModel.getStatusCode() == 203) {
                        onDone.onDone(new RegisterViewState.OnUUidBlockedState());
                    }

                } else
                    onDone.onDone(new RegisterViewState.ONetError(new Throwable(Constants.DEVICE_REGISTER)));
            }
        });

    }

}
