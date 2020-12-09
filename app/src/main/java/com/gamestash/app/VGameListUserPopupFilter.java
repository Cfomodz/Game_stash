package com.gamestash.app;

import android.util.Log;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class VGameListUserPopupFilter extends AppCompatActivity {

    private static final String TAG = VGameListAPI.class.getSimpleName();

    Spinner spinnerOne;
    Spinner spinnerTwo;
    String[] categories = {"No Of Players", "Play Time", "Locations", "Wish List"};


    public void onFirstSpinnerSelectListener(){
        /*

        Filter for:
        Wish List (should by default be off unless turned on in filter) ->
        Time (pull each game's min and max, add each integer between them (inclusive), make list unique, keep each value IFF value%5=0)
        Num players (pull each game's min and max, add each integer between them (inclusive), make list unique)
        Location (list of locations)

         */
    }

    public void onSecondSpinnerSelectListener(){

    }

    public void filterGames(){

        //call to filter games by no of players
        filterGamesByNoPlayers();

        //call to filter games by amount of time
        filterGamesByTime();

        //call to filter games by WishList
        filterGamesByWishList();

        //call to filter games by Location
        filterGamesByLocation();
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

        Log.d(TAG, gamesPlayers.toString());

        //Make gamesPlayers Unique
        List<Integer> gamesPlayersWithoutDuplicates = new ArrayList<>(
                new HashSet<>(gamesPlayers));

        //this is the final results that should be shown in the second spinner
        Log.d(TAG, gamesPlayersWithoutDuplicates.toString());
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

        Log.d(TAG, gamesTime.toString());

        //Make gamesTime Unique
        List<Integer> gamesTimeWithoutDuplicates = new ArrayList<>(
                new HashSet<>(gamesTime));

        Log.d(TAG, gamesTimeWithoutDuplicates.toString());

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
        Log.d(TAG, gamesTimeOptions.toString());
        return gamesTimeOptions;
    }

    public List<String> filterGamesByWishList(){
        List<String> wishListOptions = new ArrayList<>();
        wishListOptions.add("Show");
        wishListOptions.add("Hide");

        //this is the final results that should be shown in the second spinner
        Log.d(TAG, wishListOptions.toString());
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
        Log.d(TAG,listWithoutDuplicates.toString());
        return listWithoutDuplicates;
    }

}
