package com.example.rasam.bookhubadmins.maintanance.parent.androidComponent;


import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by RasamArabzadeh on 04/02/2018.
 */

public abstract class ParentFragment<V extends MvpView, P extends MvpBasePresenter<V>> extends MvpFragment<V, P> {


    @Override
    public P createPresenter() {
        return stableshPresenter();
    }

    public abstract P stableshPresenter();


}
