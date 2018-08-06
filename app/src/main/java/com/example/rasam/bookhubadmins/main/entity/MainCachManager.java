package com.example.rasam.bookhubadmins.main.entity;

import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/18/2018.
 */
public class MainCachManager {


    private int lastItemNumber;
    private List items;
    private Ads selectedAdvertismentID;

    public MainCachManager() {
        items = new ArrayList();
    }

    public Ads getSelectedAdvertisment() {
        return selectedAdvertismentID;
    }

    public void setSelectedAdvertismentID(Ads ads) {
        this.selectedAdvertismentID = ads;
    }


    public int getLastItemNumber() {
        return lastItemNumber;
    }


    public void overrideItemListCount(int lastItemNumber){
        this.lastItemNumber = lastItemNumber;
    }

    public void incrementLIstItem(int itemCount) {
        lastItemNumber = lastItemNumber + itemCount;
    }
}
