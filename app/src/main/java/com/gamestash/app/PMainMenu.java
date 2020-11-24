package com.gamestash.app;

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
            getJSON();
        }
        if(DApp.getHasBeenEditedReturnUserSTR()){
            gsonParse();
            DApp.setHasBeenEditedReturnUserSTR();
        }
        if(DApp.getHasBeenEditedUserGameList()){
            //Do something with the list...? Not on this screen?
            DApp.setHasBeenEditedUserGameList();
        }
    }

    private void getJSON() {
        //This should be in a thread...
        TReadJSON readJSON = new TReadJSON(this);
        Thread thread = new Thread(readJSON);
        thread.start();
    }

    private void gsonParse() {
        if(DApp.getReturnUserSTR() != null) {
            //MGSONParser gsonParse = new MGSONParser(this, MDataHolder::setApiGameList, MDataHolder::getApiGameList, MDataHolder.getReturnApiSTR());
            MGSONParser gsonParse = new MGSONParser(this, DApp::setUserGameList, DApp.getReturnUserSTR());
            Thread thread = new Thread(gsonParse);
            thread.start();

        }

    }
}
