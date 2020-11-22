package com.gamestash.app;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Comparator;

/**
 * This class is used to store data between activities.
 */

public class MDataHolder {
    // Member variables
    private static final String TAG = MDataHolder.class.getSimpleName();

    private static File userJSONFile;
    private static WeakReference<IProcess> currPresenterRef;
    private static String searchSTR;
    private static String returnApiSTR = "";
    private static String returnUserSTR = "";
    private static MGameList apiGameList = new MGameList();
    private static MGameList userGameList = new MGameList();
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

    public static MGameList getApiGameList() {return apiGameList;}

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static MGameList getUserGameList() {
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
    public static void setUserJSONFile(File userJSONFile) {MDataHolder.userJSONFile = userJSONFile;}

    public static void setSearchSTR(String searchSTR) {
        if(!searchSTR.equals(MDataHolder.getSearchSTR())){
            MDataHolder.searchSTR = searchSTR;
            MDataHolder.hasBeenEditedSearchSTR = true;
        }
    }

    public static void setReturnApiSTR(String returnApiSTR) {
        MDataHolder.returnApiSTR = returnApiSTR;
        MDataHolder.hasBeenEditedReturnApiSTR = true;
    }

    public static void setReturnUserSTR(String returnUserSTR) {
        MDataHolder.returnUserSTR = returnUserSTR;
        MDataHolder.hasBeenEditedReturnUserSTR = true;
    }

    public static void setApiGameList(MGameList apiGameList) {
        MDataHolder.apiGameList = apiGameList;
        MDataHolder.hasBeenEditedAPIGameList = true;
    }

    public static void setUserGameList(MGameList userGameList) {
        MDataHolder.userGameList = userGameList;
        MDataHolder.hasBeenEditedUserGameList = true;
    }

    public static void addGameUserGameList(MGame game) {
        MDataHolder.userGameList.getGameList().add(game);
        MDataHolder.hasBeenEditedUserGameList = true;
    }

    public static void setHasBeenEditedSearchSTR() {
        MDataHolder.hasBeenEditedSearchSTR = false;
    }

    public static void setHasBeenEditedReturnApiSTR() {
        MDataHolder.hasBeenEditedReturnApiSTR = false;
    }

    public static void setHasBeenEditedReturnUserSTR() {
        MDataHolder.hasBeenEditedReturnUserSTR = false;
    }

    public static void setHasBeenEditedAPIGameList() {
        MDataHolder.hasBeenEditedAPIGameList = false;
    }

    public static void setHasBeenEditedUserGameList() {
        MDataHolder.hasBeenEditedUserGameList = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void sortUserGameList() {
        if(MDataHolder.userGameList != null) {
            MDataHolder.userGameList.getGameList().sort(Comparator.comparing(MGame::getVisibleGameName));
        }
    }

}
