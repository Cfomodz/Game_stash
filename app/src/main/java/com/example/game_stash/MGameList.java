package com.example.game_stash;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MGameList {
    public @SerializedName("games") List<MGame> gameList = new ArrayList<>();
}
