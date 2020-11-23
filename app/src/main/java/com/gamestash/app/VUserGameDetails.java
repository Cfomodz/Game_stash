package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class VUserGameDetails extends AppCompatActivity {
    private static final String TAG = VUserGameDetails.class.getSimpleName();

    private MGame game;

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
        setContentView(R.layout.activity_game_details_user);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        this.game = MDataHolder.getUserGameList().getGameList().get(position);

        //move to presenter...
        String gameImage = this.game.getThumbURL();
        String gameName = this.game.getGameName();
        String publisher = "Publisher: " + this.game.getPublisher().getName();
        String players = "Players: " + this.game.getMinPlayers() + " - " + this.game.getMaxPlayers();
        String playTime = "Playtime: " + this.game.getMinPlayTime() + " - " + this.game.getMaxPlayTime() + " min";
        String minAge = "Age: " + this.game.getMinAge() + "+";

        VAPIGameDetails.ViewHolder holder = new VAPIGameDetails.ViewHolder();
        holder.gameImage = this.findViewById(R.id.tv_user_details_game_image);
        holder.gameName = this.findViewById(R.id.tv_user_details_game_name);
        holder.publisher = this.findViewById(R.id.tv_user_details_publisher);
        holder.players = this.findViewById(R.id.tv_user_details_players);
        holder.playTime = this.findViewById(R.id.tv_user_details_play_time);
        holder.minAge = this.findViewById(R.id.tv_user_details_min_age);

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
}