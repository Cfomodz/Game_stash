package com.example.game_stash;

import java.util.List;

/**
 * This class is used to store data between activities.
 */

public class MDataHolder {
    // Member variables
    private static final String TAG = "Msg_MDataHolder:";
    private static String searchSTR = "";
    private static String returnApiSTR = "";
    private static List<MGame> apiGameList;
    private static List<MGame> userGameList;
    private static Boolean hasBeenEditedSearchSTR = false;
    private static Boolean hasBeenEditedReturnApiSTR = false;
    private static Boolean hasBeenEditedAPIGameList = false;
    private static Boolean hasBeenEditedUserGameList = false;


    // Getters
    public static String getSearchSTR() {return searchSTR;}

    public static String getReturnApiSTR() {return returnApiSTR;}

    public static List<MGame> getApiGameList() {return apiGameList;}

    public static List<MGame> getUserGameList() {return userGameList;}

    public static Boolean getHasBeenEditedSearchSTR() {return hasBeenEditedSearchSTR;}

    public static Boolean getHasBeenEditedReturnApiSTR() {return hasBeenEditedReturnApiSTR;}

    public static Boolean getHasBeenEditedAPIGameList() {return hasBeenEditedAPIGameList;}

    public static Boolean getHasBeenEditedUserGameList() {return hasBeenEditedUserGameList;}

    //Setters
    public static void setSearchSTR(String searchSTR) {
        MDataHolder.searchSTR = searchSTR;
        MDataHolder.hasBeenEditedSearchSTR = true;
    }

    public static void setReturnApiSTR(String returnApiSTR) {
        MDataHolder.returnApiSTR = returnApiSTR;
        MDataHolder.hasBeenEditedReturnApiSTR = true;
    }

    public static void setApiGameList(List<MGame> apiGameList) {
        MDataHolder.apiGameList = apiGameList;
        MDataHolder.hasBeenEditedAPIGameList = true;
    }

    public static void setUserGameList(List<MGame> userGameList) {
        MDataHolder.userGameList = userGameList;
        MDataHolder.hasBeenEditedUserGameList = true;
    }

    public static void setHasBeenEditedSearchSTR() {
        MDataHolder.hasBeenEditedSearchSTR = false;
    }

    public static void setHasBeenEditedReturnApiSTR() {
        MDataHolder.hasBeenEditedReturnApiSTR = false;
    }

    public static void setHasBeenEditedAPIGameList() {
        MDataHolder.hasBeenEditedAPIGameList = false;
    }

    public static void setHasBeenEditedUserGameList() {
        MDataHolder.hasBeenEditedUserGameList = false;
    }
}
