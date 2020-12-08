package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class VGameDetailsUser extends AppCompatActivity {
    private static final String TAG = VGameDetailsUser.class.getSimpleName();

    private PGameDetailsUser presenter = new PGameDetailsUser(this);
    private int position;

    private DGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details_user);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        presenter.setupPresenter();
    }

    public void onclickEdit(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("goto", "gameDetails");
        Intent intent = new Intent(this, VGameEditor.class);
        intent.putExtra("position", position);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public int getPosition() {
        return position;
    }
}