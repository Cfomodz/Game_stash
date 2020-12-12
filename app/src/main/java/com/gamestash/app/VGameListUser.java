package com.gamestash.app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Shows user a list of games that they have previously added to their collection.
 * Further allows filtering the display of those games by certain criteria.
 */

public class VGameListUser extends AppCompatActivity {
    private static final String TAG = VGameListUser.class.getSimpleName();
    private PGameListUser presenter;
    private List<DGame> gameList;
    private AGameListUser adapter;
    private List<Integer> positionGameList;
    Dialog myDialog;

    /**
     * Displays list of games to the visible list view.
     * @param savedInstanceState
     */
    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist_user);

        presenter = new PGameListUser(this);
        if(DApp.getUserGameList() != null) {
            gameList = DApp.getUserGameList().getGameList();
            this.setListView(gameList, false);
        }
        myDialog = new Dialog(this);
    }

/*
    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }
*/

    /**
     * Resets the list of games to the original collection
     */
    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null){
            setListView(gameList,false);
        }
    }

    /**
     * Displays the games of a provided list of type DGame
     * (Entire collection or filtered)
     * @param listOfGames
     * @param filtered
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setListView(List<DGame> listOfGames, boolean filtered) {
        setContentView(R.layout.activity_gamelist_user);

        ListView listView = findViewById(R.id.lv_game_list);
        //Log.d(TAG, "onCreate: Started.");

        adapter = new AGameListUser(this, R.layout.item_layout_gamelist, listOfGames);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapter1, view, position, id) -> {

            if(listOfGames.get(position).getGameID().equals("err404")) {
                Toast toast = Toast.makeText(this, "NOT A REAL GAME", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Intent intent = new Intent(getApplicationContext(), VGameDetailsUser.class);
                if (filtered){
                    intent.putExtra("position", positionGameList.get(position));
                } else {
                    intent.putExtra("position", position);
                }
                startActivity(intent);
            }
        });


        listView.setOnItemLongClickListener((adapter1, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if(listOfGames.get(position).getGameID().equals("err404")) {
                builder.setTitle("DELETE GAME")
                        .setMessage("Add a new game to remove this entry.")
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
                                if (filtered){
                                    presenter.deleteGame(positionGameList.get(position));
                                } else {
                                    presenter.deleteGame(position);
                                }
                            }
                        })
                        .show();
            }
            return true;
        });
    }

    /**
     * Supplies entire collection
     * @return
     */
    public List<DGame> getGameList() {
        return gameList;
    }

    public void updateAdapter() {
        if(adapter != null){
            setListView(gameList,false);
        }
    }


    /**
     * onClickFilter is used to popup the filter dialog
     * @param view
     */
    public void onClickFilter(View view){
        TextView txtClose;
        myDialog.setContentView(R.layout.activity_gamelist_user_popup_filter);
        txtClose = myDialog.findViewById(R.id.popupCloseButton);
        txtClose.setOnClickListener(v -> myDialog.dismiss());
        initializeViews();
        myDialog.show();
    }

    /**
     * initializeViews sets up the initial state of the spinners within the filter dialog
     */
    public void initializeViews () {
        Spinner spinnerOne = myDialog.findViewById(R.id.spinnerOne);

        List<String> spinnerCategories = Arrays.asList("Select Filter", "No Of Players", "Play Time", "Location", "Wish List");

        ArrayAdapter<String> adapterOne = new ArrayAdapter<>(this, R.layout.filter_spinner_item, spinnerCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> spinnerTwoDefaultText = Collections.singletonList("Select a category above");

        spinnerOne.setAdapter(adapterOne);
        updateSpinnerTwo(spinnerTwoDefaultText);

        SpinnerInteractionListener listener = new SpinnerInteractionListener();
        spinnerOne.setOnTouchListener(listener);
        spinnerOne.setOnItemSelectedListener(listener);
    }

    /**
     * updateSpinnerTwo is used to change the options within the lower spinner based
     * upon the category selected in the first spinner
     * @param spinnerArray
     */
    public void updateSpinnerTwo(List<String> spinnerArray){
        Spinner spinnerTwo = myDialog.findViewById(R.id.spinnerTwo);

        ArrayAdapter<String> adapterTwo = new ArrayAdapter<String>
                (this, R.layout.filter_spinner_item,
                        spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Toast toast = Toast.makeText(this, (CharSequence) spinnerArray, Toast.LENGTH_SHORT);

        //adapter.notifyDataSetChanged();
        spinnerTwo.setAdapter(adapterTwo);
        SpinnerTwoInteractionListener listener = new SpinnerTwoInteractionListener();
        spinnerTwo.setOnTouchListener(listener);
        spinnerTwo.setOnItemSelectedListener(listener);

    }

    /**
     * showGames is used to determine the list of DGames that should be displayed based on
     * a user's selected filter.
     * @param text
     */
    public void showGames(String text){
        gameList = DApp.getUserGameList().getGameList();
        List<DGame> filteredGameList = new ArrayList<>();
        positionGameList = new ArrayList<>();
        String[] parts = text.split(" ");
        switch (DFilter.getCategory()){
            case "No Of Players":
                int noPlayers = Integer.parseInt(parts[0]);
                //Log.d(TAG, Integer.toString(noPlayers));
                for (DGame game: gameList){
                    if (noPlayers >= game.getMinPlayers() && noPlayers <= game.getMaxPlayers()){
                        filteredGameList.add(game);
                        positionGameList.add(gameList.indexOf(game));
                    }
                }
                break;
            case "Play Time":
                int playTime = Integer.parseInt(parts[0]);
                //Log.d(TAG, Integer.toString(playTime));
                for (DGame game: gameList){
                    if (playTime >= game.getVisibleMinPlayTime()){
                        filteredGameList.add(game);
                        positionGameList.add(gameList.indexOf(game));
                    }
                }
                break;
            case "Location":
                //Log.d(TAG, text);
                for (DGame game: gameList){
                    if (text.equals(game.getLocation())){
                        filteredGameList.add(game);
                        positionGameList.add(gameList.indexOf(game));
                    }
                }
                break;
            case "Wish List":
                //Log.d(TAG, text);
                for (DGame game: gameList){
                    if (DFilter.getSelection() == "Only Wish List") {
                        if (game.getLocation().equals("Wish List")) {
                            filteredGameList.add(game);
                            positionGameList.add(gameList.indexOf(game));
                        }
                    } else if (!game.getLocation().equals("Wish List")){
                            filteredGameList.add(game);
                            positionGameList.add(gameList.indexOf(game));
                        }
                    }
                break;
        }

        setListView(filteredGameList, true);

        //filter from that list
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * resetGameList sets the member variable gamelist back to the full
     * collection of games then updates the view.
     */
    public void resetGameList(){
        gameList = DApp.getUserGameList().getGameList();
    }

    public void onClickResetGameList(){
        resetGameList();
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }

    /**filterGames is used to determine which filter method should be called
     * based on the category selected.
     * @param text
     */
    public void filterGames(String text){

        if(text.equals("No Of Players")){
            Toast.makeText(this, "Filter by: No of Players", Toast.LENGTH_SHORT).show();
            //call to filter games by no of players
            //Log.d(TAG, filterGamesByNoPlayers().toString());
            updateSpinnerTwo(filterGamesByNoPlayers());
        }
        if(text.equals("Play Time")){
            Toast.makeText(this, "Filter by: Play Time:", Toast.LENGTH_SHORT).show();
            //call to filter games by amount of time
            //Log.d(TAG, filterGamesByTime().toString());
            updateSpinnerTwo(filterGamesByTime());
        }
        if(text.equals("Location")){
            Toast.makeText(this, "Filter by: Locations", Toast.LENGTH_SHORT).show();
            //call to filter games by Location
            //Log.d(TAG, filterGamesByLocation().toString());
            updateSpinnerTwo(filterGamesByLocation());
        }
        if(text.equals("Wish List")){
            Toast.makeText(this, "Filter by: Wish List", Toast.LENGTH_SHORT).show();
            //call to filter games by WishList
            //Log.d(TAG, filterGamesByWishList().toString());
            updateSpinnerTwo(filterGamesByWishList());
        }

    }

    public List<String> filterGamesByNoPlayers() {

        List<DGame> gameList = DApp.getUserGameList().getGameList();
        List<Integer> gamesPlayers = new ArrayList<>();
        gamesPlayers.add(0);
        for (DGame game : gameList) {
            Integer min = 0;
            Integer max = 0;
            if (game.getMinPlayers() > 0) {
                min = game.getMinPlayers();
            }
            if (game.getMaxPlayers() > 0) {
                max = game.getMaxPlayers();
            }
            for (Integer i = min; i <= max; i++) {
                gamesPlayers.add(i);
            }
        }

        //Log.d(TAG, gamesPlayers.toString());

        //Make gamesPlayers Unique
        List<Integer> gamesPlayersWithoutDuplicates = new ArrayList<>(
                new HashSet<>(gamesPlayers));

        List<String> gamesPlayersWithoutDuplicatesString = new ArrayList<>();

        for (Integer i: gamesPlayersWithoutDuplicates) {
            if (i == 1) {
                gamesPlayersWithoutDuplicatesString.add(i.toString() + " Player");
            }else{
                gamesPlayersWithoutDuplicatesString.add(i.toString()+" Players");
            }
        }

        //this is the final results that should be shown in the second spinner
        //Log.d(TAG, gamesPlayersWithoutDuplicates.toString());
        return gamesPlayersWithoutDuplicatesString;
    }

    public List<String> filterGamesByTime() {
        List<DGame> gameList = DApp.getUserGameList().getGameList();
        List<Integer> gamesTime = new ArrayList<>();
        gamesTime.add(0);
        for (DGame game : gameList) {
            Integer min = 0;
            Integer max = 0;
            if (game.getMinPlayTime() > 0) {
                min = game.getMinPlayTime();
            }
            if (game.getMaxPlayTime() > 0) {
                max = game.getMaxPlayTime();
            }
            for (Integer i = min; i <= max; i++) {
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
            if (i > 14) {
                if (i % 15 == 0) {
                    gamesTimeOptions.add(i);
                }
            }else{
                gamesTimeOptions.add(i);
            }
        }

        List<String> gamesTimeOptionsString = new ArrayList<>();

        for (Integer i: gamesTimeOptions){
            if (i == 1){
                gamesTimeOptionsString.add(i.toString()+" Minute");
            }else{
                gamesTimeOptionsString.add(i.toString()+" Minutes");
            }
        }

        //this is the final results that should be shown in the second spinner
        //Log.d(TAG, gamesTimeOptions.toString());
        return gamesTimeOptionsString;
    }

    public List<String> filterGamesByWishList(){
        List<String> wishListOptions = new ArrayList<>();
        wishListOptions.add("Select option");
        wishListOptions.add("Only Wish List");
        wishListOptions.add("All Except Wish List");

        //this is the final results that should be shown in the second spinner
        //Log.d(TAG, wishListOptions.toString());
        return wishListOptions;
    }

    public List<String> filterGamesByLocation(){
        List<DGame> gameList = DApp.getUserGameList().getGameList();
        List<String> listWithDuplicates =  new ArrayList<>();
        //Log.d(TAG,listWithDuplicates.toString());
        listWithDuplicates.add("Select option");
        listWithDuplicates.add("Wish List");
        for (DGame game: gameList){
            if(game.getLocation().trim().length()>0) {
                listWithDuplicates.add(game.getLocation());
            }
        }

        //this is the final results that should be shown in the second spinner
        //Log.d(TAG,listWithoutDuplicates.toString());
        return listWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Class dedicated to listening to the user's selection on the first (category) spinner.
     */
    public class SpinnerInteractionListener implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

        boolean userSelect = false;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            userSelect = true;
            return false;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (userSelect) {
                String text = parent.getItemAtPosition(pos).toString();
                resetGameList();
                filterGames(text);
                DFilter.setCategory(text);
                userSelect = false;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast toast = Toast.makeText(parent.getContext(), "Select a filter", Toast.LENGTH_SHORT);
        }

    }

    /**
     * Class dedicated to listening to the user's selection on the second (options) spinner.
     */
    public class SpinnerTwoInteractionListener implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

        boolean userSelect = false;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            userSelect = true;
            return false;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (userSelect) {
                String text = parent.getItemAtPosition(pos).toString();
                showGames(text);
                DFilter.setSelection(text);
                userSelect = false;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast toast = Toast.makeText(parent.getContext(), "Select a filter", Toast.LENGTH_SHORT);
        }

    }

}