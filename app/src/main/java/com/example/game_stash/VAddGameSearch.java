package com.example.game_stash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class VAddGameSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game_search);
    }

    public void onclickSearch(View view) {
        EditText editTextName = (EditText) findViewById(R.id.etName);
        MDataHolder.setSearchSTR(editTextName.getText().toString());

        Intent intent = new Intent(this, VAPISearchResults.class);
        startActivity(intent);
    }
}