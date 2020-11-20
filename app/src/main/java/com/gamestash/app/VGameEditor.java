package com.gamestash.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gamestash.app.R;

public class VGameEditor extends AppCompatActivity {
    private static final String TAG = VGameEditor.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_editor);
    }
}