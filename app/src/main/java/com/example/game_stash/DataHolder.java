package com.example.game_stash;

import java.util.List;

/**
 * This class is used to store data between activities.
 */

public class DataHolder {
    // Member variables
    private static String searchSTR;
    private static String returnApiSTR;
    private static List<Game> apiGameList;
    private static List<Game> userGameList;

    // Getters
    public static String getSearchSTR() {return searchSTR;}
    public static String getReturnApiSTR() {return returnApiSTR;}
    public static List<Game> getApiGameList() {return apiGameList;}
    public static List<Game> getUserGameList() {return userGameList;}

    //Setters
    public static void setSearchSTR(String searchSTR) {DataHolder.searchSTR = searchSTR;}
    public static void setReturnApiSTR(String returnApiSTR) {DataHolder.returnApiSTR = returnApiSTR;}
    public static void setApiGameList(List<Game> apiGameList) {DataHolder.apiGameList = apiGameList;}
    public static void setUserGameList(List<Game> userGameList) {DataHolder.userGameList = userGameList;}

}
