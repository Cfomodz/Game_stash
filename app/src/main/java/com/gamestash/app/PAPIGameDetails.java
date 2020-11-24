package com.gamestash.app;

import java.lang.ref.WeakReference;

public class PAPIGameDetails implements ISave {
    private static final String TAG = PAPIGameDetails.class.getSimpleName();

    private WeakReference<VGameDetailsAPI> masterRef;

    public PAPIGameDetails(VGameDetailsAPI activity){
        this.masterRef = new WeakReference<>(activity);
    }

    @Override
    public void saveGameInUserList() {
        if (this.masterRef.get() !=  null){
            TSaveGame saveGame = new TSaveGame(this.masterRef.get(), this, this.masterRef.get().getGame());
            Thread thread = new Thread(saveGame);
            thread.start();
        }
    }

}
