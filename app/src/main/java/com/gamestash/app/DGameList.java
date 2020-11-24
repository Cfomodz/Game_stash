package com.gamestash.app;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DGameList {
    private static final String TAG = DGameList.class.getSimpleName();

    private @SerializedName("games") List<DGame> gameList = new ArrayList<>();

    /** This constructor will likely create a new game using GSON from API data. */
    public DGameList(){}

    public void setGameList(List<DGame> gameList) {this.gameList = gameList;}

    public List<DGame> getGameList() {return gameList;}
}
