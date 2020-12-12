package com.gamestash.app;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * TSaveToFile saves the user's data, such as their list of
 * locations and their list of games, to disk.
 */

public class TSaveToFile implements Runnable{

    // Member variables.
    private static final String TAG = TSaveToFile.class.getSimpleName();
    private final WeakReference<Context> context;
    private final String fileName;
    private final String fileContents;

    // Constructors.
    public TSaveToFile(Context context, String fileName, String fileContents){
        this.context = new WeakReference<>(context);
        this.fileName = fileName;
        this.fileContents = fileContents;
    }

    public void run() {save();}

    public boolean save() {
        if(this.fileContents != null){
            try (FileOutputStream fos = this.context.get().openFileOutput(this.fileName, Context.MODE_PRIVATE)) {
                fos.write(this.fileContents.getBytes());
                Log.d(TAG, this.fileName + " should be written...");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
