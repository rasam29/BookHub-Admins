package com.example.rasam.bookhubadmins.main.view.mainList;

import android.view.View;

import com.example.rasam.bookhubadmins.pojos.ads.Ads;

public interface OnSwipeButtonClicked {
    void onDelete(View view, int adsItem);
    void onPromote(View view,int adsItem);
    void onClick(View view,int adsItem);
}
