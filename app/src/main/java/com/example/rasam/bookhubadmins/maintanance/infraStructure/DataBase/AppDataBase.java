package com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase;


import com.raizlabs.android.dbflow.annotation.Database;

import static com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AppDataBase.NAME;
import static com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AppDataBase.VERSION;

@Database(name = NAME, version = VERSION)
public class AppDataBase {
    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
}
