package com.example.rasam.bookhubadmins.auth.presenter;

import android.content.Context;
import android.os.Build;

import com.example.rasam.bookhubadmins.auth.bussiness.RegisterIntractorFacade;
import com.example.rasam.bookhubadmins.auth.bussiness.RegisterViewState;
import com.example.rasam.bookhubadmins.auth.view.RegisterView;
import com.example.rasam.bookhubadmins.maintanance.MockUuid;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.pojos.authModels.DeviceRegisterModel;
import com.example.rasam.bookhubadmins.pojos.authModels.RegisterModel;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class RegisterPresenter extends MvpBasePresenter<RegisterView> {
    private RegisterIntractorFacade registerIntractorFacade;
    private RegisterView registerView;
    private Context context;

    public RegisterPresenter(RegisterIntractorFacade registerIntractorFacade, RegisterView registerView, Context context) {
        this.registerIntractorFacade = registerIntractorFacade;
        this.registerView = registerView;
        this.context = context;
    }

    public RegisterView getRegisterView() {
        return registerView;
    }

    public void registerDevice() {
        registerIntractorFacade.regiterDevice(new DeviceRegisterModel(Build.MODEL, "Android", MockUuid.getUuid(context), Build.VERSION.SDK_INT), new OnIntractor<RegisterViewState>() {
            @Override
            public void onDone(RegisterViewState viewState) {
                if (getView() != null){
                    getView().render(viewState);
                }
            }
        });
    }

    public void registerUser(){
        registerIntractorFacade.registerUser(new RegisterModel(MockUuid.getUuid(context), "Android", null), new OnIntractor<RegisterViewState>() {
            @Override
            public void onDone(RegisterViewState viewState) {
                if (getView() != null){
                    getView().render(viewState);
                }
            }
        });
    }

}
