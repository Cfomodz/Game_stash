package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VSettings extends AppCompatActivity {
    private static final String TAG = VSettings.class.getSimpleName();

    private IProcess presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        presenter = new PSettings(this);
    }

    public void onClickResetLocationList(View view) {
        presenter.processChanges();
    }

    public void onClickShareWishList(View view) {

    }
}