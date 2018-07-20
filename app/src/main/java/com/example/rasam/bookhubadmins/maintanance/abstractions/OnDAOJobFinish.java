package com.example.rasam.bookhubadmins.maintanance.abstractions;

import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;

public interface OnDAOJobFinish<T>{
    void onDone(DataBaseModel<T> dataBaseModel);
}
