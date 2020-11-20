package com.example.game_stash;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;

public class MSaveGame implements Runnable{
    private static final String TAG = MGameList.class.getSimpleName();
    private static WeakReference<MGame> gameRef;
    private static WeakReference<AppCompatActivity> masterRef;
    private static WeakReference<IPAPIGameDetails> presenterRef;
    private boolean existsInUserList = false;
    private boolean addedToUserGameList = false;
    private boolean jsonStringCreated = false;
    private boolean savedToFile = false;
    private String jsonString;
    private static final String filename = "usergamelist.json";

    /** This constructor will likely create a new game using GSON from API data. */
    public MSaveGame(VAPIGameDetails activity, IPAPIGameDetails presenter, MGame game){
        masterRef = new WeakReference<>(activity);
        presenterRef = new WeakReference<>(presenter);
        gameRef = new WeakReference<>(game);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void run() {
        this.addedToUserGameList = this.saveGameToUserList();
        this.jsonStringCreated = this.objToJSONString();
        this.savedToFile = this.saveToFile(filename, this.jsonString);
        this.sendToast();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean saveGameToUserList() {
        //Check if user list is empty
        if(MDataHolder.getUserGameList() != null && MDataHolder.getUserGameList().getGameList() != null) {
            //Check if game id matches any an id in the user list...
            for (MGame usersGame : MDataHolder.getUserGameList().getGameList()) {
                if(gameRef.get() != null && usersGame.getGameID().equals(gameRef.get().getGameID())) {
                    this.existsInUserList = true;
                }
            }
        } else {
            Log.d(TAG, "User list was empty...");
        }

        // If no match add game...
        if (!this.existsInUserList && gameRef.get() != null && presenterRef.get() != null) {
            MDataHolder.getUserGameList().getGameList().add(gameRef.get());
            return true;
        } else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean objToJSONString() {
        if(this.addedToUserGameList) {
            this.jsonString = new Gson().toJson(MDataHolder.getUserGameList());
            Log.d(TAG, this.jsonString);
            return true;
        }
        return false;
    }

    public boolean saveToFile(String fileName, String fileContents) {
        if (masterRef.get() != null) {
            return new MSaveToFile(masterRef.get(), fileName, fileContents).run();
        }
        return false;
    }

    public void sendToast() {
        if (this.savedToFile && gameRef.get() != null) {
            // TOAST::GAME ADDED TO USER LIST
            Log.d(TAG, "Game added...");
            if (masterRef.get() != null) {
                masterRef.get().runOnUiThread(() -> {
                    Toast toast = Toast.makeText(masterRef.get(), "GAME ADDED TO USER LIST", Toast.LENGTH_LONG);
                    toast.show();
                });
            }
        } else {
            // TOAST::GAME ALREADY EXISTS IN USER LIST
            if (masterRef.get() != null) {
                masterRef.get().runOnUiThread(() -> {
                    Toast toast = Toast.makeText(masterRef.get(), "GAME ALREADY EXISTS IN USER LIST", Toast.LENGTH_LONG);
                    toast.show();
                });
            }
        }
    }

}
