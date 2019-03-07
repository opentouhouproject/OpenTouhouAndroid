package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import opentouhou.com.opentouhouandroid.math.Vector3f;
import opentouhou.com.opentouhouandroid.scene.Scene;

public abstract class AbstractText
{
    public abstract void render(String s, Vector3f position, Scene scene);
}