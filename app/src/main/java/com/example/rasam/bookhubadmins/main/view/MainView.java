package com.example.rasam.bookhubadmins.main.view;

import com.example.rasam.bookhubadmins.main.Bussiness.MainState;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/18/2018.
 */
public interface MainView  extends MvpView{
    void appendListToRecyclerView(List<Ads> adsList);
    void refreshList(List<Ads> adsList);

    void render(MainState mainState);
    void showLoading();

}
