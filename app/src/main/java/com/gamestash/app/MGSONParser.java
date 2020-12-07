package com.gamestash.app;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

interface SetList{
    void set(DGameList m);
}

public class MGSONParser implements Runnable{
    private static final String TAG = MGSONParser.class.getSimpleName();

    private WeakReference<IProcess> presenterRef;
    private String response;
    private DGameList gameListObj;
    private SetList setList;
    //private GetList getList;

    public MGSONParser(IProcess presenter, SetList setList, String response) {
        this.presenterRef = new WeakReference<>(presenter);
        this.setList = setList;
        this.response = response;
    }

    /**
    public MGSONParser(IProcess presenter, SetList setList, GetList getList, String response) {
        this.presenterRef = new WeakReference<>(presenter);
        this.setList = setList;
        this.getList = getList;
        this.response = response;
    }
     */

    @Override
    public void run() {
        Gson gson = new Gson();

        this.gameListObj = gson.fromJson(this.response, DGameList.class);

        if(gameListObj.getGameList() != null && !gameListObj.getGameList().isEmpty()) {
            this.setList.set(this.gameListObj);
            Log.d(TAG, gameListObj.getGameList().get(0).getGameName());
        } else {
            DGame game = new DGame(true);
            this.gameListObj = new DGameList(game);
            this.setList.set(this.gameListObj);
        }

        //Notify Presenter of update...
        if (this.presenterRef.get() != null) {
            this.presenterRef.get().processChanges();
        }
    }
}
