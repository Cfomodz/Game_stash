package com.gamestash.app;

public class PGameEditor implements IProcess, ISave {
    @Override
    public void processChanges() {
        //Validate
        if (this.validateGameData()) {
            //actually add the game to our JSON via game object

        } else {
            //do stuff to indicate the game is wrong...

        }

        //If successful then...??? Party!
        //Intent intent = new Intent(this, VGameListUser.class);
        //startActivity(intent);
    }

    @Override
    public void saveGameInUserList() {

    }

    //move to presenter...
    private Boolean validateGameData() {
        //return true
        return false;
    }
}