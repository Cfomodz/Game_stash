package com.example.game_stash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VErrorPage extends AppCompatActivity {
    private static final String fName = VMainMenu.class.getSimpleName();
    private static final String TAG = fName + ":";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_page);
    }

    public void onClickGoToAddGameManually(View view){
        Intent intent = new Intent(this, VAddGameManually.class);
        startActivity(intent);
    }
}