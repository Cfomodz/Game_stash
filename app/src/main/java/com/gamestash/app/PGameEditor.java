package com.gamestash.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;

public class PGameEditor implements IProcess, ISave {

    // Member variables.
    private static final String TAG = PGameEditor.class.getSimpleName();
    private Context mContext;

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

    public PGameEditor(Context context) {
        this.mContext = context;
    }

    @NonNull
    public View getView(View convertView, ViewGroup parent) {
        final View result;
        ViewHolder holder;

        if (convertView == null) {
            holder = new PGameEditor.ViewHolder();
            holder.favorite = convertView.findViewById(R.id.switch_editor_favorite);
            holder.expansion = convertView.findViewById(R.id.switch_editor_expansion);
            holder.gameName = convertView.findViewById(R.id.et_editor_game_name);
            holder.publisher = convertView.findViewById(R.id.et_editor_publisher);
            holder.minPlayers = convertView.findViewById(R.id.et_editor_min_players);
            holder.maxPlayers = convertView.findViewById(R.id.et_editor_max_players);
            holder.minPlayTime = convertView.findViewById(R.id.et_editor_min_play_time);
            holder.maxPlayTime = convertView.findViewById(R.id.et_editor_max_play_time);
            holder.minAge = convertView.findViewById(R.id.et_editor_min_age);
            holder.location = convertView.findViewById(R.id.sp_editor_location);

            result = convertView;
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        // Call validateGameData()

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

        return convertView;
    }

    @Override
    public void processChanges() {
        //Validate
        if (this.validateGameData()) {
            //actually add the game to our JSON via game object

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
    private Boolean validateGameData() {
        //return true
        return false;
    }
}