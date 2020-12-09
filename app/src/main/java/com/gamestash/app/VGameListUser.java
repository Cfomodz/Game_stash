package com.gamestash.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class VGameListUser extends AppCompatActivity {
    private static final String TAG = VGameListUser.class.getSimpleName();

    private PGameListUser presenter;

    private List<DGame> gameList;
    private AGameListUser adapter;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist_user);

        presenter = new PGameListUser(this);

        if(DApp.getUserGameList() != null) {
            this.setListView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setListView() {
        setContentView(R.layout.activity_gamelist_user);

        ListView listView = findViewById(R.id.lv_game_list);
        Log.d(TAG, "onCreate: Started.");

        gameList = DApp.getUserGameList().getGameList();
        adapter = new AGameListUser(this, R.layout.item_layout_gamelist, gameList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapter1, view, position, id) -> {

            if(gameList.get(position).getGameID().equals("err404")) {
                Toast toast = Toast.makeText(this, "NOT A REAL GAME", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Intent intent = new Intent(getApplicationContext(), VGameDetailsUser.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });


        listView.setOnItemLongClickListener((adapter1, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if(gameList.get(position).getGameID().equals("err404")) {
                builder.setTitle("DELETE GAME")
                        .setMessage("Add a new game to remove this entry.")
                        .setPositiveButton("OK", null)
                        .show();
            } else {
                builder.setTitle("DELETE GAME")
                        .setMessage("Do you want to delete this game?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.deleteGame(position);
                            }
                        })
                        .show();
            }
            return true;
        });
    }

    public List<DGame> getGameList() {
        return gameList;
    }

    public void updateAdapter() {
        adapter.notifyDataSetChanged();
    }
}