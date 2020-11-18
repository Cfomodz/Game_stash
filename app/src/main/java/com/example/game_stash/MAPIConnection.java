package com.example.game_stash;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MAPIConnection implements Runnable {
    private static final String TAG = "Msg_MDAPIConnection:";
    private WeakReference<IPresenter> presenterRef;
    private String url;
    private URLConnection connection = null;
    private InputStream response = null;
    private String apiResponse;

    public MAPIConnection(IPresenter presenter, String url) {
        this.presenterRef = new WeakReference<IPresenter>(presenter);
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

        try {
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MDataHolder.setReturnApiSTR(this.apiResponse);
        Log.d(TAG, MDataHolder.getReturnApiSTR());

        //Notify Presenter of update...
        final IPresenter presenter = presenterRef.get();

        if (presenter != null) {
            //DO STUFF W/PRESENTER LIKE TELL IT THAT THE INFO HAS BEEN UPDATED...
            //Then let presenter validate the string and send it to MGSON Parser...
        }

        //RIP THIS OUT...
        MGSONParser gsonParser = new MGSONParser(this.apiResponse);
        MGameList gameList = gsonParser.getGameList();
        if(!gameList.gameList.isEmpty()) {
            Log.d(TAG, gameList.gameList.get(0).getName());
        }
    }
}
