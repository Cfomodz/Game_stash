package com.gamestash.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class VAddGameUser extends AppCompatActivity {
    private static final String TAG = VAddGameUser.class.getSimpleName();


    @Override
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game_manually);

    }

    public void addGame(View view){

        //actually add the game to our JSON via game object,
        //which we need to create from users' input

        Intent intent = new Intent(this, VGameListUser.class);
        startActivity(intent);
    }

}