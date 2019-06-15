package com.scarlet.graphics;

import com.scarlet.math.Vector3f;

/**
 * Provides methods for performing color format conversion.
 */
public final class ColorFormatConverter {
  private ColorFormatConverter() { }

  /**
   * Convert a RGB color value to a HSV color value.
   *
   * @param rgb A vector of 3 floats representing a RGB color value.
   * @return A vector of 3 floats representing a HSV color value.
   */
  public static Vector3f RGBtoHSV(Vector3f rgb) {
    float h, s, v;
    float min, max, delta;

    // Compute the max, min and difference.
    min = rgb.x < rgb.y ? rgb.x : rgb.y;
    min = min < rgb.z ? min : rgb.z;

    max = rgb.x > rgb.y ? rgb.x : rgb.y;
    max = max > rgb.z ? max : rgb.z;

    delta = max - min;

    // For small delta aka R=G=B
    if (delta < 0.00001) {
      return new Vector3f(0, 0, max);
    }

    // Standard case.
    if (rgb.x >= max) {
      h = (rgb.y - rgb.z) / delta;

    } else if (rgb.y >= max) {
      h = 2.0f + (rgb.z - rgb.x) / delta;

    } else {
      h = 4.0f + (rgb.x - rgb.y) / delta;
    }
    h *= 60;

    if (h < 0.0) {
      h += 360.0;
    }

    return new Vector3f(h, delta / max, max);
  }

  /**
   * Converts a HSV color value to a RGB color value.
   *
   * @param hsv A vector of 3 floats representing a HSV color value.
   * @return A vector of 3 floats representing a RGB color value.
   */
  public static Vector3f HSVtoRGB(Vector3f hsv) {
    float hh, p, q, t, ff;
    long i;
    Vector3f rgb = new Vector3f(0, 0, 0);

    if(hsv.y <= 0.0) {
      rgb.x = hsv.z;
      rgb.y = hsv.z;
      rgb.z = hsv.z;

      return rgb;
    }

    hh = hsv.x;
    if (hh >= 360.0) {
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