package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VGameDetailsAPI extends AppCompatActivity {
    private static final String TAG = VGameDetailsAPI.class.getSimpleName();

    private ISave presenter = new PAPIGameDetails(this);
    private DGame game;

    static class ViewHolder {
        ImageView gameImage;
        TextView gameName;
        TextView publisher;
        TextView players;
        TextView playTime;
        TextView minAge;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details_api);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        this.game = DApp.getApiGameList().getGameList().get(position);

        //move to presenter...
        String gameImage = this.game.getThumbURL();
        String gameName = this.game.getGameName();
        String publisher = "Publisher: " + this.game.getPublisher().getName();
        String players = "Players: " + this.game.getMinPlayers() + " - " + this.game.getMaxPlayers();
        String playTime = "Playtime: " + this.game.getMinPlayTime() + " - " + this.game.getMaxPlayTime() + " min";
        String minAge = "Age: " + this.game.getMinAge() + "+";

        ViewHolder holder = new ViewHolder();
        holder.gameImage = this.findViewById(R.id.tv_api_details_game_image);
        holder.gameName = this.findViewById(R.id.tv_api_details_game_name);
        holder.publisher = this.findViewById(R.id.tv_api_details_publisher);
        holder.players = this.findViewById(R.id.tv_api_details_max_players);
        holder.playTime = this.findViewById(R.id.tv_api_details_max_play_time);
        holder.minAge = this.findViewById(R.id.tv_api_details_min_age);

        Picasso.get()
                .load(gameImage)
                .resize(200, 200)
                .centerInside()
                .noFade()
                .into(holder.gameImage);
        holder.gameName.setText(gameName);
        holder.publisher.setText(publisher);
        holder.players.setText(players);
        holder.playTime.setText(playTime);
        holder.minAge.setText(minAge);


        //use game to fill out display...
        Log.d(TAG, game.getGameName());


    }

    private void initList() {
        mLocationList = new ArrayList<>();

        mLocationList.add(new LocationItem("Shelf", "1"));
        mLocationList.add(new LocationItem("Storage", "2"));
        mLocationList.add(new LocationItem("Under Your Bed", "3"));
        mLocationList.add(new LocationItem("Shelf2", "3"));
    }


    public void onclickSave(View view) {

        // Trigger PAPIGameDetails method to save...
        presenter.saveGameInUserList();
    }

    public DGame getGame() {
        return game;
    }

    //Spinner
    private ArrayList<LocationItem> mLocationList;
    private LocationAdapter mAdapter;
}