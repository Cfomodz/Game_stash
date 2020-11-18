package com.example.game_stash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VProfile extends AppCompatActivity {
    private static final String fName = VMainMenu.class.getSimpleName();
    private static final String TAG = fName + ":";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void upload(View view) {
        Intent intent = new Intent(this, VMainMenu.class);
        startActivity(intent);
        // for now this leads back to the main activity page
    }

    public void backup(View view) {
        Intent intent = new Intent(this, VMainMenu.class);
        startActivity(intent);
        // for now this leads back to the main activity page
    }
}