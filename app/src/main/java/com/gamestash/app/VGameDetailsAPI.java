package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * VGameDetailAPI shows the details of game objects that are returned
 * from the Game Atlas API after a user's search
 */

public class VGameDetailsAPI extends AppCompatActivity {
    private static final String TAG = VGameDetailsAPI.class.getSimpleName();

    private PGameDetailsAPI presenter = new PGameDetailsAPI(this);
    private int position;

    /**
     * onCreate sets up the activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details_api);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        presenter.setupPresenter();
    }

    /**
     * onClickSave saves the game in the userList by calling the function in the presenter.
     * @param view
     */
    public void onclickSave(View view) {
        // Trigger PGameDetailsAPI method to save...
        presenter.saveGameInUserList();
    }

    /**
     * getPosition returns a list position.
     * @return
     */
    public int getPosition() {return position;}
}