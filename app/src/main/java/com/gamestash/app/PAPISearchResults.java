package com.gamestash.app;

import android.util.Log;

import java.lang.ref.WeakReference;

class PAPISearchResults implements IProcess {
    private static final String TAG = PAPISearchResults.class.getSimpleName();

    private WeakReference<VGameListAPI> masterRef;

    public PAPISearchResults(VGameListAPI activity){
        this.masterRef = new WeakReference<>(activity);
    }

    @Override
    public void processChanges() {
        Log.d(TAG, "Beginning to process the updates");
        if(DApp.getHasBeenEditedSearchSTR()) {
            //New Search String available: run search, reset the flag
            Log.d(TAG, "New searchSTR");

            //Reset game list...
            DApp.setApiGameList(null);

            //Run search
            this.doSearch(DApp.getSearchSTR());
            //Reset the flag
            DApp.setHasBeenEditedSearchSTR();
        }
        if(DApp.getHasBeenEditedReturnApiSTR()) {
             //New response from API available: run gson parse, reset the flag
            Log.d(TAG, "New returnApiSTR");

            //Parse the JSON using GSON
            this.gsonParse();

            //Reset the flag
            DApp.setHasBeenEditedReturnApiSTR();
        }
        if(DApp.getHasBeenEditedAPIGameList()) {
            //New game list from the API available: update view, reset the flag
            Log.d(TAG, "New apiGameList");

            //Update view adapter, unless the game list was set to null...
            if (masterRef.get() != null & DApp.getApiGameList() != null) {
                masterRef.get().runOnUiThread(() -> masterRef.get().setListView());
            }

            //Reset the flag
            DApp.setHasBeenEditedAPIGameList();
        }

        //ALL FLAGS SHOUlD BE RESET...VERIFY
        Log.d(TAG, "Finished processing updates.");
        Log.d(TAG, "Verify Flags: Should all be false...");
        Log.d(TAG, "\tHasBeenEditedSearchSTR: " + DApp.getHasBeenEditedSearchSTR());
        Log.d(TAG, "\tHasBeenEditedReturnApiSTR: " + DApp.getHasBeenEditedReturnApiSTR());
        Log.d(TAG, "\tHasBeenEditedAPIGameList: " + DApp.getHasBeenEditedAPIGameList());
        Log.d(TAG, "\tHasBeenEditedUserGameList: " + DApp.getHasBeenEditedUserGameList());
    }

    private void doSearch(String name) {
        Log.d(TAG, "Building API Query URL");
        String url = new MAPIQueryURL("", name, "", -1, -1,-1,-1).getUrl();
        Log.d(TAG, "URL: " + url);

        Log.d(TAG, "Beginning MAPI CONNECTION");
        MAPIConnection connection = new MAPIConnection(this, url);
        Thread thread = new Thread(connection);
        thread.start();
    }

    private void gsonParse() {
        //MGSONParser gsonParse = new MGSONParser(this, MDataHolder::setApiGameList, MDataHolder::getApiGameList, MDataHolder.getReturnApiSTR());
        MGSONParser gsonParse = new MGSONParser(this, DApp::setApiGameList, DApp.getReturnApiSTR());
        Thread thread = new Thread(gsonParse);
        thread.start();
    }
}
