package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The VAddGameAPI class adds a selected game from the Board Game Atlas
 * API results to the user's collection.
 */

public class VAddGameAPI extends AppCompatActivity {
    private static final String TAG = VAddGameAPI.class.getSimpleName();

    /**
     * onCreate sets up the activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game_api);
    }

    /**
     * onclickSearch searches for a game by the game name.
     * @param view
     */
    public void onclickSearch(View view) {
        EditText editTextName = findViewById(R.id.et_add_game_api_game_name);
        String search = editTextName.getText().toString();

        DApp.setSearchSTR(search);
        
        Intent intent = new Intent(this, VGameListAPI.class);
        startActivity(intent);
    }
}