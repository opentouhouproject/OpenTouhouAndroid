package com.scarlet.graphics;

import android.graphics.Bitmap;

import com.scarlet.math.Vector3f;

import static com.scarlet.graphics.ColorFormatConverter.HSVtoRGB;
import static com.scarlet.graphics.ColorFormatConverter.RGBtoHSV;

/**
 * Used to apply effects to a bitmap.
 */
public final class BitmapEditor {
  private BitmapEditor() { }

  /**
   * Desaturate a bitmap image.
   *
   * @param bitmap The bitmap to desaturate.
   * @return The bitmap after desaturation.
   */
  public static Bitmap desaturate(Bitmap bitmap) {
    int width = bitmap.getWidth();
    int height = bitmap.getHeight();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int color = bitmap.getPixel(x, y);

        float red = (color & 0b00000000111111110000000000000000) >> 16;
        float green = (color & 0b00000000000000001111111100000000) >> 8;
        float blue = color & 0b00000000000000000000000011111111;

        float factor = ((red / 255) * 0.299f) + ((green / 255) * 0.587f) + ((blue / 255) * 0.114f);

        int r = (int)(factor * red);
        int g = (int)(factor * green);
        int b = (int)(factor * blue);

        color = (color & 0b11111111000000000000000000000000) | (r << 16) | (g << 8) | b;

        bitmap.setPixel(x, y, color);
      }
    }

    return bitmap;
  }

  /**
   * Desaturates a bitmap.
   *
   * Algorithm:
   * (1) Converts RGB color value to HSV color value.
   * (2) Desaturate the HSV color value.
   * (3) Convert the desaturated HSV color back to RGB.
   *
   * @param bitmap The bitmap to desaturate.
   * @param saturation The factor to multiply the saturation value by.
   * @return The desaturated bitmap.
   */
  public static Bitmap greyscale(Bitmap bitmap, float saturation) {
    int width = bitmap.getWidth();
    int height = bitmap.getHeight();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int color = bitmap.getPixel(x, y);

        float red = (color & 0b00000000111111110000000000000000) >> 16;
        float green = (color & 0b00000000000000001111111100000000) >> 8;
        float blue = color & 0b00000000000000000000000011111111;

        Vector3f hsv = RGBtoHSV(new Vector3f(red/255, green/255, blue/255));

        hsv.y *= saturation;

        Vector3f rgb = HSVtoRGB(hsv);

        int r = (int)(255 * rgb.x);
        int g = (int)(255 * rgb.y);
        int b = (int)(255 * rgb.z);

        color = (color & 0b11111111000000000000000000000000) | (r << 16) | (g << 8) | b;

        bitmap.setPixel(x, y, color);
      }
    }

    return bitmap;
  }
}