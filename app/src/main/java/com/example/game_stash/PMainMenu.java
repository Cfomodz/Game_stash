package com.example.game_stash;

import java.lang.ref.WeakReference;

public class PMainMenu implements IProcess{
    private static final String TAG = PMainMenu.class.getSimpleName();

    private WeakReference<VMainMenu> masterRef;

    public PMainMenu(VMainMenu activity){
        this.masterRef = new WeakReference<>(activity);
    }

    @Override
    public void processChanges() {

    }
}
