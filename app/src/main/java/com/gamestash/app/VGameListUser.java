package com.gamestash.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class VGameListUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = VGameListUser.class.getSimpleName();
    private PGameListUser presenter;
    private List<DGame> gameList;
    private AGameListUser adapter;
    Dialog myDialog;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist_user);

        presenter = new PGameListUser(this);

        if(DApp.getUserGameList() != null) {
            this.setListView();
        }
        myDialog = new Dialog(this);
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


    public void onClickFilter(View view){
        TextView txtClose;
        Spinner spinnerOne;
        Spinner spinnerTwo;
        myDialog.setContentView(R.layout.activity_gamelist_user_popup_filter);
        txtClose = (TextView) myDialog.findViewById(R.id.popupCloseButton);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        initializeViews();
        myDialog.show();
    }

    public void initializeViews () {
        Spinner spinnerOne = myDialog.findViewById(R.id.spinnerOne);

        ArrayAdapter<CharSequence> adapterOne = ArrayAdapter.createFromResource(this, R.array.categories, R.layout.filter_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();

        spinnerOne.setAdapter(adapterOne);
        updateSpinnerTwo();

        spinnerOne.setOnItemSelectedListener(this);


    }

    public void updateSpinnerTwo(){
        Spinner spinnerTwo = myDialog.findViewById(R.id.spinnerTwo);

        ArrayAdapter<CharSequence> adapterTwo = ArrayAdapter.createFromResource(this, R.array.spinnerTwo, R.layout.filter_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTwo.setAdapter(adapterTwo);
        spinnerTwo.setOnItemSelectedListener(this);

        //adapter.notifyDataSetChanged();

    }

    public void filterGames(String text){

        if(text.equals("No Of Players")){
            Toast.makeText(this, "Filter by: No of Players", Toast.LENGTH_SHORT).show();
            //call to filter games by no of players
            Log.d(TAG, filterGamesByNoPlayers().toString());
            //filterGamesByNoPlayers();
        }
        if(text.equals("Play Time")){
            Toast.makeText(this, "Filter by: Play Time:", Toast.LENGTH_SHORT).show();
            //call to filter games by amount of time
            Log.d(TAG, filterGamesByTime().toString());
            //filterGamesByTime();
        }
        if(text.equals("Locations")){
            Toast.makeText(this, "Filter by: Locations", Toast.LENGTH_SHORT).show();
            //call to filter games by Location
            Log.d(TAG, filterGamesByLocation().toString());
            //filterGamesByLocation();
        }
        if(text.equals("Wish List")){
            Toast.makeText(this, "Filter by: Wish List", Toast.LENGTH_SHORT).show();
            //call to filter games by WishList
            Log.d(TAG, filterGamesByWishList().toString());
            //filterGamesByWishList();
        }

    }

    public List<Integer> filterGamesByNoPlayers() {

        List<DGame> gameList = DApp.getUserGameList().getGameList();
        List<Integer> gamesPlayers = new ArrayList<>();

        for (DGame game : gameList) {
            int min = 0;
            int max = 0;
            if (game.getMinPlayers() > 0) {
                min = game.getMinPlayers();
            }
            if (game.getMaxPlayers() > 0) {
                max = game.getMaxPlayers();
            }
            for (int i = min; i <= max; i++) {
                gamesPlayers.add(i);
            }
        }

        //Log.d(TAG, gamesPlayers.toString());

        //Make gamesPlayers Unique
        List<Integer> gamesPlayersWithoutDuplicates = new ArrayList<>(
                new HashSet<>(gamesPlayers));

        //this is the final results that should be shown in the second spinner
        //Log.d(TAG, gamesPlayersWithoutDuplicates.toString());
        return gamesPlayersWithoutDuplicates;
    }

    public List<Integer> filterGamesByTime() {
        List<DGame> gameList = DApp.getUserGameList().getGameList();
        List<Integer> gamesTime = new ArrayList<>();

        for (DGame game : gameList) {
            int min = 0;
            int max = 0;
            if (game.getMinPlayTime() > 0) {
                min = game.getMinPlayTime();
            }
            if (game.getMaxPlayTime() > 0) {
                max = game.getMaxPlayTime();
            }
            for (int i = min; i <= max; i++) {
                gamesTime.add(i);
            }
        }

        //Log.d(TAG, gamesTime.toString());

        //Make gamesTime Unique
        List<Integer> gamesTimeWithoutDuplicates = new ArrayList<>(
                new HashSet<>(gamesTime));

        //Log.d(TAG, gamesTimeWithoutDuplicates.toString());

        List<Integer> gamesTimeOptions = new ArrayList<>();
        for (Integer i : gamesTimeWithoutDuplicates) {
            if (i > 4) {
                if (i % 5 == 0) {
                    gamesTimeOptions.add(i);
                }
            }else{
                gamesTimeOptions.add(i);
            }
        }

        //this is the final results that should be shown in the second spinner
        //Log.d(TAG, gamesTimeOptions.toString());
        return gamesTimeOptions;
    }

    public List<String> filterGamesByWishList(){
        List<String> wishListOptions = new ArrayList<>();
        wishListOptions.add("Show");
        wishListOptions.add("Hide");

        //this is the final results that should be shown in the second spinner
        //Log.d(TAG, wishListOptions.toString());
        return wishListOptions;
    }

    public List<String> filterGamesByLocation(){
        List<DGame> gameList = DApp.getUserGameList().getGameList();
        List<String> listWithDuplicates =  new ArrayList<>();
        Log.d(TAG,listWithDuplicates.toString());
        listWithDuplicates.add("Wish List");
        for (DGame game: gameList){
            if(game.getLocation().trim().length()>0) {
                listWithDuplicates.add(game.getLocation());
            }
        }
        List<String> listWithoutDuplicates = listWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());

        //this is the final results that should be shown in the second spinner
        //Log.d(TAG,listWithoutDuplicates.toString());
        return listWithoutDuplicates;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        filterGames(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}