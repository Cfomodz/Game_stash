package com.gamestash.app;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.GsonBuilder;

import java.lang.ref.WeakReference;

public class TSaveGame implements Runnable{
    private static final String TAG = DGameList.class.getSimpleName();
    private static WeakReference<DGame> gameRef;
    private static WeakReference<AppCompatActivity> masterRef;
    private static WeakReference<ISave> presenterRef;
    private static WeakReference<IDropDown> dropDownRef;
    private boolean saveEdits = false;
    private boolean deleting = false;
    private boolean existsInUserList = false;
    private boolean addedToUserGameList = false;
    private boolean addedToUserLocationList = false;
    private boolean jsonGameStringCreated = false;
    private boolean jsonLocationStringCreated = false;
    private boolean savedGameToFile = false;
    private boolean savedLocationListToFile = false;
    private String jsonString;
    private static final String filenameUserGameList = "usergamelist.json";
    private static final String filenameUserLocationList = "userlocationlist.json";

    /** This constructor will likely create a new game using GSON from API data. */
    public TSaveGame(AppCompatActivity activity, ISave presenter, DGame game){
        this.masterRef = new WeakReference<>(activity);
        this.presenterRef = new WeakReference<>(presenter);
        this.gameRef = new WeakReference<>(game);
    }

    public TSaveGame(AppCompatActivity activity, boolean saveEdits){
        this.masterRef = new WeakReference<>(activity);
        this.saveEdits = saveEdits;
    }

    public TSaveGame(AppCompatActivity activity, boolean saveEdits, boolean deleting){
        this.masterRef = new WeakReference<>(activity);
        this.saveEdits = saveEdits;
        this.deleting = deleting;
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void run() {
        // ADD NEW GAME
        if(!this.saveEdits) {
            this.addedToUserGameList = this.saveGameToUserGameList();
        } // else (this.saveEdits = true){ // Do nothing. ADD GAMELIST will handle the true scenario.;}

        // ADD GAMELIST: If New Game or Edits
        this.jsonGameStringCreated = this.objToJSONString(DApp.getUserGameList(), this.addedToUserGameList);
        this.savedGameToFile = this.saveToFile(filenameUserGameList, this.jsonString, this.jsonGameStringCreated);

        // ADD NEW LOCATION
        if(!this.saveEdits) {
            this.addedToUserLocationList = this.saveLocationToUserLocationList(this.savedGameToFile);
            Log.d(TAG, "LOCATION CHECK: " + DApp.getUserLocationList().getLocationList().toString());
        }
        // SAVE LOCATION LIST
        this.jsonLocationStringCreated = this.objToJSONString(DApp.getUserLocationList(), this.addedToUserLocationList);
        this.savedLocationListToFile = this.saveToFile(filenameUserLocationList, this.jsonString, this.jsonLocationStringCreated);

        this.sendToast();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean saveGameToUserGameList() {
        //Check if user list is empty
        if(DApp.getUserGameList() != null && DApp.getUserGameList().getGameList() != null) {
            //Check if game id matches any an id in the user list...
            for (DGame usersGame : DApp.getUserGameList().getGameList()) {
                if(gameRef.get() != null && usersGame.getGameID().equals(gameRef.get().getGameID()) && !gameRef.get().getIsUserCreated()) {
                    this.existsInUserList = true;
                } else if(gameRef.get() != null && gameRef.get().getIsUserCreated() && usersGame.getVisibleGameName().equals(gameRef.get().getVisibleGameName())) {
                    this.existsInUserList = true;
                }
            }
        } else {
            Log.d(TAG, "User list was empty...");
        }

        // If no match add game...
        if (!this.existsInUserList && gameRef.get() != null && presenterRef.get() != null) {
            DApp.addGameUserGameList(gameRef.get());
            return true;
        } else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean saveLocationToUserLocationList(boolean testResult) {
        //Check if location is empty
        boolean match = false;
        if(testResult && gameRef.get() != null && gameRef.get().getLocation().length() > 0){
            if(DApp.getUserLocationList() != null && DApp.getUserLocationList().getLocationList() != null && DApp.getUserLocationList().getLocationList().size() > 0) {
                //Check if game id matches any an id in the user list...
                for (String location : DApp.getUserLocationList().getLocationList()) {
                    if(gameRef.get() != null && location.toLowerCase().equals(gameRef.get().getLocation().toLowerCase())){
                        match = true;
                    }
                }
            } else {
                Log.d(TAG, "Location list was empty...");
            }
        } else {
            match = true; // If length = 0 or no save occurred then string is "" and we don't allow that, so match = true, even thought it isn't a real match.
        }

        // If no match add location to location list...
        if (gameRef.get() != null && !match) {
            DApp.addUserLocationList(gameRef.get().getLocation());
            return true;
        } else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean objToJSONString(Object obj, boolean testResult) {
        this.jsonString = "";
        if(testResult || saveEdits) {
            this.jsonString = new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(obj);
            Log.d(TAG, this.jsonString);
            return true;
        }
        return false;
    }

    public boolean saveToFile(String fileName, String fileContents, boolean testResult) {

        if (testResult && masterRef.get() != null) {
            return new TSaveToFile(masterRef.get(), fileName, fileContents).save();
        }
        return false;
    }

    public void sendToast() {
        // if (this.savedGameToFile && gameRef.get() != null) {
        if(this.savedGameToFile && this.deleting) {
            Log.d(TAG, "GameList Saved. Deletes Occurred.");
            if (masterRef.get() != null) {
                masterRef.get().runOnUiThread(() -> {
                    Toast toast = Toast.makeText(masterRef.get(), "GAME DELETED.", Toast.LENGTH_SHORT);
                    toast.show();
                });
            }
        } else if (this.savedGameToFile) {
            // TOAST::GAME ADDED TO USER LIST
            Log.d(TAG, "Game Saved to GameList.");
            if (masterRef.get() != null) {
                masterRef.get().runOnUiThread(() -> {
                    Toast toast = Toast.makeText(masterRef.get(), "GAME SAVED", Toast.LENGTH_SHORT);
                    toast.show();
                });
            }
        } else {
            // TOAST::GAME ALREADY EXISTS IN USER LIST
            if (masterRef.get() != null) {
                masterRef.get().runOnUiThread(() -> {
                    Toast toast = Toast.makeText(masterRef.get(), "GAME ALREADY EXISTS IN USER LIST", Toast.LENGTH_SHORT);
                    toast.show();
                });
            }
        }
    }
}
