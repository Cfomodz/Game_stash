package com.example.game_stash;

import java.util.List;

/**
 * This class is used to store data between activities.
 */

public class MDataHolder {
    // Member variables
    private static String searchSTR;
    private static String returnApiSTR;
    private static List<MGame> apiGameList;
    private static List<MGame> userGameList;

    // Getters
    public static String getSearchSTR() {return searchSTR;}
    public static String getReturnApiSTR() {return returnApiSTR;}
    public static List<MGame> getApiGameList() {return apiGameList;}
    public static List<MGame> getUserGameList() {return userGameList;}

    //Setters
    public static void setSearchSTR(String searchSTR) {
        MDataHolder.searchSTR = searchSTR;}
    public static void setReturnApiSTR(String returnApiSTR) {
        MDataHolder.returnApiSTR = returnApiSTR;}
    public static void setApiGameList(List<MGame> apiGameList) {
        MDataHolder.apiGameList = apiGameList;}
    public static void setUserGameList(List<MGame> userGameList) {
        MDataHolder.userGameList = userGameList;}

}
