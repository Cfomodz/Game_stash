package com.example.game_stash;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class MSaveGame implements Runnable{
    private static final String TAG = MGameList.class.getSimpleName();
    private static WeakReference<MGame> gameRef;
    private static WeakReference<AppCompatActivity> masterRef;
    private static WeakReference<IPAPIGameDetails> presenterRef;
    boolean existsInUserList = false;
    boolean saved = false;
    boolean savedToFile = false;

    /** This constructor will likely create a new game using GSON from API data. */
    public MSaveGame(VAPIGameDetails activity, IPAPIGameDetails presenter, MGame game){
        this.masterRef = new WeakReference<>(activity);
        this.presenterRef = new WeakReference<>(presenter);
        this.gameRef = new WeakReference<>(game);
    }

    @Override
    public void run() {
        this.saved = this.saveGameToUserList();
        this.savedToFile = this.sendObjToJSON();
        this.sendToast();
    }

    public boolean saveGameToUserList() {
        //Check if user list is empty
        if(MDataHolder.getUserGameList() != null && MDataHolder.getUserGameList().getGameList() != null) {
            //Check if game id matches any an id in the user list...
            for (MGame usersGame : MDataHolder.getUserGameList().getGameList()) {
                if(this.gameRef.get() != null && usersGame.getGameID().equals(this.gameRef.get().getGameID())) {
                    this.existsInUserList = true;
                }
            }
        } else {
            Log.d(TAG, "User list was empty...");
        }

        // If no match add game...
        if (!this.existsInUserList && this.gameRef.get() != null && this.presenterRef.get() != null) {
            MDataHolder.getUserGameList().getGameList().add(this.gameRef.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean sendObjToJSON() {
        if(this.saved) {
            String filename = "usergamelist.json";
            String fileContents = new Gson().toJson(MDataHolder.getUserGameList());
            Log.d(TAG, fileContents);

            try (FileOutputStream fos = this.masterRef.get().openFileOutput(filename, Context.MODE_PRIVATE)) {
                fos.write(fileContents.getBytes());
                Log.d(TAG, "File should be written...");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void sendToast() {
        if (this.savedToFile && this.gameRef.get() != null) {
            // TOAST::GAME ADDED TO USER LIST
            Log.d(TAG, "Game added...");
            if (masterRef.get() != null) {
                masterRef.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(masterRef.get(), "GAME ADDED TO USER LIST", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
            }
        } else {
            // TOAST::GAME ALREADY EXISTS IN USER LIST
            if (masterRef.get() != null) {
                masterRef.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(masterRef.get(), "GAME ALREADY EXISTS IN USER LIST", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
            }
        }
    }

}
