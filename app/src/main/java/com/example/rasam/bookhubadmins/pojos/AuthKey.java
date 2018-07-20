package com.example.rasam.bookhubadmins.pojos;


import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDataBase.class)
public class AuthKey extends BaseModel {
    @Column
    @PrimaryKey
    private int id;

    @Column
    private String key;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public AuthKey(String key) {
        this.key = key;
    }

    public AuthKey() {
    }
}
