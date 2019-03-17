package opentouhou.com.opentouhouandroid.graphics.opengl.common.texture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import opentouhou.com.opentouhouandroid.graphics.common.BitmapEditor;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.io.FileManager;

/*
 * Manages all textures.
 */

public abstract class TextureManager
{
    public enum Options
    {
        NONE, DESATURATE, GREYSCALE, LIGHTGREYSCALE
    }

    // Hash table that maps resource ids to texture objects.
    private Hashtable<Integer, Texture> bitmapTable;

    // Hash table that maps asset paths to texture objects.
    private Hashtable<String, Texture> assetMap;

    // Constructor
    public TextureManager()
    {
        bitmapTable = new Hashtable<>();
        assetMap = new Hashtable<>();
    }

    // Retrieve the texture object.
    public Texture getTexture(int resourceId)
    {
        return bitmapTable.get(resourceId);
    }

    public Texture getTexture(String path)
    {
        return assetMap.get(path);
    }

    // Retrieve the texture handle.
    public int getTextureHandle(int resourceId) { return bitmapTable.get(resourceId).getTextureHandle(); }
    public int getTextureHandle(String path) { return assetMap.get(path).getTextureHandle(); }

    // Loads multiple resources.
    public void loadResourceBitmaps(int[] resourceIds, FileManager fileManager)
    {
        for (int id : resourceIds)
        {
            loadResourceBitmap(id, Options.NONE, fileManager);
        }
    }

    // Loads multiple assets.
    public void loadAssetBitmaps(String[] assetPaths, FileManager fileManager)
    {
        for (String path : assetPaths)
        {
            loadAssetBitmap(path, Options.NONE, fileManager);
        }
    }

    // Loads an image from the drawable resources.
    public void loadResourceBitmap(int resourceId, Options option, FileManager fileManager) {
        // Set the decoding options.
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false; // No pre-scaling
        options.inMutable = true;

        // Decode the bitmap.
        Bitmap bitmap = fileManager.openBitmapResource(resourceId, options);

        // Edit the bitmap.
        bitmap = editBitmap(bitmap, option);

        // Create the texture.
        Texture texture = createTexture(bitmap, options);

        // Free the native object associated with the bitmap.
        bitmap.recycle();

        // Save the association.
        bitmapTable.put(resourceId, texture);
    }

    // Loads an image from the assets.
    public void loadAssetBitmap(String path, Options option, FileManager fileManager) {
        // Set the decoding options.
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false; // No pre-scaling
        options.inMutable = true;

        // Decode the bitmap.
        Bitmap bitmap = fileManager.openBitmapAsset(path, options);

        // Edit the bitmap.
        bitmap = editBitmap(bitmap, option);

        // Create the texture.
        Texture texture = createTexture(bitmap, options);

        // Free the native object associated with the bitmap.
        bitmap.recycle();

        // Save the association.
        assetMap.put(path, texture);
    }

    // Edits the given bitmap.
    private Bitmap editBitmap(Bitmap bitmap, Options option) {
        // Check if we edit the bitmap.
        switch (option) {
            case DESATURATE:
                return BitmapEditor.desaturate(bitmap);

            case GREYSCALE:
                return BitmapEditor.greyscale(bitmap, 0.5f);

            case LIGHTGREYSCALE:
                return BitmapEditor.greyscale(bitmap, 0.1f);

            default:
                return bitmap;
        }
    }

    abstract protected Texture createTexture(Bitmap bitmap, BitmapFactory.Options options);
}