package com.gamestash.app;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>DLocationList</h1>
 * This class is used with the creation of a User Location List.
 */
public class DLocationList {
    private static final String TAG = DLocationList.class.getSimpleName();

    private List<String> locationList = new ArrayList<>();

    /** This constructor will likely create a new game using GSON from API data. */
    public DLocationList(){}

    public void setLocationList(List<String> locationList) {this.locationList = locationList;}

    public List<String> getLocationList() {return locationList;}
}