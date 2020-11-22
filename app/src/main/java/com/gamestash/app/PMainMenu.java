package com.gamestash.app;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;

public class PMainMenu implements IProcess{
    private static final String TAG = PMainMenu.class.getSimpleName();

    private WeakReference<VMainMenu> masterRef;
    private static File file;

    public PMainMenu(VMainMenu activity){
        this.masterRef = new WeakReference<>(activity);
        this.file = new File(activity.getFilesDir() + "/usergamelist.json");
    }

    @Override
    public void processChanges() {
        if(MDataHolder.getReturnUserSTR().equals("")){
            getJSON();
        }
        if(MDataHolder.getHasBeenEditedReturnUserSTR()){
            gsonParse();
            MDataHolder.setHasBeenEditedReturnUserSTR();
        }
        if(MDataHolder.getHasBeenEditedUserGameList()){
            //Do something with the list...? Not on this screen?
            MDataHolder.setHasBeenEditedUserGameList();
        }

    }

    private void getJSON() {
        //This should be in a thread...
        String jsonString;
        try {
            InputStream is = new FileInputStream(file);

            int size = is.available();
            byte[] buffer = new byte[size];
            int exists = is.read(buffer);
            is.close();

            if(exists > -1) {
                jsonString = new String(buffer, StandardCharsets.UTF_8);
            } else {
                jsonString = null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            jsonString = null;
        }

        if(jsonString != null) {
            Log.d(TAG, jsonString);
        } else {
            Log.d(TAG, "file not found.");
        }

        if (jsonString != null && !jsonString.equals("")){
            MDataHolder.setReturnUserSTR(jsonString);
        }
    }

    private void gsonParse() {
        if(MDataHolder.getReturnUserSTR() != null) {
            //MGSONParser gsonParse = new MGSONParser(this, MDataHolder::setApiGameList, MDataHolder::getApiGameList, MDataHolder.getReturnApiSTR());
            MGSONParser gsonParse = new MGSONParser(this, MDataHolder::setUserGameList, MDataHolder.getReturnUserSTR());
            Thread thread = new Thread(gsonParse);
            thread.start();

        }

    }
}
