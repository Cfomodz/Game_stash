package com.gamestash.app;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DLocationList {
    private static final String TAG = DLocationList.class.getSimpleName();

    private List<String> locationList = new ArrayList<>();

    /** This constructor will likely create a new game using GSON from API data. */
    public DLocationList(){}

    public void setLocationList(List<String> locationList) {this.locationList = locationList;}

    public List<String> getLocationList() {return locationList;}
}