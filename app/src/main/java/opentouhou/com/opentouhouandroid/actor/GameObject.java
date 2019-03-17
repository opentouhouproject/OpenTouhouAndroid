package opentouhou.com.opentouhouandroid.actor;

import opentouhou.com.opentouhouandroid.scene.Scene;

public abstract class GameObject {
    abstract void update();
    abstract void draw(Scene scene);
}