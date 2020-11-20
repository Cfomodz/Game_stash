package com.example.game_stash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class VMainMenu extends AppCompatActivity {
    private static final String TAG = VMainMenu.class.getSimpleName();

    private static final String TAG_B = TAG + "_B:";
    private static final String TAG_T = TAG + "_T:";
    private static final String TAG_M = TAG + "_M:";
    private static final String TAG_A = TAG + "_A:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Random starting image... just add R.darawable. image name to array to expand.
        int[] mainImgs =  {R.drawable.main_menu_img_00,
                R.drawable.main_menu_img_01,
                R.drawable.main_menu_img_02,
                R.drawable.main_menu_img_03,
                R.drawable.main_menu_img_04,
                R.drawable.main_menu_img_05,
                R.drawable.main_menu_img_06,
                R.drawable.main_menu_img_07,
                R.drawable.main_menu_img_10,
                R.drawable.main_menu_img_11,
                R.drawable.main_menu_img_12,
                R.drawable.main_menu_img_13};
        ImageView imageView = (ImageView) findViewById(R.id.iv_main_menu);
        imageView.setImageResource(mainImgs[new Random().nextInt(mainImgs.length)]);

        // TEST AREA START // // TEST AREA START // // TEST AREA START //
        // TEST AREA START // // TEST AREA START // // TEST AREA START //
        // TEST AREA START // // TEST AREA START // // TEST AREA START //

        // Use your function below to test stuff you may need to test...
        testStuff4Lee();
        testStuff4Daren();
        testStuff4David();

       

        System.out.println(TAG);

        testStuff4Megan();
        //  TEST AREA END  // //  TEST AREA END  // //  TEST AREA END  //
        //  TEST AREA END  // //  TEST AREA END  // //  TEST AREA END  //
        //  TEST AREA END  // //  TEST AREA END  // //  TEST AREA END  //

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onclickViewCollection(View view) {
        Intent intent = new Intent(this, VUsersListOfGames.class);
        startActivity(intent);
    }

    public void onclickAddGameManually(View view) {
        Intent intent = new Intent(this, VAddGameManually.class);
        startActivity(intent);
    }

    public void onclickAddGameSearch(View view) {
        Intent intent = new Intent(this, VAddGameSearch.class);
        startActivity(intent);
    }

    public void onclickProfile(View view) {
        Intent intent = new Intent(this, VProfile. class);
        startActivity(intent);
    }

    public void testStuff4Lee() {
        //TEST AREA for Lee

        //SET FILE
        File file = new File(this.getFilesDir() + "/usergamelist.json");

        //FIND FILE AND PULL JSON STRING
        String jsonString;
        try {
            InputStream is = new FileInputStream(file);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            jsonString = null;
            //return null;
        }

        if(jsonString != null) {
            Log.d(TAG, jsonString);
        } else {
            Log.d(TAG, "file not found.");
        }
        //return jsonString

        //PROCESS JSON STRING
        //Not placed in the above string because that should be in its own function... While this should be separate...
        if(jsonString != null) {
            MGSONParser gsonParse = new MGSONParser(new PMainMenu(this), MDataHolder::setUserGameList, MDataHolder::getUserGameList, jsonString);
            //Thread thread = new Thread(gsonParse);
            //thread.start();
            gsonParse.run();
        }

    }

    public void testStuff4Daren() {
        //TEST AREA for Daren
    }

    public void testStuff4David() {
        //TEST AREA for David
        
    }

    public void testStuff4Megan() {
        //TEST AREA for Megan
    }
}