package com.example.game_stash;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

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

    public void onclickAddGameManually(View view){
        Intent intent = new Intent(this, VAddGameManually.class);
        startActivity(intent);
    }

    public void onclickAddGameSearch(View view){
        Intent intent = new Intent(this, VAddGameSearch.class);
        startActivity(intent);
    }

    public void onclickProfile(View view) {
        Intent intent = new Intent(this, VProfile. class);
        startActivity(intent);
    }

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