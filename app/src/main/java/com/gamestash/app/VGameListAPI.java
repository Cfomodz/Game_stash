package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class VGameListAPI extends AppCompatActivity {
    private static final String TAG = VGameListAPI.class.getSimpleName();

    private IProcess presenter = new PAPISearchResults(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist_api);

        // Check for updates and reset flags before handling view...
        this.presenter.processChanges();

        if(DApp.getApiGameList() != null) {
            this.setListView();
        }
    }

    public void setListView() {
        setContentView(R.layout.activity_gamelist_api);

        ListView listView = findViewById(R.id.lv_game_list);
        Log.d(TAG, "onCreate: Started.");

        List<DGame> gameList = DApp.getApiGameList().getGameList();

        AGameListAPI adapter = new AGameListAPI(this, R.layout.item_layout_gamelist, gameList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapter1, view, position, id) -> {

            Intent intent = new Intent(getApplicationContext(), VGameDetailsAPI.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });
    }
}