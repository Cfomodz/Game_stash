package com.example.game_stash;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class VAPISearchResults extends AppCompatActivity {
    private static final String TAG = "VAPISearchResults";
    private IPresenter presenter = new PAPISearchResults(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_search_results);

        this.setListView();
        this.presenter.processUpdates(); //check for updates and reset flags
    }

    public void setListView() {
        setContentView(R.layout.activity_api_search_results);

        ListView listView = (ListView) findViewById(R.id.lvGameList);
        Log.d(TAG, "onCreate: Started.");

        List<MGame> gameList;

        if(MDataHolder.getApiGameList() != null){
           gameList  = MDataHolder.getApiGameList().getGameList();
        } else {
            gameList = new ArrayList<>();
            gameList.add(new MGame("Please wait... still searching."));
        }

        AAPISearchResults adapter = new AAPISearchResults(this, R.layout.item_layout_gamelist, gameList);

        listView.setAdapter(adapter);
    }
}