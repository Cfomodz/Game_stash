package com.example.game_stash;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MAPIConnection implements Runnable {
    private static final String TAG = MAPIConnection.class.getSimpleName();

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
            Scanner apiScanner = new Scanner(response);
            this.apiResponse = apiScanner.useDelimiter("\\A").next();
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
        if (presenterRef.get() != null) {
            presenterRef.get().processUpdates();
        }
    }
}
