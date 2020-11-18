package com.example.game_stash;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class MAPIQueryURL {
    private static final String TAG = MAPIQueryURL.class.getSimpleName();

    private final String apiURL = "https://api.boardgameatlas.com/api/";
    private final String apiFunction = "search?";
    private final String apiClientID = "&client_id=KsnYD4Bc2j";
    private String gameIds;
    private String gameName;
    private String gamePublisher;
    private int gameMinPlayers;
    private int gameMaxPlayers;
    private int gameMinPlayTime;
    private int gameMaxPlayTime;
    private String apiQuery;
    private String url;

    public MAPIQueryURL(String gameIds,
                        String gameName,
                        String gamePublisher,
                        int gameMinPlayers,
                        int gameMaxPlayers,
                        int gameMinPlayTime,
                        int gameMaxPlayTime) {
        this.gameIds = gameIds.trim();
        this.gameName = gameName.trim().replaceAll("\\s", "");
        this.gamePublisher = gamePublisher.trim().replaceAll("\\s+", "%20");

        if(this.gameMinPlayers > 0) {
            this.gameMinPlayers = gameMinPlayers - 1;
        } else {
            this.gameMinPlayers =gameMinPlayers;
        }

        if(this.gameMaxPlayers >= 0) {
            this.gameMaxPlayers = gameMaxPlayers + 1;
        } else {
            this.gameMaxPlayers = gameMaxPlayers;
        }

        if(this.gameMinPlayTime > 0) {
            this.gameMinPlayTime = gameMinPlayTime - 1;
        } else {
            this.gameMinPlayTime =gameMinPlayTime;
        }

        if(this.gameMaxPlayTime >= 0) {
            this.gameMaxPlayTime = gameMaxPlayTime + 1;
        } else {
            this.gameMaxPlayTime = gameMaxPlayTime;
        }

        this.setApiQuery();
        this.setUrl();
    }

    private void setApiQuery() {
        List<String> queryList = new ArrayList<>();
        if (!this.gameIds.equals("")) {
            queryList.add("ids=" + this.gameIds);
        }
        if (!this.gameName.equals("")) {
            queryList.add("name=" + this.gameName);
            queryList.add("fuzzy_match=true");
        }
        if (!this.gamePublisher.equals("")) {
            queryList.add("publisher=" + this.gamePublisher);
        }
        if (this.gameMinPlayers > 0) {
            queryList.add("gt_min_players=" + this.gameMinPlayers);
        }
        if (this.gameMaxPlayers > 0) {
            queryList.add("lt_max_players=" + this.gameMaxPlayers);
        }
        if (this.gameMinPlayTime > 0) {
            queryList.add("gt_min_playtime=" + this.gameMinPlayTime);
        }
        if (this.gameMaxPlayTime > 0) {
            queryList.add("lt_max_playtime=" + this.gameMaxPlayTime);
        }

        this.apiQuery = TextUtils.join("&", queryList);
    }

    private void setUrl() {
        this.url = this.apiURL + apiFunction + this.apiQuery + apiClientID;
    }

    public String getUrl() { return url; }
}
