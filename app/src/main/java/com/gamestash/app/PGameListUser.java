package com.gamestash.app;

import java.lang.ref.WeakReference;

/**
 * <h1>PGameListUser</h1>
 * This is the presenter for VGameListUser.
 */
public class PGameListUser {
    private static final String TAG = PGameListUser.class.getSimpleName();

    private WeakReference<VGameListUser> masterRef;

    public PGameListUser (VGameListUser activity) {
        this.masterRef = new WeakReference<>(activity);
    }

    /**
     * deleteGame Deletes the game from the gameList at the set position.
     * Saves the gameList on a thread.
     * @param position
     */
    public void deleteGame(int position){
        if(this.masterRef.get() != null) {
            this.masterRef.get().getGameList().remove(position);
            this.masterRef.get().updateAdapter();
            TSaveGame saveGame = new TSaveGame(this.masterRef.get(), true, true);
            Thread thread = new Thread(saveGame);
            thread.start();
        }
    }
}
