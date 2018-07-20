package com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase;

public class DataBaseModel<T> {
    private Throwable throwable;
    private T data;
    private boolean isOk;



    public boolean isOk() {
        return isOk;
    }

    public DataBaseModel(Throwable throwable) {
        this.throwable = throwable;
    }




    public DataBaseModel(T data, boolean isOk) {
        this.data = data;
        this.isOk = isOk;
    }

    public DataBaseModel(boolean isOk) {
        this.isOk = isOk;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public T getData() {
        return data;
    }
}
