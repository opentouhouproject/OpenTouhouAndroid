package opentouhou.com.opentouhouandroid.game.OpenGLESTest;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.Renderer30;

/**
 * For testing/ prototyping.
 */

public class OpenGLESTestView extends GLSurfaceView
{
    public volatile boolean ready = false;

    private final float TOUCH_SCALE_FACTOR = 180.0f / 600;
    private float mPreviousX;
    private float mPreviousY;

    public Renderer renderer;

    public OpenGLESTestView(Context context)
    {
        super(context);

        // Create an OpenGL ES 3 context.
        setEGLContextClientVersion(3);

        // Create the new renderer.
        renderer = new Renderer30(context);
        //renderer = new Renderer20(context);

        // Set the renderer.
        setRenderer(renderer);

        // Render only when screen changes
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        /*
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (e.getY() > getHeight() - 50) {
                    //gameThread.setRunning(false);
                    ((Activity) getContext()).finish();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                // reverse direction of rotation below the mid-line
                if (y < getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // reverse direction of rotation to right of the mid-line
                if (x > getWidth() / 2) {
                    dy = dy * -1 ;
                }

                render.setAngle(
                        render.getAngle() +
                                ((dx + dy) * TOUCH_SCALE_FACTOR));
                requestRender();
                break;
        }

        mPreviousX = x;
        mPreviousY = y;
        */
        return true;
    }
}