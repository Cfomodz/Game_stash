package com.gamestash.app;

public class LocationItem {
    private String locationName;
    private String locationID;

    LocationItem(String location, String id) {
        locationName = location;
        locationID = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

}
