package com.example.game_stash;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class VAddGameSearch extends AppCompatActivity {
    private IPresenter presenter = new PGameSearch();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game_search);
    }

    public void onclickSearch() {
        //Call presenter...
        EditText editTextName = (EditText) findViewById(R.id.editTextTextPersonName7);
        String name = editTextName.getText().toString();
        this.presenter.doSearch(name);

    }
}