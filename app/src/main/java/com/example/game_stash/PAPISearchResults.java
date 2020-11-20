package com.example.game_stash;

import android.util.Log;

import java.lang.ref.WeakReference;

class PAPISearchResults implements IProcess {
    private static final String TAG = PAPISearchResults.class.getSimpleName();

    private WeakReference<VAPISearchResults> masterRef;

    public PAPISearchResults(VAPISearchResults activity){
        this.masterRef = new WeakReference<>(activity);
    }

    @Override
    public void processChanges() {
        Log.d(TAG, "Beginning to process the updates");
        if(MDataHolder.getHasBeenEditedSearchSTR()) {
            //New Search String available: run search, reset the flag
            Log.d(TAG, "New searchSTR");

            //Reset game list...
            MDataHolder.setApiGameList(null);

            //Run search
            this.doSearch(MDataHolder.getSearchSTR());
            //Reset the flag
            MDataHolder.setHasBeenEditedSearchSTR();
        }
        if(MDataHolder.getHasBeenEditedReturnApiSTR()) {
             //New response from API available: run gson parse, reset the flag
            Log.d(TAG, "New returnApiSTR");

            //Parse the JSON using GSON
            this.gsonParse();

            //Reset the flag
            MDataHolder.setHasBeenEditedReturnApiSTR();
        }
        if(MDataHolder.getHasBeenEditedAPIGameList()) {
            //New game list from the API available: update view, reset the flag
            Log.d(TAG, "New apiGameList");

            //Update view adapter, unless the game list was set to null...
            if (masterRef.get() != null & MDataHolder.getApiGameList() != null) {
                masterRef.get().runOnUiThread(() -> masterRef.get().setListView());
            }

            //Reset the flag
            MDataHolder.setHasBeenEditedAPIGameList();
        }

        //ALL FLAGS SHOUlD BE RESET...VERIFY
        Log.d(TAG, "Finished processing updates.");
        Log.d(TAG, "Verify Flags: Should all be false...");
        Log.d(TAG, "\tHasBeenEditedSearchSTR: " + MDataHolder.getHasBeenEditedSearchSTR());
        Log.d(TAG, "\tHasBeenEditedReturnApiSTR: " + MDataHolder.getHasBeenEditedReturnApiSTR());
        Log.d(TAG, "\tHasBeenEditedAPIGameList: " + MDataHolder.getHasBeenEditedAPIGameList());
        Log.d(TAG, "\tHasBeenEditedUserGameList: " + MDataHolder.getHasBeenEditedUserGameList());
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
        MGSONParser gsonParse = new MGSONParser(this, MDataHolder::setApiGameList, MDataHolder.getReturnApiSTR());
        Thread thread = new Thread(gsonParse);
        thread.start();
    }
}
