package com.gamestash.app;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * <h1>MAPIConnection</h1>
 * Handles connecting to the API and the response from the website.
 */
public class MAPIConnection implements Runnable {
    private static final String TAG = MAPIConnection.class.getSimpleName();

    private WeakReference<IProcess> presenterRef;
    private String url;
    private URLConnection connection = null;
    private InputStream response = null;
    private String apiResponse;

    public MAPIConnection(IProcess presenter, String url) {
        this.presenterRef = new WeakReference<>(presenter);
        this.url = url;
    }

    /**
     * run gets things going.
     */
    @Override
    public void run() {
        try {
            connection = new URL(this.url).openConnection();
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            Scanner apiScanner = new Scanner(response);
            this.apiResponse = apiScanner.useDelimiter("\\A").next();
        } else {

            this.apiResponse = "";
        }

        try {
            assert response != null;
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DApp.setReturnApiSTR(this.apiResponse);
        Log.d(TAG, DApp.getReturnApiSTR());

        //Notify Presenter of update...
        if (presenterRef.get() != null) {
            presenterRef.get().processChanges();
        }
    }
}
