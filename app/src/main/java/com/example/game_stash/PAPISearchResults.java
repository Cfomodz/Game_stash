package com.example.game_stash;

import android.util.Log;

class PAPISearchResults implements IPresenter {
    private static final String TAG = "PGameSearch:";

    @Override
    public void processUpdates() {
        Log.d(TAG, "Beginning to process the updates");
        if(MDataHolder.getHasBeenEditedSearchSTR()) {
            //New Search String available: run search, reset the flag
            Log.d(TAG, "New searchSTR");

            //Run search
            this.doSearch(MDataHolder.getSearchSTR());
            //Reset the flag
            MDataHolder.setHasBeenEditedSearchSTR();
        }
        if(MDataHolder.getHasBeenEditedReturnApiSTR()) {
             //New response from API available: run gson parse, reset the flag
            Log.d(TAG, "New returnApiSTR");

            //Parse the JSON using GSON
            this.gsonParse(MDataHolder.getReturnApiSTR());

            //Reset the flag
            MDataHolder.setHasBeenEditedReturnApiSTR();
        }
        if(MDataHolder.getHasBeenEditedAPIGameList()) {
            //New game list from the API available: update view, reset the flag
            Log.d(TAG, "New apiGameList");

            //Update view ???

            //Reset the flag
            MDataHolder.setHasBeenEditedAPIGameList();
        }
        if(MDataHolder.getHasBeenEditedUserGameList()) {
            //New user game list available: reset the flag only
            Log.d(TAG, "New userGameList");

            //Reset the flag
            MDataHolder.setHasBeenEditedUserGameList();
        }

        //ALL FLAGS SHOUlD BE RESET...VERIFY
        Log.d(TAG, "Finished processing updates; verify flags.");
        Log.d(TAG, "\tHasBeenEditedSearchSTR: " + MDataHolder.getHasBeenEditedSearchSTR());
        Log.d(TAG, "\tHasBeenEditedReturnApiSTR: " + MDataHolder.getHasBeenEditedReturnApiSTR());
        Log.d(TAG, "\tHasBeenEditedAPIGameList: " + MDataHolder.getHasBeenEditedAPIGameList());
        Log.d(TAG, "\tHasBeenEditedUserGameList: " + MDataHolder.getHasBeenEditedUserGameList());
    }

    private void doSearch(String name) {
        Log.d(TAG, "Building API Query URL");
        String url = new MAPIQueryURL("", name, "", -1, 10,-1,-1).getUrl();
        Log.d(TAG, "URL: " + url);

        Log.d(TAG, "Beginning MAPI CONNECTION");
        MAPIConnection connection = new MAPIConnection(this, url);
        Thread thread = new Thread(connection);
        thread.start();
    }

    private void gsonParse(String response) {

        MGSONParser gsonParse = new MGSONParser(this, MDataHolder.getReturnApiSTR());
        Thread thread = new Thread(gsonParse);
        thread.start();
    }
}
