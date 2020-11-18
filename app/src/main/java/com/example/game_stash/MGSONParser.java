package com.example.game_stash;

import com.google.gson.Gson;

public class MGSONParser {
    private String response;
    private MGameList gameList;

    public MGSONParser(String response) {
        this.response = response;
        Gson gson = new Gson();
        this.gameList = gson.fromJson(this.response, MGameList.class);
    }

    public MGameList getGameList() {
        return gameList;
    }
}
