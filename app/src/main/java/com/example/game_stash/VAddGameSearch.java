package com.example.game_stash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VAddGameSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game_search);
    }

    public void onClickGoToAddGameSearch(View view){
        Intent intent = new Intent(this, VAddGameSearch.class);
        startActivity(intent);
    }
}