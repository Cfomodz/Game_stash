package com.gamestash.app;

import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;

public class MReadJSON implements Runnable{
    private static final String TAG = MReadJSON.class.getSimpleName();

    private WeakReference<IProcess> presenterRef;

    public MReadJSON(IProcess presenter) {
        this.presenterRef = new WeakReference<>(presenter);
    }

    @Override
    public void run() {
        String jsonString;
        try {
            InputStream is = new FileInputStream(MDataHolder.getUserJSONFile());

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
            MDataHolder.setReturnUserSTR(jsonString);
        }

        //ADD the process piece...
        //Notify Presenter of update...
        if (this.presenterRef.get() != null) {
            this.presenterRef.get().processChanges();
        }
    }
}
