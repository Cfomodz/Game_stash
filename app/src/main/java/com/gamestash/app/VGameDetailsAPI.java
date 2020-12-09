package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VGameDetailsAPI extends AppCompatActivity {
    private static final String TAG = VGameDetailsAPI.class.getSimpleName();

    private PGameDetailsAPI presenter = new PGameDetailsAPI(this);
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details_api);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        presenter.setupPresenter();

    }

    public void onclickSave(View view) {
        // Trigger PGameDetailsAPI method to save...
        presenter.saveGameInUserList();
    }

    public int getPosition() {return position;}

}