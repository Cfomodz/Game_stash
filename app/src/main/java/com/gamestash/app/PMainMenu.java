package com.gamestash.app;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * <h1>PMainMenu</h1>
 * Presenter for the Main menu. Handles input from the user and updates variables accordingly.
 */

public class PMainMenu implements IProcess{
    private static final String TAG = PMainMenu.class.getSimpleName();

    private WeakReference<VMainMenu> masterRef;

    public PMainMenu(VMainMenu activity){this.masterRef = new WeakReference<>(activity);}

    /**
     * processChanges checks to see if variables have been updated and, if they need to be,
     * runs the correct function to update them.
     */
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
            //getJSON(DApp.getUserLocationListJSONFile());
        }
        if(DApp.getReturnUserLocationListSTR().equals("")){
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

    /**
     * getJSON(File file) gets the JSON string from the file on a thread.
     * Calls processChanges to process the changes.
     * @param file
     */
    private void getJSON(File file) {
        //This should be in a thread...
        if (file.exists()){
            TReadJSON readJSON = new TReadJSON(this, file);
            Thread thread = new Thread(readJSON);
            thread.start();
        } else if(file.getName().equals(DApp.getUserLocationListJSONFileName())){
            String jsonString = DApp.getDefaultReturnUserLocationListSTR();
            String fileName = DApp.getUserLocationListJSONFileName();
            TSaveToFile saveToFile = new TSaveToFile(this.masterRef.get(), fileName, jsonString);
            Thread thread = new Thread(saveToFile);
            thread.start();
            DApp.setReturnJSONStr(jsonString, file);
            processChanges();
        }
    }

    /**
     * gsonParse creates a new class called MGSONParser and runs that on a thread.
     * Parsing the gson file. Requires setList to be SetGameList to know where to put the parsed object.
     * The response is the information returned from the getJSON function.
     * @param setList
     * @param response
     */
    private void gsonParse(SetGameList setList, String response) {
        // This is an overloaded method...
        if(DApp.getReturnUserSTR() != null) {
            MGSONParser gsonParse = new MGSONParser(this, setList, response);
            Thread thread = new Thread(gsonParse);
            thread.start();
        }
    }

    /**
     * gsonParse creates a new class called MGSONParser and runs that on a thread.
     * Parsing the gson file. Requires setList to be SetLocationList to know where to put the parsed object.
     * The response is the information returned from the getJSON function.
     * @param setList
     * @param response
     */
    private void gsonParse(SetLocationList setList, String response) {
        // This is an overloaded method...
        if(DApp.getReturnUserSTR() != null) {
            MGSONParser gsonParse = new MGSONParser(this, setList, response);
            Thread thread = new Thread(gsonParse);
            thread.start();
        }
    }
}
