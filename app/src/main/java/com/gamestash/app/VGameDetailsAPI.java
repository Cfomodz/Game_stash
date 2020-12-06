package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VGameDetailsAPI extends AppCompatActivity {
    private static final String TAG = VGameDetailsAPI.class.getSimpleName();

    private PAPIGameDetails presenter = new PAPIGameDetails(this);
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details_api);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        presenter.processChanges();

    }

    public void onclickSave(View view) {
        // Trigger PAPIGameDetails method to save...
        presenter.saveGameInUserList();
    }

    public int getPosition() {
        return position;
    }



}