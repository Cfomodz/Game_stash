package com.example.game_stash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class VUsersListOfGames extends AppCompatActivity {
    private static final String TAG = VUsersListOfGames.class.getSimpleName();

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list_of_games);

        if(MDataHolder.getUserGameList() != null) {
            this.setListView();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setListView() {
        setContentView(R.layout.activity_users_list_of_games);

        ListView listView = (ListView) findViewById(R.id.lv_game_list);
        Log.d(TAG, "onCreate: Started.");

        List<MGame> gameList = MDataHolder.getUserGameList().getGameList();

        AUserGameList adapter = new AUserGameList(this, R.layout.item_layout_gamelist, gameList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapter1, view, position, id) -> {

            Intent intent = new Intent(getApplicationContext(), VUserGameDetails.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });
    }
}