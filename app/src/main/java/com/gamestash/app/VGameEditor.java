package com.gamestash.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class VGameEditor extends AppCompatActivity {
    private static final String TAG = VGameEditor.class.getSimpleName();

    private PGameEditor presenter;

    private DGame game = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_editor);

        presenter = new PGameEditor(this, this);
        presenter.setupPresenter();

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);

        if(position > -1) {
            // can you edit an api game first? not sure...yes
            this.game = DApp.getUserGameList().getGameList().get(position);
            //load the game editor with the game obj field data...
        } else {
            this.game = new DGame();
        }

    }

    public void onclickSaveGame(View view) {
        //Pass to presenter...
        presenter.processChanges();
    }

}