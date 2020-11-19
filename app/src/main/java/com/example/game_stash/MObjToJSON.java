package com.example.game_stash;

import android.util.Log;

import com.google.gson.Gson;

public class MObjToJSON implements Runnable{
    private static final String TAG = MObjToJSON.class.getSimpleName();
        private Object obj;

    public MObjToJSON(Object obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        Log.d(TAG, new Gson().toJson(this.obj));
    }
}
