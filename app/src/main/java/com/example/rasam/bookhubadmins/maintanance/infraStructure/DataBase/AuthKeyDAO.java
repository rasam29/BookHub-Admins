package com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.pojos.AuthKey;

public interface AuthKeyDAO {
    void deleteAuthKey(OnDAOJobFinish<AuthKey> onDone);
    void getAuthKey(OnDAOJobFinish<AuthKey> onDone);

    void insertAuthKey(String authKey,OnDAOJobFinish<AuthKey> onDone);



}