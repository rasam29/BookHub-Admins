package com.example.rasam.bookhubadmins.contactUs.entity;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;

/**
 * Created by R.Arabzadeh Taktell on 7/27/2018.
 */

public interface ContactUsDAO {
    void saveMassageToDataBase(OnDAOJobFinish<Void> onDAOJobFinish);
}
