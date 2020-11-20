package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.gamestash.app.R;

public class VAddGameSearch extends AppCompatActivity {
    private static final String TAG = VAddGameSearch.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game_search);
    }

    public void onclickSearch(View view) {
        EditText editTextName = findViewById(R.id.et_game_name);
        String search = editTextName.getText().toString();

        MDataHolder.setSearchSTR(search);
        
        Intent intent = new Intent(this, VAPISearchResults.class);
        startActivity(intent);
    }
}