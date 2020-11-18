package com.example.game_stash;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class VAPIGameDetails extends AppCompatActivity {
    private static final String fName = VMainMenu.class.getSimpleName();
    private static final String TAG = fName + ":";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_game_details);
    }
}