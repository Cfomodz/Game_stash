package com.gamestash.app;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class PGameEditor implements IProcess, ISave {

    // Member variables.
    private static final String TAG = PGameEditor.class.getSimpleName();
    private Context mContext;
    private WeakReference<Activity> activityRef;

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
        Spinner location;
    }

    public PGameEditor(Context context, Activity activity) {
        this.mContext = context;
        this.activityRef = new WeakReference<Activity>(activity);
    }

    //EditText gameName = (EditText) this.activity.findViewById(R.id.et_editor_game_name);
    //        return gameName.getText().toString();



    @Override
    public void processChanges() {
        //Add Layout to Holder
        ViewHolder holder = new PGameEditor.ViewHolder();
        if(activityRef.get() != null) {
            holder.favorite = this.activityRef.get().findViewById(R.id.switch_editor_favorite);
            holder.expansion = this.activityRef.get().findViewById(R.id.switch_editor_expansion);
            holder.gameName = this.activityRef.get().findViewById(R.id.et_editor_game_name);
            holder.publisher = this.activityRef.get().findViewById(R.id.et_editor_publisher);
            holder.minPlayers = this.activityRef.get().findViewById(R.id.et_editor_min_players);
            holder.maxPlayers = this.activityRef.get().findViewById(R.id.et_editor_max_players);
            holder.minPlayTime = this.activityRef.get().findViewById(R.id.et_editor_min_play_time);
            holder.maxPlayTime = this.activityRef.get().findViewById(R.id.et_editor_max_play_time);
            holder.minAge = this.activityRef.get().findViewById(R.id.et_editor_min_age);
            holder.location = this.activityRef.get().findViewById(R.id.sp_editor_location);
        }
        //Validate
        if (this.validateGameData(holder)) {
            //actually add the game to our JSON via game object

            DGame game = new DGame();
            game.setEditedGameName(holder.gameName.getText().toString());
            game.setEditedMinPlayers(Integer.parseInt(holder.minPlayers.getText().toString()));
            game.setEditedMaxPlayers(Integer.parseInt(holder.maxPlayers.getText().toString()));
            game.setEditedMinPlayTime(Integer.parseInt(holder.minPlayTime.getText().toString()));
            game.setEditedMaxPlayTime(Integer.parseInt(holder.maxPlayTime.getText().toString()));
            game.setEditedMinAge(Integer.parseInt(holder.minAge.getText().toString()));

            DPublisher publisher = new DPublisher();
            publisher.setName(holder.publisher.getText().toString());
            game.setEditedPublisher(publisher);

            Log.d(TAG, game.getEditedGameName());

        } else {
            //do stuff to indicate the game is wrong...

        }

        //If successful then...??? Party!
        //Intent intent = new Intent(this, VGameListUser.class);
        //startActivity(intent);
    }

    @Override
    public void saveGameInUserList() {

    }

    //move to presenter...
    private Boolean validateGameData(ViewHolder holder) {
        Boolean validation = true;
        if(holder.gameName.getText().toString().length() == 0) {
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
}