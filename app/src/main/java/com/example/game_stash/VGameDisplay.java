package com.example.game_stash;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class VGameDisplay extends AppCompatActivity {
    private static final String TAG = VGameDisplay.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);
    }
}