package com.gamestash.app;

import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * <h1>PGameDetailsUser</h1>
 * Presenter for VGameDetailsUser.
 */
public class PGameDetailsUser implements IPresent{
    private static final String TAG = PGameDetailsUser.class.getSimpleName();

    private WeakReference<VGameDetailsUser> masterRef;

    private DGame game;
    private ViewHolder holder = new ViewHolder();

    public PGameDetailsUser(VGameDetailsUser activity){
        this.masterRef = new WeakReference<>(activity);
    }

    /**
     * <h1>ViewHolder</h1>
     * This is to hold all the layout items for VGameDetailsUser/activity_game_details_user.xml.
     */
    private class ViewHolder {
        ImageView gameImage;
        TextView gameName;
        TextView publisher;
        TextView players;
        TextView playTime;
        TextView minAge;
        TextView location;
    }

    /**
     * setupPresenter is setting the ViewHolder variables to the corresponding layout items.
     */
    @Override
    public void setupPresenter(){
        //
        if(masterRef.get() != null){
            VGameDetailsUser master = masterRef.get();
            this.game = DApp.getUserGameList().getGameList().get(master.getPosition());

            String gameImage = this.game.getVisibleThumbURL();
            String gameName = this.game.getVisibleGameName();
            String publisher = "Publisher: " + this.game.getVisiblePublisher().getName();
            String players = "Players: " + this.game.getVisibleMinPlayers() + " - " + this.game.getVisibleMaxPlayers();
            String playTime = "Playtime: " + this.game.getVisibleMinPlayTime() + " - " + this.game.getVisibleMaxPlayTime() + " min";
            String minAge = "Age: " + this.game.getVisibleMinAge() + "+";
            String location;
            if (this.game.getLocation().length() > 0) {
                location = "Location: " + this.game.getLocation();
            } else {
                location = "Location: Undefined";
            }

            holder.gameImage = master.findViewById(R.id.tv_user_details_game_image);
            holder.gameName = master.findViewById(R.id.tv_user_details_game_name);
            holder.publisher = master.findViewById(R.id.tv_user_details_publisher);
            holder.players = master.findViewById(R.id.tv_user_details_players);
            holder.playTime = master.findViewById(R.id.tv_user_details_play_time);
            holder.minAge = master.findViewById(R.id.tv_user_details_min_age);
            holder.location = master.findViewById(R.id.tv_user_details_location);

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
            holder.location.setText(location);


            //use game to fill out display...
            Log.d(TAG, game.getGameName());
        }

    }
}
