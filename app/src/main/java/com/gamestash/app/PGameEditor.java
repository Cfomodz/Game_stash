package com.gamestash.app;

import android.content.Context;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class PGameEditor implements IProcess, ISave {

    // Member variables.
    private static final String TAG = PGameEditor.class.getSimpleName();
    private Context mContext;
    private WeakReference<AppCompatActivity> masterRef;
    private DGame game;

    public PGameEditor(Context context, AppCompatActivity activity) {
        this.mContext = context;
        this.masterRef = new WeakReference<AppCompatActivity>(activity);
    }

    static class ViewHolder {
        Switch favorite;
        Switch expansion;
        EditText gameName;
        EditText publisher;
        EditText minPlayers;
        EditText maxPlayers;
        EditText minPlayTime;
        EditText maxPlayTime;
        EditText minAge;
        EditText location;
    }

    @Override
    public void processChanges() {
        //Add Layout to Holder
        ViewHolder holder = new PGameEditor.ViewHolder();
        if(masterRef.get() != null) {
            holder.favorite = this.masterRef.get().findViewById(R.id.switch_editor_favorite);
            holder.expansion = this.masterRef.get().findViewById(R.id.switch_editor_expansion);
            holder.gameName = this.masterRef.get().findViewById(R.id.et_editor_game_name);
            holder.publisher = this.masterRef.get().findViewById(R.id.et_editor_publisher);
            holder.minPlayers = this.masterRef.get().findViewById(R.id.et_editor_min_players);
            holder.maxPlayers = this.masterRef.get().findViewById(R.id.et_editor_max_players);
            holder.minPlayTime = this.masterRef.get().findViewById(R.id.et_editor_min_play_time);
            holder.maxPlayTime = this.masterRef.get().findViewById(R.id.et_editor_max_play_time);
            holder.minAge = this.masterRef.get().findViewById(R.id.et_editor_min_age);
            holder.location = this.masterRef.get().findViewById(R.id.et_editor_location);
        }
        //Validate
        if (this.validateGameData(holder)) {
            //IF VALID GAME DATA... THEN CREATE GAME OBJ.

            this.game = new DGame();
            game.setIsUserCreated(true);
            game.setFavorite(holder.favorite.isChecked());
            game.setExpansion(holder.expansion.isChecked());
            game.setEditedGameName(holder.gameName.getText().toString().trim());
            game.setEditedMinPlayers(Integer.parseInt(holder.minPlayers.getText().toString().trim()));
            game.setEditedMaxPlayers(Integer.parseInt(holder.maxPlayers.getText().toString().trim()));
            game.setEditedMinPlayTime(Integer.parseInt(holder.minPlayTime.getText().toString().trim()));
            game.setEditedMaxPlayTime(Integer.parseInt(holder.maxPlayTime.getText().toString().trim()));
            game.setEditedMinAge(Integer.parseInt(holder.minAge.getText().toString().trim()));

            DPublisher publisher = new DPublisher();
            publisher.setName(holder.publisher.getText().toString().trim());
            game.setEditedPublisher(publisher);

            /**
            Log.d(TAG, game.getFavorite().toString());
            Log.d(TAG, game.getExpansion().toString());
            Log.d(TAG, game.getEditedGameName());
            Log.d(TAG, game.getEditedMinPlayers().toString());
            Log.d(TAG, game.getEditedMaxPlayers().toString());
            Log.d(TAG, game.getEditedMinPlayTime().toString());
            Log.d(TAG, game.getEditedMaxPlayTime().toString());
            Log.d(TAG, game.getEditedMinAge().toString());
            */

            //SAVE GAME OBJECT
            this.saveGameInUserList();

        } else {
            //do stuff to indicate the game is wrong...

        }

        //If successful then...??? Party!
        //Intent intent = new Intent(this, VGameListUser.class);
        //startActivity(intent);
    }

    private Boolean validateGameData(ViewHolder holder) {
        Boolean validation = true;
        if(holder.gameName.getText().toString().trim().length() == 0) {
            holder.gameName.setError("Enter game name");
            validation = false;
        }
        if(holder.publisher.getText().toString().trim().length() == 0) {
            holder.publisher.setError("Enter a publisher");
            validation = false;
        }
        if(holder.minPlayers.getText().toString().trim().length() == 0) {
            holder.minPlayers.setError("Enter a #");
            validation = false;
        }
        if(holder.maxPlayers.getText().toString().trim().length() == 0) {
            holder.maxPlayers.setError("Enter a #");
            validation = false;
        }
        if(holder.minPlayTime.getText().toString().trim().length() == 0) {
            holder.minPlayTime.setError("Enter a #");
            validation = false;
        }
        if(holder.maxPlayTime.getText().toString().trim().length() == 0) {
            holder.maxPlayTime.setError("Enter a #");
            validation = false;
        }
        if(holder.minAge.getText().toString().trim().length() == 0) {
            holder.minAge.setError("Enter a #");
            validation = false;
        }
        if (validation == false) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void saveGameInUserList() {
        TSaveGame saveGame = new TSaveGame(this.masterRef.get(), this, this.game);
        Thread thread = new Thread(saveGame);
        thread.start();
    }
}