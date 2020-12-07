package com.gamestash.app;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PSettings implements IProcess {
    private static final String TAG = PMainMenu.class.getSimpleName();

    private WeakReference<VSettings> masterRef;
    private String jsonString;

    public PSettings(VSettings activity) {
        this.masterRef = new WeakReference<>(activity);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean objToJSONString(Object obj, boolean testResult) {
        this.jsonString = "";
        if(testResult) {
            this.jsonString = new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(obj);
            Log.d(TAG, this.jsonString);
            return true;
        }
        return false;
    }

    @Override
    public void processChanges() {
        List<DGame> gameList = DApp.getUserGameList().getGameList();
        List<String> listWithDuplicates =  new ArrayList<>();
        Log.d(TAG,listWithDuplicates.toString());
        for (DGame game: gameList){
            if(game.getLocation().trim().length()>0) {
                listWithDuplicates.add(game.getLocation());
            }
        }
        List<String> listWithoutDuplicates = listWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
        Log.d(TAG,listWithoutDuplicates.toString());

        DApp.getUserLocationList().setLocationList(listWithoutDuplicates);
        objToJSONString(DApp.getUserLocationList(),true);
        if(this.masterRef.get()!=null) {
            TSaveToFile saveToFile = new TSaveToFile(this.masterRef.get(), "userlocationlist.json", this.jsonString);
            Thread thread = new Thread(saveToFile);
            thread.start();
        }
    }
}