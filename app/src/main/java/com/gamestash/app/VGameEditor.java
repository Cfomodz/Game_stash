package com.gamestash.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * VGameEditor allows games within a user's library to be edited.
 */

public class VGameEditor extends AppCompatActivity {
    private static final String TAG = VGameEditor.class.getSimpleName();

    private PGameEditor presenter;
    private int position = -1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_editor);

        // Must come before presenter.setupPresenter is called if game position is supplied.
        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);

        presenter = new PGameEditor(this, this);
        presenter.setupPresenter();
    }

    public void onclickSaveGame(View view) {
        // Pass to presenter...
        presenter.processChanges();
    }

    public void onclickDelete(View view) {
        // Pass to presenter...
        presenter.deleteGame();
    }

    @Override
    public Intent getSupportParentActivityIntent() {
        return getParentActivityIntentImplement();
    }

    @Override
    public Intent getParentActivityIntent() {
        return getParentActivityIntentImplement();
    }

    public Intent getParentActivityIntentImplement() {
        Intent intent = null;

        Bundle extras = getIntent().getExtras();
        String goToIntent = extras.getString("goto");

        if(goToIntent.equals("mainMenu")) {
            intent = new Intent (this, VMainMenu.class);
            //Set flags to reuse the previous activity instead of creating a new activity instance.
        } else {
            intent = new Intent (this, VGameDetailsUser.class);
            //Set flags to reuse the previous activity instead of creating a new activity instance.
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }

    public int getPosition() {return position;}
}