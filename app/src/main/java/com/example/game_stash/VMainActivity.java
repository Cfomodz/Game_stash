package com.example.game_stash;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;

public class VMainActivity extends AppCompatActivity {
    private static final String TAG = "Msg_VMainActivity:";

    private static final String TAG_B = "VMainActivity_B:";
    private static final String TAG_T = "VMainActivity_T:";
    private static final String TAG_M = "VMainActivity_M:";
    private static final String TAG_A = "VMainActivity_A:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void testStuff4Lee() {
        //TEST AREA for Lee
        Log.d(TAG_B, "Building API Query URL");
        String test = new MAPIQueryURL("", "", "Certifiable Games", -1, 10,-1,-1).getUrl();
        Log.d(TAG_B, "URL: " + test);

        MAPIConnection connection = new MAPIConnection(test);
        Log.d(TAG_B, "Beginning MAPI CONNECTION");
        Thread thread = new Thread(connection);
        thread.start();
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