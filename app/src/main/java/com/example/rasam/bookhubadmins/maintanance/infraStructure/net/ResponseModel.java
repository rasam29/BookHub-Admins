package com.example.rasam.bookhubadmins.maintanance.infraStructure.net;

public class ResponseModel<T> {
    private Throwable throwable;
    private int statusCode;
    private T Data;

    public ResponseModel(Throwable throwable) {
        this.throwable = throwable;
    }

    public ResponseModel(int statusCode, T data) {
        this.statusCode = statusCode;
        Data = data;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getData() {
        return Data;
    }
}
