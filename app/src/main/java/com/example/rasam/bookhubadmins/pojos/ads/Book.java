package com.example.rasam.bookhubadmins.pojos.ads;

import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


@Table(database = AppDataBase.class)
public class Book extends BaseModel {

    @Column
    @PrimaryKey
    private int id;


    @Column
    private String name;

    @Column
    private int numberOfPages;

    @Column
    private String author;


    @Column
    private String publisher;

    @Column
    private float price;

    @Column
    private String shabak;

    public Book() {
    }


    private Book(String name, int numberOfPages, String author, String publisher, float price, String shabak) {
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.shabak = shabak;
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

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getShabak() {
        return shabak;
    }

    public void setShabak(String shabak) {
        this.shabak = shabak;
    }


    public static class BookBuilder {


        private int id;

        private String name;

        private int numberOfPages;

        private String author;

        private String publisher;

        private float price;

        private String shabak;


        public BookBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public BookBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BookBuilder setNumberOfPages(int numberOfPages) {
            this.numberOfPages = numberOfPages;
            return this;
        }

        public BookBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public BookBuilder setPrice(float price) {
            this.price = price;
            return this;
        }

        public BookBuilder setShabak(String shabak) {
            this.shabak = shabak;
            return this;
        }
        public Book build(){
            return new Book(name,numberOfPages,author,publisher,price,shabak);
        }
    }

}
