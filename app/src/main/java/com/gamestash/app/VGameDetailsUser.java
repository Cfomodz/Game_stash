package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class VGameDetailsUser extends AppCompatActivity {
    private static final String TAG = VGameDetailsUser.class.getSimpleName();

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
        setContentView(R.layout.activity_game_details_user);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        this.game = DApp.getUserGameList().getGameList().get(position);

        //move to presenter...
        String gameImage = this.game.getVisibleThumbURL();
        String gameName = this.game.getVisibleGameName();
        String publisher = "Publisher: " + this.game.getVisiblePublisher().getName();
        String players = "Players: " + this.game.getVisibleMinPlayers() + " - " + this.game.getVisibleMaxPlayers();
        String playTime = "Playtime: " + this.game.getVisibleMinPlayTime() + " - " + this.game.getVisibleMaxPlayTime() + " min";
        String minAge = "Age: " + this.game.getVisibleMinAge() + "+";

        VGameDetailsAPI.ViewHolder holder = new VGameDetailsAPI.ViewHolder();
        holder.gameImage = this.findViewById(R.id.tv_user_details_game_image);
        holder.gameName = this.findViewById(R.id.tv_user_details_game_name);
        holder.publisher = this.findViewById(R.id.tv_user_details_publisher);
        holder.players = this.findViewById(R.id.tv_user_details_players);
        holder.playTime = this.findViewById(R.id.tv_user_details_play_time);
        holder.minAge = this.findViewById(R.id.tv_user_details_min_age);

        if(gameImage.trim().length() > 0) {
            Picasso.get()
                    .load(gameImage)
                    .resize(200, 200)
                    .error(R.drawable.main_menu_img_00)
                    .centerInside()
                    .noFade()
                    .into(holder.gameImage);
        } else {
            Picasso.get()
                    .load(R.drawable.main_menu_img_00)
                    .resize(200, 200)
                    .centerInside()
                    .noFade()
                    .into(holder.gameImage);
        }

        holder.gameName.setText(gameName);
        holder.publisher.setText(publisher);
        holder.players.setText(players);
        holder.playTime.setText(playTime);
        holder.minAge.setText(minAge);


        //use game to fill out display...
        Log.d(TAG, game.getGameName());
    }

    public void onclickEdit(View view) {
        Intent intent = new Intent(this, VGameListUser.class);
        startActivity(intent);
    }

    public void onclickDelete(View view) {
        Intent intent = new Intent(this, VGameListUser.class);
        startActivity(intent);
    }
}