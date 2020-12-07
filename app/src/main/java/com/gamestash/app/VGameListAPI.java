package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

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

        //RETURN LIST...
        List<DGame> gameList = DApp.getApiGameList().getGameList();
        // NEED TO DO::ADD FILTER HERE; Currently not implemented

        AGameListAPI adapter = new AGameListAPI(this, R.layout.item_layout_gamelist, gameList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapter1, view, position, id) -> {

            if(!gameList.get(position).getGameID().equals("err404")){
                Intent intent = new Intent(getApplicationContext(), VGameDetailsAPI.class);
                intent.putExtra("position", position);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(this, "GAME NOT FOUND. SEARCH AGAIN.", Toast.LENGTH_SHORT);
                toast.show();
            }


        });
    }
}