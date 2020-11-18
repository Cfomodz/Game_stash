package com.example.game_stash;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class VAPISearchResults extends AppCompatActivity {
    private static final String fName = VMainMenu.class.getSimpleName();
    private static final String TAG = fName + ":";

    private IPresenter presenter = new PAPISearchResults(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_search_results);

        this.presenter.processUpdates(); //check for updates and reset flags
    }

    public void setListView() {
        setContentView(R.layout.activity_api_search_results);

        ListView listView = (ListView) findViewById(R.id.lvGameList);
        Log.d(TAG, "onCreate: Started.");

        List<MGame> gameList = MDataHolder.getApiGameList().getGameList();

        AAPISearchResults adapter = new AAPISearchResults(this, R.layout.item_layout_gamelist, gameList);

        listView.setAdapter(adapter);
    }
}