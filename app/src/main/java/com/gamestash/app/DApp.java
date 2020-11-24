package com.gamestash.app;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Comparator;

/**
 * This class is used to store data between activities.
 */

public class DApp {
    // Member variables
    private static final String TAG = DApp.class.getSimpleName();

    private static File userJSONFile;
    private static WeakReference<IProcess> currPresenterRef;
    private static String searchSTR;
    private static String returnApiSTR = "";
    private static String returnUserSTR = "";
    private static DGameList apiGameList = new DGameList();
    private static DGameList userGameList = new DGameList();
    private static Boolean hasBeenEditedSearchSTR = false;
    private static Boolean hasBeenEditedReturnApiSTR = false;
    private static Boolean hasBeenEditedReturnUserSTR = false;
    private static Boolean hasBeenEditedAPIGameList = false;
    private static Boolean hasBeenEditedUserGameList = false;

    // Getters
    public static File getUserJSONFile() {return userJSONFile;}

    public static String getSearchSTR() {return searchSTR;}

    public static String getReturnApiSTR() {return returnApiSTR;}

    public static String getReturnUserSTR() {return returnUserSTR;}

    public static DGameList getApiGameList() {return apiGameList;}

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static DGameList getUserGameList() {
        if(hasBeenEditedUserGameList) {
            sortUserGameList();
            setHasBeenEditedUserGameList();
        }
        return userGameList;
    }

    public static Boolean getHasBeenEditedSearchSTR() {return hasBeenEditedSearchSTR;}

    public static Boolean getHasBeenEditedReturnApiSTR() {return hasBeenEditedReturnApiSTR;}

    public static Boolean getHasBeenEditedReturnUserSTR() {return hasBeenEditedReturnUserSTR;}

    public static Boolean getHasBeenEditedAPIGameList() {return hasBeenEditedAPIGameList;}

    public static Boolean getHasBeenEditedUserGameList() {return hasBeenEditedUserGameList;}

    //Setters
    public static void setUserJSONFile(File userJSONFile) {
        DApp.userJSONFile = userJSONFile;}

    public static void setSearchSTR(String searchSTR) {
        if(!searchSTR.equals(DApp.getSearchSTR())){
            DApp.searchSTR = searchSTR;
            DApp.hasBeenEditedSearchSTR = true;
        }
    }

    public static void setReturnApiSTR(String returnApiSTR) {
        DApp.returnApiSTR = returnApiSTR;
        DApp.hasBeenEditedReturnApiSTR = true;
    }

    public static void setReturnUserSTR(String returnUserSTR) {
        DApp.returnUserSTR = returnUserSTR;
        DApp.hasBeenEditedReturnUserSTR = true;
    }

    public static void setApiGameList(DGameList apiGameList) {
        DApp.apiGameList = apiGameList;
        DApp.hasBeenEditedAPIGameList = true;
    }

    public static void setUserGameList(DGameList userGameList) {
        DApp.userGameList = userGameList;
        DApp.hasBeenEditedUserGameList = true;
    }

    public static void addGameUserGameList(DGame game) {
        DApp.userGameList.getGameList().add(game);
        DApp.hasBeenEditedUserGameList = true;
    }

    public static void setHasBeenEditedSearchSTR() {
        DApp.hasBeenEditedSearchSTR = false;
    }

    public static void setHasBeenEditedReturnApiSTR() {
        DApp.hasBeenEditedReturnApiSTR = false;
    }

    public static void setHasBeenEditedReturnUserSTR() {
        DApp.hasBeenEditedReturnUserSTR = false;
    }

    public static void setHasBeenEditedAPIGameList() {
        DApp.hasBeenEditedAPIGameList = false;
    }

    public static void setHasBeenEditedUserGameList() {
        DApp.hasBeenEditedUserGameList = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void sortUserGameList() {
        if(DApp.userGameList != null) {
            DApp.userGameList.getGameList().sort(Comparator.comparing(DGame::getVisibleGameName));
        }
    }

}
