package com.scarlet.io;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * Manages all file reads.
 */

public class FileManager {
    private Context context;

    /*
     * Constructor(s).
     */
    public FileManager(Context context) {
        this.context = context;
    }

    public InputStreamReader openRawAsset(String filePath) {
        try {
            return new InputStreamReader(context.getAssets().open(filePath));
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to open raw asset.", e);
        }
    }

    public StringBuffer openTextAsset(String filePath) {
        BufferedReader reader = null;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            String line;

            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filePath)));

            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error reading shader from file.");
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException e) {
                throw new RuntimeException("Could not close BufferedReader.");
            }
        }

        return stringBuffer;
    }

    public Bitmap openBitmapResource(int resourceId, BitmapFactory.Options options) {
        InputStream in = null;

        try {
            in = context.getResources().openRawResource(resourceId);
            return BitmapFactory.decodeStream(in, null, options);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e) {
                throw new RuntimeException("Failed to close stream.", e);
            }
        }
    }

    public Bitmap openBitmapAsset(String filePath, BitmapFactory.Options options) {
        InputStream in = null;

        try {
            in = context.getAssets().open(filePath);
            return BitmapFactory.decodeStream(in, null, options);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to decode bitmap.", e);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e) {
                throw new RuntimeException("Failed to close stream.", e);
            }
        }
    }
}