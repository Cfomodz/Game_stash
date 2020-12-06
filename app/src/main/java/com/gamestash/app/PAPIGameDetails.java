package com.gamestash.app;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

public class PAPIGameDetails implements IProcess, ISave, View.OnTouchListener, AdapterView.OnItemClickListener {
    private static final String TAG = PAPIGameDetails.class.getSimpleName();

    private WeakReference<VGameDetailsAPI> masterRef;

    private DGame game;
    private List<String> locationList;
    private ListPopupWindow lpw;
    private ViewHolder holder = new ViewHolder();

    public PAPIGameDetails(VGameDetailsAPI activity){
        this.masterRef = new WeakReference<>(activity);
    }

    private class ViewHolder {
        ImageView gameImage;
        TextView gameName;
        TextView publisher;
        TextView players;
        TextView playTime;
        TextView minAge;
        EditText location;
    }

    @Override
    public void processChanges() {
        if(masterRef.get() != null) {
            VGameDetailsAPI master = masterRef.get();
            this.game = DApp.getApiGameList().getGameList().get(master.getPosition());


            //move to presenter...
            String gameImage = this.game.getThumbURL();
            String gameName = this.game.getGameName();
            String publisher = "Publisher: " + this.game.getPublisher().getName();
            String players = "Players: " + this.game.getMinPlayers() + " - " + this.game.getMaxPlayers();
            String playTime = "Playtime: " + this.game.getMinPlayTime() + " - " + this.game.getMaxPlayTime() + " min";
            String minAge = "Age: " + this.game.getMinAge() + "+";
            // String location = "something";

            holder.gameImage = master.findViewById(R.id.tv_api_details_game_image);
            holder.gameName = master.findViewById(R.id.tv_api_details_game_name);
            holder.publisher = master.findViewById(R.id.tv_api_details_publisher);
            holder.players = master.findViewById(R.id.tv_api_details_max_players);
            holder.playTime = master.findViewById(R.id.tv_api_details_max_play_time);
            holder.minAge = master.findViewById(R.id.tv_api_details_min_age);
            holder.location = master.findViewById(R.id.et_api_details_location);

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
            // holder.location is set by on item click;
        }


        //use game to fill out display...
        Log.d(TAG, game.getGameName());


        // LOCATION REPLACEMENT
        holder.location.setOnTouchListener(this);

        //TEST VALUES
        DApp.addUserLocationList("Box11");
        DApp.addUserLocationList("Box12");
        DApp.addUserLocationList("Box01");
        DApp.addUserLocationList("Box02");
        DApp.addUserLocationList("Box03");
        DApp.addUserLocationList("Box04");
        DApp.addUserLocationList("Box05");
        DApp.addUserLocationList("Box06");
        DApp.addUserLocationList("Box07");
        DApp.addUserLocationList("Box08");
        DApp.addUserLocationList("Box09");
        DApp.addUserLocationList("Box10");
        DApp.addUserLocationList("Box13");
        DApp.addUserLocationList("Box14");
        DApp.addUserLocationList("Box15");
        DApp.addUserLocationList("Box16");
        DApp.addUserLocationList("Box17");
        DApp.addUserLocationList("Box18");
        //TEST END

        locationList = DApp.getUserLocationList().getLocationList();

        if(masterRef.get() != null) {
            VGameDetailsAPI master = masterRef.get();
            lpw = new ListPopupWindow(master);
            lpw.setAdapter(new ArrayAdapter<String>(master,
                    android.R.layout.simple_list_item_1, locationList));
            lpw.setAnchorView(holder.location);
            lpw.setModal(true);
            lpw.setOnItemClickListener(this);
        }
    }



    @Override
    public void saveGameInUserList() {
        if (this.masterRef.get() !=  null){
            // Save the location value to the DGame obj
            if (holder.location.getText().toString().trim().length() > 0) {
                this.game.setLocation(holder.location.getText().toString());
            }
            TSaveGame saveGame = new TSaveGame(this.masterRef.get(), this, this.game);
            Thread thread = new Thread(saveGame);
            thread.start();
        }
    }

    //LOCATION
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_RIGHT = 2;

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getX() >= (v.getWidth() - ((EditText) v)
                    .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                lpw.show();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = locationList.get(position);
        holder.location.setText(item);
        lpw.dismiss();
    }

}
