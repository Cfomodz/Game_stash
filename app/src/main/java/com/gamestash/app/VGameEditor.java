package com.gamestash.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class VGameEditor extends AppCompatActivity {
    private static final String TAG = VGameEditor.class.getSimpleName();

    private DGame game = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_editor);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);

        if(position > -1) {
            // can you edit an api game first? not sure...
            this.game = DApp.getUserGameList().getGameList().get(position);
        } else {
            this.game = new DGame();
        }

    }

    public void saveGame(View view){

        //actually add the game to our JSON via game object,
        //which we need to create from users' input

        //Intent intent = new Intent(this, VGameListUser.class);
        //startActivity(intent);
    }

}