package opentouhou.com.opentouhouandroid.graphics.opengl.common.texture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import opentouhou.com.opentouhouandroid.graphics.common.BitmapEditor;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;

/**
 * Created by Kenny 2019-02-21
 */

public abstract class AbstractTextureManager
{
    public enum Options
    {
        NONE, DESATURATE, GREYSCALE, LIGHTGREYSCALE
    }

    // Hash table that maps resource ids to texture objects.
    private Hashtable<Integer, AbstractTexture> bitmapTable;

    // Hash table that maps asset paths to texture objects.
    private Hashtable<String, AbstractTexture> assetMap;

    // Constructor
    public AbstractTextureManager()
    {
        bitmapTable = new Hashtable<>();
        assetMap = new Hashtable<>();
    }

    // Retrieve the texture object.
    public AbstractTexture getTexture(int resourceId)
    {
        return bitmapTable.get(resourceId);
    }
    public AbstractTexture getTexture(String path)
    {
        return assetMap.get(path);
    }

    // Retrieve the texture handle.
    public int getTextureHandle(int resourceId) { return bitmapTable.get(resourceId).getTextureHandle(); }
    public int getTextureHandle(String path) { return assetMap.get(path).getTextureHandle(); }

    // Loads multiple resources.
    public void loadResourceBitmaps(int[] resourceIds, Renderer renderer)
    {
        for (int id : resourceIds)
        {
            loadResourceBitmap(id, Options.NONE, renderer);
        }
    }

    // Loads multiple assets.
    public void loadAssetBitmaps(String[] assetPaths, Renderer renderer)
    {
        for (String path : assetPaths)
        {
            loadAssetBitmap(path, Options.NONE, renderer);
        }
    }

    // Loads an image from the drawable resources.
    public void loadResourceBitmap(int resourceId, Options option, Renderer renderer)
    {
        // Open a stream.
        InputStream in = renderer.getContext().getResources().openRawResource(resourceId);

        // Create the texture.
        AbstractTexture texture = decodeBitmap(in, option);

        // Save the association.
        bitmapTable.put(resourceId, texture);
    }

    // Loads an image from the assets.
    public void loadAssetBitmap(String path, Options option, Renderer renderer)
    {
        InputStream in;

        try
        {
            // Open a stream.
            in = renderer.getContext().getAssets().open(path);

            // Create the texture.
            AbstractTexture texture = decodeBitmap(in, option);

            // Save the association.
            assetMap.put(path, texture);
        }
        catch (IOException e)
        {
            Log.d("Game Debug", "Failed to open bitmap from assets. (" + path + ")");
        }
    }

    // Decodes the bitmap given by the resource id.
    private AbstractTexture decodeBitmap(InputStream stream, Options option)
    {
        // Set the decoding options.
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false; // No pre-scaling
        options.inMutable = true;

        // Decode the bitmap.
        Bitmap bitmap = BitmapFactory.decodeStream(stream, null, options);

        // Check if we do anything.
        switch (option)
        {
            case DESATURATE:
                bitmap = BitmapEditor.desaturate(bitmap);
                break;

            case GREYSCALE:
                bitmap = BitmapEditor.greyscale(bitmap, 0.5f);
                break;

            case LIGHTGREYSCALE:
                bitmap = BitmapEditor.greyscale(bitmap, 0.1f);
                break;
        }

        // Create the texture.
        AbstractTexture texture = createTexture(bitmap, options);

        // Recycle the bitmap.
        bitmap.recycle();

        return texture;
    }

    abstract protected AbstractTexture createTexture(Bitmap bitmap, BitmapFactory.Options options);
}