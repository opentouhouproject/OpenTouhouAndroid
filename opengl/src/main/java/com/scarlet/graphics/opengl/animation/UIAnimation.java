package com.scarlet.graphics.opengl.animation;

import android.util.Log;

import com.scarlet.math.Vector3f;

public class UIAnimation extends InterpolatedAnimation {
    public Vector3f startPosition;
    public Vector3f endPosition;

    private float startAngle;
    private float endAngle;

    private Vector3f currentPosition;
    private float currentAngle;

    public UIAnimation(String name) {
        super(name);

        startPosition = new Vector3f(0, 0, 0);
        endPosition = new Vector3f(0, 0, 0);
    }

    public Vector3f getCurrentPosition() {
        return currentPosition;
    }

    public float getCurrentAngle() {
        return currentAngle;
    }

    public void setPositionParameters(Vector3f start, Vector3f end) {
        startPosition.set(start.x, start.y, start.z);
        endPosition.set(end.x, end.y, end.z);
        currentPosition = start;
    }

    public void setAngleParameters(float start, float end) {
        startAngle = start;
        endAngle = end;
        currentAngle = start;
    }

    @Override
    protected void update(float t) {
        //Log.d("anim before", startPosition.toString());
        currentPosition = endPosition.subtract(startPosition).multiply(t).add(startPosition);
        //Log.d("anim after", startPosition.toString());
        currentAngle = (endAngle - startAngle) * t + startAngle;
    }
}