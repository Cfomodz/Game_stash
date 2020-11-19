package com.example.game_stash;

import java.lang.ref.WeakReference;

public class PAPIGameDetails implements IPresenter{
    private static final String TAG = PAPIGameDetails.class.getSimpleName();

    private WeakReference<VAPIGameDetails> masterRef;

    public PAPIGameDetails(VAPIGameDetails activity){
        this.masterRef = new WeakReference<>(activity);
    }

    @Override
    public void processUpdates() {
        this.saveGameInUserList();
    }

    public void saveGameInUserList() {
        if (this.masterRef.get() !=  null){
            MSaveGame saveGame = new MSaveGame(this.masterRef.get(), this.masterRef.get().getGame());
            Thread thread = new Thread(saveGame);
            thread.start();
        }
    }
}
