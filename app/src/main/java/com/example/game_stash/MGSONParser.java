package com.example.game_stash;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;

public class MGSONParser implements Runnable{
    private static final String fName = VMainMenu.class.getSimpleName();
    private static final String TAG = fName + ":";

    private WeakReference<IPresenter> presenterRef;
    private String response;
    private MGameList gameListObj;

    public MGSONParser(IPresenter presenter, String response) {
        this.presenterRef = new WeakReference<>(presenter);
        this.response = response;
    }

    public MGameList getGameListObj() {
        return gameListObj;
    }

    @Override
    public void run() {
        Gson gson = new Gson();
        MDataHolder.setApiGameList(gson.fromJson(this.response, MGameList.class));
        this.gameListObj = MDataHolder.getApiGameList();

        //REMOVEABLE::NEED TO DEBUG ONLY...
        if(!gameListObj.getGameList().isEmpty()) {
            Log.d(TAG, gameListObj.getGameList().get(0).getName());
        }

        //Notify Presenter of update...
        if (presenterRef.get() != null) {
            presenterRef.get().processUpdates();
        }



    }
}
