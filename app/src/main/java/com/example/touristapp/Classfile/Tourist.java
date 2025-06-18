package com.example.touristapp.Classfile;

public class Tourist extends Person{

    float rating;
    String experience;
    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}