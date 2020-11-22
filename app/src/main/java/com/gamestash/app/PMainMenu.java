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

    public PMainMenu(VMainMenu activity){
        this.masterRef = new WeakReference<>(activity);
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
        MReadJSON readJSON = new MReadJSON(this);
        Thread thread = new Thread(readJSON);
        thread.start();
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
