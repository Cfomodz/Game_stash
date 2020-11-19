package com.example.game_stash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VAPIGameDetails extends AppCompatActivity {
    private static final String TAG = VAPIGameDetails.class.getSimpleName();
    private IPAPIGameDetails presenter = new PAPIGameDetails(this);
    private MGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_game_details);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        this.game = MDataHolder.getApiGameList().getGameList().get(position);

        //use game to fill out display...
        Log.d(TAG, game.getGameName());
    }

    public void onclickSave(View view) {

        // Trigger PAPIGameDetails method to save...
        presenter.saveGameInUserList();
    }

    public MGame getGame() {
        return game;
    }
}