package com.example.rasam.bookhubadmins.pojos.ads;

import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDataBase.class)
public class User {

    @Column
    @PrimaryKey
    private int id;

    @Column
    private String name;


    @Column
    private String familyName;

    @Column
    private String phoneNumber;

    @Column
    private String userId;

    @Column
    private String location;
    @Column
    private String profilePicture;


    public User() {}


    public User(String name, String familyName, String phoneNumber, String userId, String location, String profilePicture) {
        this.name = name;
        this.familyName = familyName;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.location = location;
        this.profilePicture = profilePicture;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }


    public static class UserBuilder {

        private int id;
        private String name;
        private String familyName;
        private String phoneNumber;
        private String userId;
        private String location;
        private String profilePicture;


        public UserBuilder Id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder Name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder FamilyName(String familyName) {
            this.familyName = familyName;
            return this;
        }

        public UserBuilder PhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder UserId(String userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder Location(String location) {
            this.location = location;
            return this;
        }

        public UserBuilder ProfilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
            return this;
        }

        public User build() {
            return new User(name,familyName,phoneNumber,userId,location,profilePicture);
        }
    }
}