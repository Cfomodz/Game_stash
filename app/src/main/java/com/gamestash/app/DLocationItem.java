package com.gamestash.app;

public class DLocationItem {
    private String locationName;
    private String locationID;

    DLocationItem(String location, String id) {
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
