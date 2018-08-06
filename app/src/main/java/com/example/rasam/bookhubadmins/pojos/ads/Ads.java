package com.example.rasam.bookhubadmins.pojos.ads;


import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDataBase.class)
public class Ads extends BaseModel {

    @Column
    @PrimaryKey
    private int id;

    @Column
    @ForeignKey(saveForeignKeyModel = false)
    private Book book;


    @Column
    private String location;


    @Column
    @ForeignKey
    private User user;


    @Column
    private long timePosted;

    @Column
    private String advertismentID;


    @Column
    private boolean isPromoted;

    public Ads() {
    }

    public Ads(Book book, String location, User user, long timePosted) {
        this.book = book;
        this.location = location;
        this.user = user;
        this.timePosted = timePosted;
    }

    public boolean isPromoted() {
        return isPromoted;
    }

    public void setPromoted(boolean promoted) {
        isPromoted = promoted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdvertismentID() {
        return advertismentID;
    }

    public void setAdvertismentID(String advertismentID) {
        this.advertismentID = advertismentID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(long timePosted) {
        this.timePosted = timePosted;
    }


    public class AdvertismentBuilder {

        private Book book;
        private String location;
        private User user;
        private long timePosted;


        public AdvertismentBuilder setBook(Book book) {
            this.book = book;
            return this;
        }

        public AdvertismentBuilder setLocation(String location) {
            this.location = location;
            return this;

        }

        public AdvertismentBuilder setUser(User user) {
            this.user = user;
            return this;

        }

        public AdvertismentBuilder setTime(long timePosted) {
            this.timePosted = timePosted;
            return this;

        }

        public Ads build() {
            return new Ads(book, location, user, timePosted);
        }
    }
}

