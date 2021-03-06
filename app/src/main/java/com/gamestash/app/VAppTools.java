package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import java.util.List;

/**
 * The VAppTools page allows users to both share the items on their Wish List
 * via email, as well as reset the locations list if they stop storing games
 * in any location and no longer want it to show up as a suggestion for a
 * possible location while adding a game.
 */

public class VAppTools extends AppCompatActivity {
    private static final String TAG = VAppTools.class.getSimpleName();

    private IProcess presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_tools);
        presenter = new PAppTools(this);
    }

    public void onClickResetLocationList(View view) {
        presenter.processChanges();
    }


    public void onClickShareWishList(View view) {

        List<DGame> gameList = DApp.getUserGameList().getGameList();
        String outputHTML = "";

        Log.d(TAG,outputHTML);
        String plainText = "";

        for (DGame game: gameList){
            if (game.getLocation().equals("Wish List")) {

                String gameName = game.getGameName();

                String publisher = game.getPublisher().getName();

                String minPlayers = game.getMinPlayers().toString();
                String maxPlayers = game.getMaxPlayers().toString();

                String minAge = game.getMinAge().toString();
                minAge = minAge.concat("+");

                String htmlTableRow = "Publisher: " + publisher;
                String htmlTableRow2 = "Players: " + minPlayers + " - " + maxPlayers;
                String htmlTableRow3 = "Recommended Age: " + minAge;

                String gameString = gameName + "\n\r\n\r" + htmlTableRow + "\n\r\n\r" + htmlTableRow2 + "\n\r\n\r" + htmlTableRow3;

                outputHTML = outputHTML.concat(gameString);

                plainText = plainText.concat(gameName + " by " + publisher + ", which is a " + minPlayers + " to " + maxPlayers + " player game. The recommended age is " + minAge + ".");
                plainText = plainText.concat(System.lineSeparator());
                plainText = plainText.concat(System.lineSeparator());
            }
        }


        //Log.d(TAG,outputHTML);

        String subject = "My Game Wish List";
        //String body = "Here is my Wish List of games!";

        EditText email = findViewById(R.id.dt_app_tools_email_address);

        //linebreak = "\\n"

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //emailIntent.putExtra(Intent.EXTRA_STREAM,outputHTML);
        emailIntent.putExtra(Intent.EXTRA_TEXT, plainText);
        //emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, outputHTML);
        //emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(outputHTML, HtmlCompat.FROM_HTML_MODE_LEGACY));
        //emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, Html.fromHtml(outputHTML, HtmlCompat.FROM_HTML_MODE_LEGACY));
        emailIntent.setType("text/html");
        //emailIntent.setData(Uri.parse("mailto:"));
        if(emailIntent.resolveActivity(getPackageManager()) != null){
            startActivity(emailIntent);
        }else{
            Toast.makeText(VAppTools.this,"There is no email application installed", Toast.LENGTH_SHORT).show();
        }

    }
}