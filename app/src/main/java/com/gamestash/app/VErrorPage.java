package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VErrorPage extends AppCompatActivity {
    private static final String TAG = VErrorPage.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_page);
    }

    public void onClickGoToAddGameManually(View view){
        Intent intent = new Intent(this, VGameEditor.class);
        startActivity(intent);
    }
}