package com.gamestash.app;

import java.io.File;
import java.lang.ref.WeakReference;

public class PMainMenu implements IProcess{
    private static final String TAG = PMainMenu.class.getSimpleName();

    private WeakReference<VMainMenu> masterRef;

    public PMainMenu(VMainMenu activity){
        this.masterRef = new WeakReference<>(activity);
    }

    @Override
    public void processChanges() {
        if(DApp.getReturnUserSTR().equals("")){
            getJSON(DApp.getUserJSONFile());
        }
        if(DApp.getHasBeenEditedReturnUserSTR()){
            gsonParse(DApp::setUserGameList, DApp.getReturnUserSTR());
            DApp.setHasBeenEditedReturnUserSTR();
        }
        if(DApp.getHasBeenEditedUserGameList()){
            //Do something with the list...? Not on this screen?
            DApp.setHasBeenEditedUserGameList();
            //Start the process to add the location list...
            getJSON(DApp.getUserLocationListJSONFile());
        }
        if(DApp.getHasBeenEditedReturnUserLocationListSTR()){
            gsonParse(DApp::setUserLocationList, DApp.getReturnUserLocationListSTR());
            DApp.setHasBeenEditedReturnUserLocationListSTR();
        }
        if(DApp.getHasBeenEditedUserLocationList()){
            //Do something with the list...? Not on this screen?
            DApp.setHasBeenEditedUserLocationList();
        }
    }

    private void getJSON(File file) {
        //This should be in a thread...
        TReadJSON readJSON = new TReadJSON(this, file);
        Thread thread = new Thread(readJSON);
        thread.start();
    }

    private void gsonParse(SetGameList setList, String response) {
        // This is an overloaded method...
        if(DApp.getReturnUserSTR() != null) {
            //MGSONParser gsonParse = new MGSONParser(this, MDataHolder::setApiGameList, MDataHolder::getApiGameList, MDataHolder.getReturnApiSTR());
            MGSONParser gsonParse = new MGSONParser(this, setList, response);
            Thread thread = new Thread(gsonParse);
            thread.start();

        }

    }

    private void gsonParse(SetLocationList setList, String response) {
        // This is an overloaded method...
        if(DApp.getReturnUserSTR() != null) {
            //MGSONParser gsonParse = new MGSONParser(this, MDataHolder::setApiGameList, MDataHolder::getApiGameList, MDataHolder.getReturnApiSTR());
            MGSONParser gsonParse = new MGSONParser(this, setList, response);
            Thread thread = new Thread(gsonParse);
            thread.start();

        }

    }
}
