package com.example.game_stash;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class MSaveToFile {
    private static final String TAG = MSaveToFile.class.getSimpleName();
    private WeakReference<Context> context;
    private String fileName;
    private String fileContents;

    public MSaveToFile(Context context, String fileName, String fileContents){
        this.context = new WeakReference<>(context);
        this.fileName = fileName;
        this.fileContents = fileContents;
    }

    public boolean run() {
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
