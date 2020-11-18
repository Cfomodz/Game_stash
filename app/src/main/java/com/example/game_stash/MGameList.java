package com.example.game_stash;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MGameList {
    private static final String TAG = MGameList.class.getSimpleName();

    private @SerializedName("games") List<MGame> gameList = new ArrayList<>();

    /** This constructor will likely create a new game using GSON from API data. */
    public MGameList(){}

    public void setGameList(List<MGame> gameList) {this.gameList = gameList;}

    public List<MGame> getGameList() {return gameList;}
}
