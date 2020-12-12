package com.gamestash.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

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
        String htmlOpener = "<!DOCTYPE html><html><body>";
        String htmlHeader = "<h2>Game Wish List</h2>";

        outputHTML = outputHTML.concat(htmlOpener);
        outputHTML = outputHTML.concat(htmlHeader);

        Log.d(TAG,outputHTML);
        String plainText = "";

        for (DGame game: gameList){
            if (game.getLocation().equals("Wish List")) {
                String htmlTableOpen = "<table style=\"width:100%\"";

                String imageURL = game.getImageURL();
                String gameName = game.getGameName();

                String publisher = game.getPublisher().getName();

                String minPlayers = game.getMinPlayers().toString();
                String maxPlayers = game.getMaxPlayers().toString();

                String minAge = game.getMinAge().toString();
                minAge = minAge.concat("+");

                String description = game.getDescription();

                String htmlTableHeading = "<tr><th><img src=\"" + imageURL + "\"></th><th>" + gameName + "</th>";
                String htmlTableRow = "<tr><td>Publisher: " + publisher + "</td></tr>";
                String htmlTableRow2 = "<tr><td>Players: " + minPlayers + " - " + maxPlayers + "</td><td>Age: " + minAge + "</td></tr>";
                String htmlTableRow3 = "<tr><td>" + description + "</td></tr>";
                String htmlTableClose = "</table>";

                String gameString = htmlTableOpen + htmlTableHeading + htmlTableRow + htmlTableRow2 + htmlTableRow3 + htmlTableClose;

                outputHTML = outputHTML.concat(gameString);

                plainText = plainText.concat(gameName + " by " + publisher + ", which is a " + minPlayers + " to " + maxPlayers + " player game. The minimum recommended age is " + minAge + ".");
                plainText = plainText.concat(System.lineSeparator());
                plainText = plainText.concat(System.lineSeparator());
            }
        }

        String htmlClose = "</body></html>";
        outputHTML = outputHTML.concat(htmlClose);


        Log.d(TAG,outputHTML);

        String subject = "My Game Wish List";
        //String body = "Here is my Wish List of games!";

        EditText email = findViewById(R.id.dt_app_tools_email_address);

        //linebreak = "\\n"

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //emailIntent.putExtra(Intent.EXTRA_STREAM,outputHTML);
        emailIntent.putExtra(Intent.EXTRA_TEXT, plainText);
        emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, outputHTML);
        //emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(outputHTML));
        emailIntent.setType("text/html");
        //emailIntent.setData(Uri.parse("mailto:"));
        if(emailIntent.resolveActivity(getPackageManager()) != null){
            startActivity(emailIntent);
        }else{
            Toast.makeText(VAppTools.this,"There is no email application installed", Toast.LENGTH_SHORT).show();
        }

    }
}