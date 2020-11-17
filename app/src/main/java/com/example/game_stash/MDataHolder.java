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
    private static Boolean hasBeenEditedreturnApiSTR = false;
    private static Boolean hasBeenEditedapiGameList = false;
    private static Boolean hasBeenEditeduserGameList = false;


    // Getters
    public static String getSearchSTR() {return searchSTR;}

    public static String getReturnApiSTR() {return returnApiSTR;}

    public static List<MGame> getApiGameList() {return apiGameList;}

    public static List<MGame> getUserGameList() {return userGameList;}

    public static Boolean getHasBeenEditedSearchSTR() {return hasBeenEditedSearchSTR;}

    public static Boolean getHasBeenEditedreturnApiSTR() {return hasBeenEditedreturnApiSTR;}

    public static Boolean getHasBeenEditedapiGameList() {return hasBeenEditedapiGameList;}

    public static Boolean getHasBeenEditeduserGameList() {return hasBeenEditeduserGameList;}

    //Setters
    public static void setSearchSTR(String searchSTR) {
        MDataHolder.searchSTR = searchSTR;
        MDataHolder.hasBeenEditedSearchSTR = true;
    }

    public static void setReturnApiSTR(String returnApiSTR) {
        MDataHolder.returnApiSTR = returnApiSTR;
        MDataHolder.hasBeenEditedSearchSTR = true;
    }

    public static void setApiGameList(List<MGame> apiGameList) {
        MDataHolder.apiGameList = apiGameList;
        MDataHolder.hasBeenEditedSearchSTR = true;
    }

    public static void setUserGameList(List<MGame> userGameList) {
        MDataHolder.userGameList = userGameList;
        MDataHolder.hasBeenEditedSearchSTR = true;
    }

    public static void setHasBeenEditedSearchSTR() {
        MDataHolder.hasBeenEditedSearchSTR = false;
    }

    public static void setHasBeenEditedreturnApiSTR() {
        MDataHolder.hasBeenEditedreturnApiSTR = false;
    }

    public static void setHasBeenEditedapiGameList() {
        MDataHolder.hasBeenEditedapiGameList = false;
    }

    public static void setHasBeenEditeduserGameList() {
        MDataHolder.hasBeenEditeduserGameList = false;
    }
    //TESTING SOMETHING...AGAIN
}
