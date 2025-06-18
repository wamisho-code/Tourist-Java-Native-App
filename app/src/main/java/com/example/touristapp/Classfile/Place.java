package com.example.touristapp.Classfile;

public class Place {
    private String name;
    private String location;
    private String region;
    private String longitude;
    private String latitude;
    private String description;
    private Double distance;
    private float rating;

    // Main method for testing
    public static void main(String[] args) {
        // Test your classes here
    }

    // Getter and setter methods
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDistance() {
        return distance;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    // Method to print place details
    public void printPlace() {
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Region: " + region);
        System.out.println("Longitude: " + longitude);
        System.out.println("Latitude: " + latitude);
        System.out.println("Description: " + description);
        System.out.println("Distance: " + distance);
        System.out.println("Rating: " + rating);
    }
}

class HistoricalSiteAndNaturalHeritage extends Place {
    private String nearerHotel;
    private String nearestHospital;
    private String nearestPoliceStation;
    private String nearestBank;
    private String nearestATM;
    private String nearestBusStop;
    private String nearestRailwayStation;

    // Getter and setter methods
    public void setNearerHotel(String nearerHotel) {
        this.nearerHotel = nearerHotel;
    }

    public String getNearerHotel() {
        return nearerHotel;
    }

    public void setNearestHospital(String nearestHospital) {
        this.nearestHospital = nearestHospital;
    }

    public String getNearestHospital() {
        return nearestHospital;
    }

    public void setNearestPoliceStation(String nearestPoliceStation) {
        this.nearestPoliceStation = nearestPoliceStation;
    }

    public String getNearestPoliceStation() {
        return nearestPoliceStation;
    }
}

class Hotel {
    private String name;
    private String location;
    private String contactInfo;

    // Getter and setter methods
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}

class Event {
    private String name;
    private String location;
    private String date;
    private String time;
    private String description;
    private String contactInfo;
    private String organizer;
    private String type;
    private String duration;
    private String price;

    // Getter and setter methods
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
}

class Food {
    private String name;
    private String description;

    // Getter and setter methods
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

class CulturalClothes {
    private String name;
    private String description;
    private String region;

    // Getter and setter methods
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }
}

class Jewelry {
    private String name;
    private String description;
    private String material;

    // Getter and setter methods
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }
}
