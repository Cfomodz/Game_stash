package com.gamestash.app;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;

/**
 * TReadJSON reads local JSON files that include the user's data,
 * such as their game list and their location list.
 */

public class TReadJSON implements Runnable{
    private static final String TAG = TReadJSON.class.getSimpleName();

    // Member variables.
    private WeakReference<IProcess> presenterRef;
    private File fileName;

    // Constructor.
    public TReadJSON(IProcess presenter, File fileName) {
        this.presenterRef = new WeakReference<>(presenter);
        this.fileName = fileName;
    }

    // Read from file, save to jsonString.
    @Override
    public void run() {
        String jsonString;
        try {
            InputStream is = new FileInputStream(this.fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            int exists = is.read(buffer);
            is.close();

            if(exists > -1) {
                jsonString = new String(buffer, StandardCharsets.UTF_8);
            } else {
                jsonString = null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            jsonString = null;
        }

        if(jsonString != null) {
            Log.d(TAG, jsonString);
        } else {
            Log.d(TAG, "file not found.");
        }

        if (jsonString != null && !jsonString.equals("")){
            DApp.setReturnJSONStr(jsonString, this.fileName);
        }

        //ADD the process piece...
        //Notify Presenter of update...
        if (this.presenterRef.get() != null) {
            this.presenterRef.get().processChanges();
        }
    }
}
