package com.gamestash.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Random;


/**
 * <h1>Game Stash App</h1>
 * The Game Stash app allows users to query the board game atlas api and store any game to their list.
 * <b>Note:</b> The app was largely developed to give users a convenient way to set a storage location for users
 * with a larger collection. *
 */
public class VMainMenu extends AppCompatActivity {
    private static final String TAG = VMainMenu.class.getSimpleName();

    private static final String TAG_B = TAG + "_B:";
    private static final String TAG_T = TAG + "_T:";
    private static final String TAG_M = TAG + "_M:";
    private static final String TAG_A = TAG + "_A:";

    private IProcess presenter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        presenter = new PMainMenu(this);

        //Random starting image... just add R.drawable. image name to array to expand.
        int[] mainImgs =  {R.drawable.main_menu_img_00,
                R.drawable.main_menu_img_01,
                R.drawable.main_menu_img_02,
                R.drawable.main_menu_img_03,
                R.drawable.main_menu_img_04};
        ImageView imageView = findViewById(R.id.iv_main_menu);
        imageView.setImageResource(mainImgs[new Random().nextInt(mainImgs.length)]);

        String userGameListFileName = DApp.getUserGameListJSONFileName();
        String userLocationListFileName = DApp.getUserLocationListJSONFileName();
        DApp.setUserJSONFile(new File(this.getFilesDir() + "/" + userGameListFileName));
        DApp.setUserLocationListJSONFile(new File(this.getFilesDir() + "/" + userLocationListFileName));

        this.presenter.processChanges();
    }

    /**
     * onclickViewCollection starts the user game list.
     * @param view
     */
    public void onclickViewCollection(View view) {
        Intent intent = new Intent(this, VGameListUser.class);
        startActivity(intent);
    }

    /**
     * onclickAddGameManually starts the game editor.
     * <b>Note:</b> Passes the bundle parm so that the game editor can return to the proper
     * starting parent if the user moves backward. Position is passed as -1 to indicate that
     * no list position is being passed. This also indicates to the editor that it is opening from
     * a non-list. There is a distinction in that the game editor can be opened from to additional
     * locations that would have a value.
     */
    public void onclickAddGameManually(View view) {
        //The two puts cannot be consolidated. We need the integer for position. -1 does imply
        Bundle bundle = new Bundle();
        bundle.putString("goto", "mainMenu");
        Intent intent = new Intent(this, VGameEditor.class);
        intent.putExtra("position", -1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onclickAddGameSearch(View view) {
        Intent intent = new Intent(this, VAddGameAPI.class);
        startActivity(intent);
    }

    public void onclickProfile(View view) {
        Intent intent = new Intent(this, VAppTools. class);
        startActivity(intent);
    }
}