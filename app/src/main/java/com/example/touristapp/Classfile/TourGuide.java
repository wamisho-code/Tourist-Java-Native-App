package com.example.touristapp.Classfile;

public class TourGuide extends Person {
    private String language;
    private String location;
    private String phone;
    private float rating;

    public void setRating(float rating) {
        this.rating = rating;
    }

    private String imagruri;


    public TourGuide() {

    }

    public TourGuide(String language, String location, String phone, float rating, String imagruri) {
        this.language = language;
        this.location = location;
        this.phone = phone;
        this.rating = rating;
        this.imagruri = imagruri;
    }

    public String getLanguage() {
        return language;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public float getRating() {
        return rating;
    }

    public String getImagruri() {  // Update getter
        return imagruri;
    }




}
