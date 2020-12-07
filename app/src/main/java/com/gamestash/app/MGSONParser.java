package com.gamestash.app;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

interface SetGameList{
    void set(DGameList m);
}

interface SetLocationList{
    void set(DLocationList m);
}

public class MGSONParser implements Runnable{
    private static final String TAG = MGSONParser.class.getSimpleName();

    private WeakReference<IProcess> presenterRef;
    private String response;
    private DGameList gameListObj;
    private DLocationList locationListObj;
    private SetGameList setGameList;
    private SetLocationList setLocationList;
    private String setListType = "";
    //private GetList getList;

    public MGSONParser(IProcess presenter, SetGameList setList, String response) {
        this.presenterRef = new WeakReference<>(presenter);
        this.setGameList = setList;
        this.response = response;
        this.setListType = "game";
    }

    public MGSONParser(IProcess presenter, SetLocationList setList, String response) {
        this.presenterRef = new WeakReference<>(presenter);
        this.setLocationList = setList;
        this.response = response;
        this.setListType = "location";
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

        if(setListType.equals("game")) {
            this.gameListObj = gson.fromJson(this.response, DGameList.class);

            if(gameListObj.getGameList() != null && !gameListObj.getGameList().isEmpty()) {
                this.setGameList.set(this.gameListObj);
                Log.d(TAG, gameListObj.getGameList().get(0).getGameName());
            } else {
                DGame game = new DGame(true);
                this.gameListObj = new DGameList(game);
                this.setGameList.set(this.gameListObj);
            }
        } else if(setListType.equals("location")) {
            this.locationListObj = gson.fromJson(this.response, DLocationList.class);
            if(locationListObj.getLocationList() != null && !locationListObj.getLocationList().isEmpty()) {
                this.setLocationList.set(this.locationListObj);
            }
        }



        //Notify Presenter of update...
        if (this.presenterRef.get() != null) {
            this.presenterRef.get().processChanges();
        }
    }
}
