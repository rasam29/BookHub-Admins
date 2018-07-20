package com.example.rasam.bookhubadmins.pojos.ads;

public class Location {

    private float longtitude;
    private float latitude;

    public Location(float longtitude, float latitude) {
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
