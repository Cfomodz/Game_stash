package com.gamestash.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class VGameListUser extends AppCompatActivity {
    private static final String TAG = VGameListUser.class.getSimpleName();

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist_user);

        if(DApp.getUserGameList() != null) {
            this.setListView();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setListView() {
        setContentView(R.layout.activity_gamelist_user);

        ListView listView = findViewById(R.id.lv_game_list);
        Log.d(TAG, "onCreate: Started.");

        List<DGame> gameList = DApp.getUserGameList().getGameList();

        AGameListUser adapter = new AGameListUser(this, R.layout.item_layout_gamelist, gameList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapter1, view, position, id) -> {

            Intent intent = new Intent(getApplicationContext(), VGameDetailsUser.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });
    }
}