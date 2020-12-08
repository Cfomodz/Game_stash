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


        listView.setOnItemLongClickListener((adapter1, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("DELETE GAME")
                    .setMessage("Do you want to delete this game?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //DO STUFF
                            showDeleteToast();
                            deleteGame(position, gameList);
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .show();




            return true;
        });

    }


    public void showDeleteToast() {
        Toast.makeText(this, "GAME DELETED", Toast.LENGTH_SHORT).show();
    }

    private void deleteGame(int position, List<DGame> gameList){
        gameList.remove(position);
        //Save game list again... on presenter...

    };
}