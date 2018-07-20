package com.example.rasam.bookhubadmins.auth.view;

import com.example.rasam.bookhubadmins.auth.bussiness.RegisterViewState;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface RegisterView extends MvpView {
    void render(RegisterViewState viewState);

}
