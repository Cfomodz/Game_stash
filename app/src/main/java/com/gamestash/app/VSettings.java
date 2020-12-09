package com.gamestash.app;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

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

        List<DGame> gameList = DApp.getUserGameList().getGameList();
        List<String> listOfGames =  new ArrayList<>();
        for (DGame game: gameList){
            listOfGames.add(game.getGameName());
        }

        Log.d(TAG,listOfGames.toString());

        String subject = "My Game Wish List";
        String body = listOfGames.toString();

        EditText email = findViewById(R.id.etTo);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        //emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text
        emailIntent.setType("message/rfc822");
        //emailIntent.setData(Uri.parse("mailto:"));
        if(emailIntent.resolveActivity(getPackageManager()) != null){
            startActivity(emailIntent);
        }else{
            Toast.makeText(VSettings.this,"There is no email application installed", Toast.LENGTH_SHORT).show();
        }
    }
}