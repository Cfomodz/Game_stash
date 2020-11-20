package com.example.game_stash;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;

interface SetList{
    void set(MGameList m);
}

interface GetList{
    MGameList get();
}

public class MGSONParser implements Runnable{
    private static final String TAG = MGSONParser.class.getSimpleName();

    private WeakReference<IProcess> presenterRef;
    private String response;
    private MGameList gameListObj;
    private SetList setList;
    private GetList getList;

    public MGSONParser(IProcess presenter, SetList setList, GetList getList, String response) {
        this.presenterRef = new WeakReference<>(presenter);
        this.setList = setList;
        this.getList = getList;
        this.response = response;
    }



    //public MGameList getGameListObj() {
    //    return gameListObj;
    //}

    @Override
    public void run() {
        Gson gson = new Gson();
        this.setList.set(gson.fromJson(this.response, MGameList.class));
        this.gameListObj = this.getList.get();

        //REMOVEABLE::NEEDED TO DEBUG ONLY...
        if(gameListObj.getGameList() != null && !gameListObj.getGameList().isEmpty()) {
            Log.d(TAG, gameListObj.getGameList().get(0).getGameName());
        }

        //Notify Presenter of update...
        if (this.presenterRef.get() != null) {
            this.presenterRef.get().processChanges();
        }
    }
}
