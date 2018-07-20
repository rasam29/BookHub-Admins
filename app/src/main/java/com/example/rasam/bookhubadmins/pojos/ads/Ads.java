package com.example.rasam.bookhubadmins.pojos.ads;

public class Ads {

    private Book book;
    private String location;
    private User user;
    private long timePosted;

    public Ads(Book book, String location, User user, long timePosted) {
        this.book = book;
        this.location = location;
        this.user = user;
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
            return new Ads(book,location,user,timePosted);
        }
    }

}
