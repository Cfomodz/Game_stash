package com.example.game_stash;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class VAddGameSearch extends AppCompatActivity {
    private IPresenter presenter = new PGameSearch();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game_search);
    }

    public void onclickSearch(View view) {
        EditText editTextName = (EditText) findViewById(R.id.etName);
        String gameName = editTextName.getText().toString();
        this.presenter.doSearch(gameName);
    }
}