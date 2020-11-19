package com.example.game_stash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class VAPISearchResults extends AppCompatActivity {
    private static final String TAG = VAPISearchResults.class.getSimpleName();

    private IPresenter presenter = new PAPISearchResults(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_search_results);

        // Check for updates and reset flags before handling view...
        this.presenter.processUpdates();

        if(MDataHolder.getApiGameList() != null) {
            this.setListView();
        }
    }

    public void setListView() {
        setContentView(R.layout.activity_api_search_results);

        ListView listView = (ListView) findViewById(R.id.lvGameList);
        Log.d(TAG, "onCreate: Started.");

        List<MGame> gameList = MDataHolder.getApiGameList().getGameList();

        AAPISearchResults adapter = new AAPISearchResults(this, R.layout.item_layout_gamelist, gameList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), VAPIGameDetails.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
}