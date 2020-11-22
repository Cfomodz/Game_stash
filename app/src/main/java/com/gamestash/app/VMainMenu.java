package com.gamestash.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class VMainMenu extends AppCompatActivity {
    private static final String TAG = VMainMenu.class.getSimpleName();

    private static final String TAG_B = TAG + "_B:";
    private static final String TAG_T = TAG + "_T:";
    private static final String TAG_M = TAG + "_M:";
    private static final String TAG_A = TAG + "_A:";

    private IProcess presenter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        presenter = new PMainMenu(this);

        //Random starting image... just add R.darawable. image name to array to expand.
        int[] mainImgs =  {R.drawable.main_menu_img_00,
                R.drawable.main_menu_img_01,
                R.drawable.main_menu_img_02,
                R.drawable.main_menu_img_03,
                R.drawable.main_menu_img_04};
        ImageView imageView = findViewById(R.id.iv_main_menu);
        imageView.setImageResource(mainImgs[new Random().nextInt(mainImgs.length)]);

        MDataHolder.setUserJSONFile(new File(this.getFilesDir() + "/usergamelist.json"));

        this.presenter.processChanges();

        // TEST AREA START // // TEST AREA START // // TEST AREA START //
        // TEST AREA START // // TEST AREA START // // TEST AREA START //
        // TEST AREA START // // TEST AREA START // // TEST AREA START //

        // Use your function below to test stuff you may need to test...
        testStuff4Lee();
        testStuff4Daren();
        testStuff4David();
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void testStuff4Lee() {
        //TEST AREA for Lee
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