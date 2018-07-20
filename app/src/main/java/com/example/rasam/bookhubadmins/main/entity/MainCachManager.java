package com.example.rasam.bookhubadmins.main.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by R.Arabzadeh Taktell on 7/18/2018.
 */
public class MainCachManager {
    private int lastItemNumber;
    private List items;

    public MainCachManager() {
        items = new ArrayList();
    }

    public int getLastItemNumber() {
        return lastItemNumber;
    }

    public List getItems() {
        if (items == null) {
            return Collections.emptyList();
        } else return items;
    }

    public void incrementLIstItem(int itemCount){
        lastItemNumber = lastItemNumber+itemCount;
    }
}
