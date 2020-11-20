package com.gamestash.app;

import java.lang.ref.WeakReference;

public class PAPIGameDetails implements IPAPIGameDetails {
    private static final String TAG = PAPIGameDetails.class.getSimpleName();

    private WeakReference<VAPIGameDetails> masterRef;

    public PAPIGameDetails(VAPIGameDetails activity){
        this.masterRef = new WeakReference<>(activity);
    }

    @Override
    public void saveGameInUserList() {
        if (this.masterRef.get() !=  null){
            MSaveGame saveGame = new MSaveGame(this.masterRef.get(), this, this.masterRef.get().getGame());
            Thread thread = new Thread(saveGame);
            thread.start();
        }
    }

}
