package com.example.game_stash;

import android.util.Log;

class PAPISearchResults implements IPresenter {
    private static final String TAG = "PGameSearch:";

    private static final String TAG_B = "PGameSearch_B:";
    private static final String TAG_T = "PGameSearch_T:";
    private static final String TAG_M = "PGameSearch_M:";
    private static final String TAG_A = "PGameSearch_A:";

    @Override
    public void dataHolderUpdated() {
        //
    }

    @Override
    public void doSearch(String name) {
        Log.d(TAG_B, "Building API Query URL");
        String test = new MAPIQueryURL("", name, "", -1, 10,-1,-1).getUrl();
        Log.d(TAG_B, "URL: " + test);

        MAPIConnection connection = new MAPIConnection(null, test);
        Log.d(TAG_B, "Beginning MAPI CONNECTION");
        Thread thread = new Thread(connection);
        thread.start();
    }
}
