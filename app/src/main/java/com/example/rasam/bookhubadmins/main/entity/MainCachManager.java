package com.example.rasam.bookhubadmins.main.entity;

import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/18/2018.
 */
public class MainCachManager {


    public MainCachManager() {
        items = new ArrayList();
    }


    private int lastItemNumber;
    private List items;

    private Ads selectedAdvertismentID;




    public Ads getSelectedAdvertisment() {
        return selectedAdvertismentID;
    }

    public void setSelectedAdvertismentID(Ads ads) {
        this.selectedAdvertismentID = ads;
    }





    public int getLastItemNumber() {
        return lastItemNumber;
    }

    public List getItems() {
        if (items == null) {
            return Collections.emptyList();
        } else return items;
    }

    public void incrementLIstItem(int itemCount) {
        lastItemNumber = lastItemNumber + itemCount;
    }
}
