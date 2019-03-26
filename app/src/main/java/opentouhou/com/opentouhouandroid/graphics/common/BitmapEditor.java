package opentouhou.com.opentouhouandroid.graphics.common;

import android.graphics.Bitmap;

import com.scarlet.math.Vector3f;

/**
 * Used to apply effects to a bitmap.
 */

public class BitmapEditor
{
    public static Bitmap desaturate(Bitmap bitmap)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                int color = bitmap.getPixel(x, y);

                float red = (color & 0b00000000111111110000000000000000) >> 16;
                float green = (color & 0b00000000000000001111111100000000) >> 8;
                float blue = color & 0b00000000000000000000000011111111;

                //Log.d("Bitmap Editor Logging", "R: " + red + " G: " + green + " B: " + blue);

                float factor = ((red / 255) * 0.299f) + ((green / 255) * 0.587f) + ((blue / 255) * 0.114f);

                //Log.d("Bitmap Editor Logging", "Factor: " + factor);

                int r = (int)(factor * red);
                int g = (int)(factor * green);
                int b = (int)(factor * blue);

                //Log.d("Bitmap Editor Logging", "R: " + r + " G: " + g + " B: " + b);

                color = (color & 0b11111111000000000000000000000000) | (r << 16) | (g << 8) | b;

                // Log.d("Bitmap Editor Logging", Integer.toBinaryString(color));

                bitmap.setPixel(x, y, color);
            }
        }

        return bitmap;
    }

    public static Bitmap greyscale(Bitmap bitmap, float saturation)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                int color = bitmap.getPixel(x, y);

                float red = (color & 0b00000000111111110000000000000000) >> 16;
                float green = (color & 0b00000000000000001111111100000000) >> 8;
                float blue = color & 0b00000000000000000000000011111111;

                Vector3f hsv = RGBtoHSV(new Vector3f(red/255, green/255, blue/255));

                //Log.d("Bitmap Editor Logging", "H: " + hsv.x + " S: " + hsv.y + " V: " + hsv.z);

                hsv.y *= saturation;

                Vector3f rgb = HSVtoRGB(hsv);

                int r = (int)(255 * rgb.x);
                int g = (int)(255 * rgb.y);
                int b = (int)(255 * rgb.z);

                //Log.d("Bitmap Editor Logging", "R: " + r + " G: " + g + " B: " + b);

                color = (color & 0b11111111000000000000000000000000) | (r << 16) | (g << 8) | b;


                bitmap.setPixel(x, y, color);
            }
        }

        return bitmap;
    }

    private static Vector3f RGBtoHSV(Vector3f rgb)
    {
        float h, s, v;
        float min, max, delta;

        // Compute the max, min and difference.
        min = rgb.x < rgb.y ? rgb.x : rgb.y;
        min = min < rgb.z ? min : rgb.z;

        max = rgb.x > rgb.y ? rgb.x : rgb.y;
        max = max > rgb.z ? max : rgb.z;

        delta = max - min;

        // For small delta aka R=G=B
        if (delta < 0.00001)
        {
            return new Vector3f(0, 0, max);
        }

        // Standard case.
        if (rgb.x >= max) {
            h = (rgb.y - rgb.z) / delta;
        }
        else if (rgb.x >= max) {
            h = 2.0f + (rgb.z - rgb.x) / delta;
        }
        else {
            h = 4.0f + (rgb.x - rgb.y) / delta;
        }
        h *= 60;

        if (h < 0.0)
        {
            h += 360.0;
        }

        return new Vector3f(h, delta / max, max);
    }

    private static Vector3f HSVtoRGB(Vector3f hsv)
    {
        float hh, p, q, t, ff;
        long i;
        Vector3f rgb = new Vector3f(0, 0, 0);

        if(hsv.y <= 0.0)
        {
            rgb.x = hsv.z;
            rgb.y = hsv.z;
            rgb.z = hsv.z;

            return rgb;
        }

        hh = hsv.x;
        if (hh >= 360.0)
        {
            hh = 0.0f;
        }
        hh /= 60.0;
        i = (long)hh;
        ff = hh - i;

        p = hsv.z * (1.0f - hsv.y);
        q = hsv.z * (1.0f - (hsv.y * ff));
        t = hsv.z * (1.0f - (hsv.y * (1.0f - ff)));

        switch((int)i) {
            case 0:
                rgb.x = hsv.z;
                rgb.y = t;
                rgb.z = p;
                break;

            case 1:
                rgb.x = q;
                rgb.y = hsv.z;
                rgb.z = p;
                break;

            case 2:
                rgb.x = p;
                rgb.y = hsv.z;
                rgb.z = t;
                break;

            case 3:
                rgb.x = p;
                rgb.y = q;
                rgb.z = hsv.z;
                break;

            case 4:
                rgb.x = t;
                rgb.y = p;
                rgb.z = hsv.z;
                break;

            case 5:

            default:
                rgb.x = hsv.z;
                rgb.y = p;
                rgb.z = q;
                break;
        }

        return rgb;
    }
}