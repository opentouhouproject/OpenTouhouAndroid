package opentouhou.com.opentouhouandroid.graphics.opengl.common.texture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.util.Hashtable;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;

/**
 * Created by Kenny 2019-02-21
 */

public abstract class AbstractTextureManager
{
    // Hash table that maps resource ids to texture objects.
    protected Hashtable<Integer, AbstractTexture> bitmapTable;

    // Constructor
    public AbstractTextureManager()
    {
        bitmapTable = new Hashtable<>();
    }

    // Retrieve the texture object.
    public AbstractTexture getTexture(int resourceId)
    {
        return bitmapTable.get(resourceId);
    }

    // Retrieve the texture handle.
    public int getTextureHandle(int resourceId) { return bitmapTable.get(resourceId).getTextureHandle(); }

    // Loads multiple resource.
    public void loadBitmaps(int[] resourceIds, Renderer renderer)
    {
        // Create textures.
        for (int id : resourceIds)
        {
            loadBitmap(id, renderer);
        }
    }

    // Loads a resource as a texture.
    public void loadBitmap(int resourceId, Renderer renderer)
    {
        // Decode the resource.
        InputStream in = renderer.getContext().getResources().openRawResource(resourceId);
        Bitmap bitmap = decodeBitmap(in);

        // Create TextureGL class.
        AbstractTexture tex = createTexture(bitmap);

        // Recycle the bitmap.
        bitmap.recycle();

        // Save the association.
        bitmapTable.put(resourceId, tex);
    }

    // Decodes the bitmap given by the resource id.
    private Bitmap decodeBitmap(InputStream stream)
    {
        // Set the decoding options.
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false; // No pre-scaling

        return BitmapFactory.decodeStream(stream, null, options);
    }

    abstract protected AbstractTexture createTexture(Bitmap bitmap);
}