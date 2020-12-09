package com.gamestash.app;

import android.content.Context;

import java.lang.ref.WeakReference;

public class PGameListUser {
    private static final String TAG = PGameListUser.class.getSimpleName();

    private WeakReference<VGameListUser> masterRef;

    public PGameListUser (VGameListUser activity) {
        this.masterRef = new WeakReference<>(activity);
    }

    public void deleteGame(int position){
        if(this.masterRef.get() != null) {
            this.masterRef.get().getGameList().remove(position);
            this.masterRef.get().updateAdapter();
            TSaveGame saveGame = new TSaveGame(this.masterRef.get(), true, true);
            Thread thread = new Thread(saveGame);
            thread.start();
        }

    };
}
