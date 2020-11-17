package com.example.game_stash;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MAPIConnection implements Runnable {
    private static final String TAG = "Msg_MDAPIConnection:";
    private String url;
    private URLConnection connection = null;
    private InputStream response = null;
    private String apiResponse;

    public MAPIConnection(String url) {
        this.url = url;
    }

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
            Scanner weatherScanner = new Scanner(response);
            this.apiResponse = weatherScanner.useDelimiter("\\A").next();
        } else {
            this.apiResponse = "";
        }
        MDataHolder.setReturnApiSTR(this.apiResponse);
        Log.d(TAG, MDataHolder.getReturnApiSTR());
    }
}
