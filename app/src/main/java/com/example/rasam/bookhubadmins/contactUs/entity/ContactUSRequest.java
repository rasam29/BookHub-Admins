package com.example.rasam.bookhubadmins.contactUs.entity;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.pojos.AdminMassageReports;

import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/27/2018.
 */

public interface ContactUSRequest {
    void sendMassageToServer(String token,String massage,OnRequestDone<Void> onRequestDone);
    void getMassagesHistoryFromBookHub(String token, OnRequestDone<List<AdminMassageReports>> onIntractor);
}
