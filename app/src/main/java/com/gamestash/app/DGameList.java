package com.gamestash.app;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>DGameList</h1>
 * This is a basic class that is primarily used with the JSON calls to create the returned
 * api objects. It can be used in other locations as it is creating a DGameList object.
 */
public class DGameList {
    private static final String TAG = DGameList.class.getSimpleName();

    private @SerializedName("games") List<DGame> gameList = new ArrayList<>();

    /** This constructor will likely create a new game using GSON from API data. */
    public DGameList(){}

    public DGameList(DGame game){
        this.gameList.add(game);
    };

    public void setGameList(List<DGame> gameList) {this.gameList = gameList;}

    public List<DGame> getGameList() {return gameList;}

    /**
     * resetNullGameValues loops over the class/object gameList and resets every entry to the
     * default values. This is used when saving.
     */
    public void resetNullGameValues() {
        gameList.forEach(game -> game.resetNullValues());
    }
}
