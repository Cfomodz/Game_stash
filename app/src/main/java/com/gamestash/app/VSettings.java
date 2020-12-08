package com.gamestash.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

        //String email = "David.Ashby.LDS@gmail.com";
        String subject = "Test Subject";
        String body = "Here are some games";

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

        /* TODO

        1. Get wish list object
            ->  from DApp (just a guess)
        2. For each game object, parse it out into a text string
            Game Image URL in <img src = URL> tag and Game Name

         */

    }
}