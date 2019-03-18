package opentouhou.com.opentouhouandroid.entity.OpenGLES;

import opentouhou.com.opentouhouandroid.math.Vector3f;

/**
 * Light Source
 */

public class LightSource {

    private Vector3f position;

    public LightSource() {
        position = new Vector3f();
    }

    public LightSource(float x, float y, float z) {
        position = new Vector3f(x, y, z);
    }

    public Vector3f getPosition() {
        return position;
    }
}
