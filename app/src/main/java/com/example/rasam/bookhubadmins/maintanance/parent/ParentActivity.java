package com.example.rasam.bookhubadmins.maintanance.parent;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.WindowManager;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by RasamArabzadeh on 21/01/2018.
 */

public abstract class ParentActivity<V extends MvpView,P extends MvpBasePresenter<V>> extends MvpActivity<V,P> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialConfiguration();

    }

    void initialConfiguration(){
        //toDo configs wich happens in all activity
        //hiding status bar


    }

    @NonNull
    @Override
    public P createPresenter() {
        return stablishPresenter();
    }
    protected abstract P stablishPresenter();


    //this has been set for calligraphy
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }







}
