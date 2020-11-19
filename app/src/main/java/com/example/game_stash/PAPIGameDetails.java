package com.example.game_stash;

import java.lang.ref.WeakReference;

public class PAPIGameDetails implements IPAPIGameDetails {
    private static final String TAG = PAPIGameDetails.class.getSimpleName();

    private WeakReference<VAPIGameDetails> masterRef;

    public PAPIGameDetails(VAPIGameDetails activity){
        this.masterRef = new WeakReference<>(activity);
    }

    @Override
    public void processUpdates() {
        if(MDataHolder.getHasBeenEditedUserGameList()) {
            //New user game list available

            //Create JSON String
            this.createJSONString();

            //Save JSON file
            //NOTE AS IS THIS WILL GET OUT OF SYNC... because create JSON string is threaded and doesn't report back
            this.saveJSONToPhone();

            //Reset the flag to false
            MDataHolder.setHasBeenEditedUserGameList();
        }
    }

    @Override
    public void saveGameInUserList() {
        if (this.masterRef.get() !=  null){
            MSaveGame saveGame = new MSaveGame(this.masterRef.get(), this, this.masterRef.get().getGame());
            Thread thread = new Thread(saveGame);
            thread.start();
        }
    }

    private void createJSONString() {
        MObjToJSON objToJSON = new MObjToJSON(MDataHolder.getUserGameList());
        Thread thread = new Thread(objToJSON);
        thread.start();

    }

    private void saveJSONToPhone() {
        //SAVE TO JSON file
        MSaveUserGameList saveUserGameList = new MSaveUserGameList();
        Thread thread = new Thread(saveUserGameList);
        thread.start();
    }

}
