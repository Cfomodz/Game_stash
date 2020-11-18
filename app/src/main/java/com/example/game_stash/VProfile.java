package com.example.game_stash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void upload(View view) {
        Intent intent = new Intent(this, VMenu.class);
        startActivity(intent);
        // for now this leads back to the main activity page
    }

    public void backup(View view) {
        Intent intent = new Intent(this, VMenu.class);
        startActivity(intent);
        // for now this leads back to the main activity page
    }
}