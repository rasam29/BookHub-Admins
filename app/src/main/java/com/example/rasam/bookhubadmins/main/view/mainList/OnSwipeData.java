package com.example.rasam.bookhubadmins.main.view.mainList;

import android.view.View;

import com.example.rasam.bookhubadmins.pojos.ads.Ads;

public interface OnSwipeData {
    void onDelete(View view, Ads adsItem);

    void onPromote(View view, Ads adsItem);

    void onItemClick(View view, Ads adsItem);
}
