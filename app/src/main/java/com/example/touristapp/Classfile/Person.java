package com.example.touristapp.Classfile;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Person {
    private String first_name;
    private String last_name;
    private String place;
    private  String email;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }



    // Default constructor


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }








}
