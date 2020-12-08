package com.gamestash.app;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.List;

public class PGameEditor implements IPresent, IProcess, ISave, IDropDown, View.OnTouchListener, AdapterView.OnItemClickListener {

    // Member variables.
    private static final String TAG = PGameEditor.class.getSimpleName();
    private Context mContext;
    private WeakReference<VGameEditor> masterRef;
    private DGame saveGame;
    private DGame pulledGame;
    private List<String> locationList;
    private ListPopupWindow lpw;
    private ViewHolder holder = new ViewHolder();
    private int position;

    public PGameEditor(Context context, VGameEditor activity) {
        this.mContext = context;
        this.masterRef = new WeakReference<>(activity);
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
    public void setupPresenter() {

        if(masterRef.get() != null) {
            AppCompatActivity master = masterRef.get();
            position = masterRef.get().getPosition();

            holder.favorite = master.findViewById(R.id.switch_editor_favorite);
            holder.expansion = master.findViewById(R.id.switch_editor_expansion);
            holder.gameName = master.findViewById(R.id.et_editor_game_name);
            holder.publisher = master.findViewById(R.id.et_editor_publisher);
            holder.minPlayers = master.findViewById(R.id.et_editor_min_players);
            holder.maxPlayers = master.findViewById(R.id.et_editor_max_players);
            holder.minPlayTime = master.findViewById(R.id.et_editor_min_play_time);
            holder.maxPlayTime = master.findViewById(R.id.et_editor_max_play_time);
            holder.minAge = master.findViewById(R.id.et_editor_min_age);
            holder.location = master.findViewById(R.id.et_editor_location);
            // holder.location value is set by on item click;
            // holder.location list is set by:
            setDropDown();

            locationList = DApp.getUserLocationList().getLocationList();

            if(masterRef.get() != null) {
                master = masterRef.get();
                lpw = new ListPopupWindow(master);
                lpw.setAdapter(new ArrayAdapter<String>(master,
                        android.R.layout.simple_list_item_1, locationList));
                lpw.setAnchorView(holder.location);
                lpw.setModal(true);
                lpw.setOnItemClickListener(this);
            }

            // Set up the info for the fields...
            if(position > -1) {
                pulledGame = DApp.getUserGameList().getGameList().get(position);
                Log.d(TAG, pulledGame.getVisibleGameName());

                if(pulledGame.getFavorite()){
                    holder.favorite.setChecked(true);
                } else {
                    holder.favorite.setChecked(false);
                }

                if(pulledGame.getExpansion()){
                    holder.expansion.setChecked(true);
                } else {
                    holder.expansion.setChecked(false);
                }

                holder.gameName.setText(pulledGame.getVisibleGameName());
                holder.gameName.setHint(pulledGame.getVisibleGameName());

                holder.publisher.setText(pulledGame.getVisiblePublisher().getName());
                holder.publisher.setHint(pulledGame.getVisiblePublisher().getName());

                holder.minPlayers.setText(pulledGame.getVisibleMinPlayers().toString());
                holder.minPlayers.setHint(pulledGame.getVisibleMinPlayers().toString());

                holder.maxPlayers.setText(pulledGame.getVisibleMaxPlayers().toString());
                holder.maxPlayers.setHint(pulledGame.getVisibleMaxPlayers().toString());

                holder.minPlayTime.setText(pulledGame.getVisibleMinPlayTime().toString());
                holder.minPlayTime.setHint(pulledGame.getVisibleMinPlayTime().toString());

                holder.maxPlayTime.setText(pulledGame.getVisibleMaxPlayTime().toString());
                holder.maxPlayTime.setHint(pulledGame.getVisibleMaxPlayTime().toString());

                holder.minAge.setText(pulledGame.getVisibleMinAge().toString());
                holder.minAge.setHint(pulledGame.getVisibleMinAge().toString());

                holder.location.setText(pulledGame.getLocation());
            }
        }

    }

    public void setDropDown() {
        holder.location.setOnTouchListener(this);

        locationList = DApp.getUserLocationList().getLocationList();

        if(masterRef.get() != null) {
            AppCompatActivity master = masterRef.get();
            lpw = new ListPopupWindow(master);
            lpw.setAdapter(new ArrayAdapter<String>(master,
                    android.R.layout.simple_list_item_1, locationList));
            lpw.setAnchorView(holder.location);
            lpw.setModal(true);
            lpw.setOnItemClickListener(this);
        }
    }

    @Override
    public void processChanges() {
        //Validate
        if (this.validateGameData(holder)) {
            //IF VALID GAME DATA... THEN CREATE GAME OBJ.

            if(position > -1) {
                //do stuff for game that is being edited.
                //pull.set...field...
                //if value equals game value... do not set edited field...
            } else { // This is a manual entry game...

                this.saveGame = new DGame();
                this.saveGame.setIsUserCreated(true);
                this.saveGame.setFavorite(holder.favorite.isChecked());
                this.saveGame.setExpansion(holder.expansion.isChecked());
                this.saveGame.setEditedGameName(holder.gameName.getText().toString().trim());
                this.saveGame.setEditedMinPlayers(Integer.parseInt(holder.minPlayers.getText().toString().trim()));
                this.saveGame.setEditedMaxPlayers(Integer.parseInt(holder.maxPlayers.getText().toString().trim()));
                this.saveGame.setEditedMinPlayTime(Integer.parseInt(holder.minPlayTime.getText().toString().trim()));
                this.saveGame.setEditedMaxPlayTime(Integer.parseInt(holder.maxPlayTime.getText().toString().trim()));
                this.saveGame.setEditedMinAge(Integer.parseInt(holder.minAge.getText().toString().trim()));

                if (holder.gameName.getText().toString().trim().length() > 0) {
                    saveGame.setLocation(holder.gameName.getText().toString().trim());
                }


                DPublisher publisher = new DPublisher();
                publisher.setName(holder.publisher.getText().toString().trim());
                saveGame.setEditedPublisher(publisher);

                //SAVE GAME OBJECT
                this.saveGameInUserList();

            }

        } else {
            //do stuff to indicate the game is wrong...
        }

        //If successful then...??? Party!
        //Intent intent = new Intent(this, VGameListUser.class);
        //startActivity(intent);
    }

    // Validate that required fields are filled in and have valid data.
    private Boolean validateGameData(ViewHolder holder) {
        Boolean validation = true;
        // Make sure they entered a game name.
        if(holder.gameName.getText().toString().trim().length() == 0) {
            holder.gameName.setError("Enter game name");
            validation = false;
        }
        // Make sure they entered a publisher.
        if(holder.publisher.getText().toString().trim().length() == 0) {
            holder.publisher.setError("Enter a publisher");
            validation = false;
        }
        // Make sure they entered minimum player data.
        if(holder.minPlayers.getText().toString().trim().length() == 0) {
            holder.minPlayers.setError("Enter a #");
            validation = false;
        }

        // Make sure they entered maximum player value.
        if(holder.maxPlayers.getText().toString().trim().length() == 0) {
            holder.maxPlayers.setError("Enter a #");
            validation = false;
        }

        if(holder.minPlayers.getText().toString().trim().length() > 0 && holder.maxPlayers.getText().toString().trim().length() > 0) {
            if(Integer.parseInt(holder.minPlayers.getText().toString().trim()) > Integer.parseInt(holder.maxPlayers.getText().toString().trim())) {
                holder.minPlayers.setError("Must Be Less Than Or Equal to Max Players.");
                validation = false;
            }

            if(Integer.parseInt(holder.maxPlayers.getText().toString().trim()) < Integer.parseInt(holder.minPlayers.getText().toString().trim())) {
                holder.maxPlayers.setError("Must Be Greater Than Or Equal to Min Players.");
                validation = false;
            }
        }


        // Make sure they entered minimum play time value.
        if(holder.minPlayTime.getText().toString().trim().length() == 0) {
            holder.minPlayTime.setError("Enter a #");
            validation = false;
        }

        // Make sure they entered maximum play time value.
        if(holder.maxPlayTime.getText().toString().trim().length() == 0) {
            holder.maxPlayTime.setError("Enter a #");
            validation = false;
        }

        if(holder.minPlayTime.getText().toString().trim().length() > 0 && holder.maxPlayTime.getText().toString().trim().length() > 0){
            if(Integer.parseInt(holder.minPlayTime.getText().toString().trim()) > Integer.parseInt(holder.maxPlayTime.getText().toString().trim())) {
                holder.minPlayTime.setError("Must Be Less Than Or Equal to Max Play Time");
                validation = false;
            }

            // Make sure the entered maximum is not less than the minimum play time.
            if(Integer.parseInt(holder.maxPlayTime.getText().toString().trim()) < Integer.parseInt(holder.minPlayTime.getText().toString().trim())) {
                holder.maxPlayTime.setError("Must Be Greater Than Or Equal to Min Play Time");
                validation = false;
            }
        }


        // Make sure they entered a minimum age value.
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
        TSaveGame saveGame = new TSaveGame(this.masterRef.get(), this, this, this.saveGame);
        Thread thread = new Thread(saveGame);
        thread.start();
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