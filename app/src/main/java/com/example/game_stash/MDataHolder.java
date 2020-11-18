package com.example.game_stash;

import java.lang.ref.WeakReference;

/**
 * This class is used to store data between activities.
 */

public class MDataHolder {
    // Member variables
    private static final String fName = VMainMenu.class.getSimpleName();
    private static final String TAG = fName + ":";

    private static WeakReference<IPresenter> currPresenterRef;
    private static String searchSTR = "";
    private static String returnApiSTR = "";
    private static MGameList apiGameList;
    private static MGameList userGameList;
    private static Boolean hasBeenEditedSearchSTR = false;
    private static Boolean hasBeenEditedReturnApiSTR = false;
    private static Boolean hasBeenEditedAPIGameList = false;
    private static Boolean hasBeenEditedUserGameList = false;


    // Getters
    public static String getSearchSTR() {return searchSTR;}

    public static String getReturnApiSTR() {return returnApiSTR;}

    public static MGameList getApiGameList() {return apiGameList;}

    public static MGameList getUserGameList() {return userGameList;}

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

    public static void setApiGameList(MGameList apiGameList) {
        MDataHolder.apiGameList = apiGameList;
        MDataHolder.hasBeenEditedAPIGameList = true;
    }

    public static void setUserGameList(MGameList userGameList) {
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
