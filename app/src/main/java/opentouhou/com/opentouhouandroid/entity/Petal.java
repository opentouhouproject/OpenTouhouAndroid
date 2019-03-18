package opentouhou.com.opentouhouandroid.entity;

import java.util.Random;

import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.math.Vector3f;

public class Petal
{
    // Define wind animation parameters.
    private enum Wind
    {
        WIND_LEFT_SLOW, WIND_LEFT_FAST,
        WIND_RIGHT_SLOW, WIND_RIGHT_FAST
    }

    // units / second
    private static float LEFT_SLOW = -0.35f;
    private static float LEFT_FAST = -0.55f;

    private static float RIGHT_SLOW = 0.35f;
    private static float RIGHT_FAST = 0.55f;


    // Define flutter animation parameters.
    private enum Flutter
    {
        SWAY_0, SWAY_1, SWAY_2, SWAY_3, SWAY_4,
        SWAY_5, SWAY_6, SWAY_7, SWAY_8
    }

    // degrees
    private static float SWAY_0_INIT = -5;
    private static float SWAY_1_INIT = 10;
    private static float SWAY_2_INIT = 15;
    private static float SWAY_3_INIT = 25;
    private static float SWAY_4_INIT = 40;
    private static float SWAY_5_INIT = 50;
    private static float SWAY_6_INIT = 65;
    private static float SWAY_7_INIT = 72;
    private static float SWAY_8_INIT = 94;

    // STATES
    private enum State
    {
        ALIVE, ANIMATED, DEAD
    }

    // RNGJESUS
    private static final Random r = new Random();

    // Decide our animation.
    private Wind windAnim;
    private float windRate;

    private Flutter flutter;
    private float initAngle;
    private float curAngle;
    private float flutterRate;

    private State state;

    // MODEL TRANSFORMATION
    private Matrix4f model;
    private Vector3f position;

    // TIMING
    private long lastTime, curTime, startTime, endTime;

    public Petal()
    {
        state = State.DEAD;

        model = new Matrix4f();

        setAnimation();
    }

    // Setter(s)
    public void setModel(Matrix4f model) { this.model = model; }

    // Getter(s)
    public Matrix4f getModel() { return model; }

    // Sets a petal animation.
    public void setAnimation()
    {
        // Set timing.
        lastTime = System.currentTimeMillis();
        curTime = System.currentTimeMillis();
        startTime = curTime + (long) (r.nextFloat() * 8000);
        endTime = System.currentTimeMillis() + 10000 + (long) (r.nextFloat() * 10000);

        // Set the animation.
        setWindAnimation();
        setFlutterAnimation();

        // Set the matrix
        float x_offset = -4.0f + r.nextFloat() * 8.0f;
        float y_offset = 8.0f + r.nextFloat() * 1.0f;
        position = new Vector3f(x_offset, y_offset, 0);

        model.setIdentity();
        model.scale(0.25f, 0.25f, 1f);
        model.rotateZ(initAngle, true);
        model.translate(position.x, position.y, position.z);

        state = State.ALIVE;
    }

    // Selects a random wind animation.
    public void setWindAnimation()
    {
        int result = r.nextInt(4);

        switch (result)
        {
            case 0:
                windAnim = Wind.WIND_LEFT_SLOW;
                windRate = LEFT_SLOW;
                break;

            case 1:
                windAnim = Wind.WIND_LEFT_FAST;
                windRate = LEFT_FAST;
                break;

            case 2:
                windAnim = Wind.WIND_RIGHT_SLOW;
                windRate = RIGHT_SLOW;
                break;

            case 3:
                windAnim = Wind.WIND_RIGHT_FAST;
                windRate = RIGHT_FAST;
                break;
        }
    }

    public void setFlutterAnimation()
    {
        int result = r.nextInt(9);

        switch (result)
        {
            case 0:
                flutter = Flutter.SWAY_0;
                initAngle = SWAY_0_INIT;
                flutterRate = 4;
                break;

            case 1:
                flutter = Flutter.SWAY_1;
                initAngle = SWAY_1_INIT;
                flutterRate = -4;
                break;

            case 2:
                flutter = Flutter.SWAY_2;
                initAngle = SWAY_2_INIT;
                flutterRate = 12;
                break;

            case 3:
                flutter = Flutter.SWAY_3;
                initAngle = SWAY_3_INIT;
                flutterRate = -12;
                break;

            case 4:
                flutter = Flutter.SWAY_4;
                initAngle = SWAY_4_INIT;
                flutterRate = 20;
                break;

            case 5:
                flutter = Flutter.SWAY_5;
                initAngle = SWAY_5_INIT;
                flutterRate = -20;
                break;

            case 6:
                flutter = Flutter.SWAY_6;
                initAngle = SWAY_6_INIT;
                flutterRate = 28;
                break;

            case 7:
                flutter = Flutter.SWAY_7;
                initAngle = SWAY_7_INIT;
                flutterRate = -28;
                break;

            case 8:
                flutter = Flutter.SWAY_8;
                initAngle = SWAY_8_INIT;
                flutterRate = 30;
                break;
        }

        curAngle = initAngle;
    }


    // Updates a petal.
    public void update()
    {
        long delta = curTime - lastTime;

        float deltaAngle = 0;
        deltaAngle = flutterRate * ((float)delta / 1000f);
        curAngle += deltaAngle;

        position.x += windRate * ((float)delta / 1000f);
        position.y += -0.02f;

        model.setIdentity();
        model.scale(0.25f, 0.25f, 1f);
        model.rotateZ(curAngle, true);
        model.translate(position.x, position.y, position.z);
    }

    public float progress()
    {
        return (float)(curTime - startTime) / (float)(endTime - startTime);
    }

    // Check if a petal is live.
    public boolean isAnimated()
    {
        // Log.d("PETAL DEBUG", String.valueOf(state == State.ANIMATED));
        return state == State.ANIMATED;
    }

    public void updateState()
    {
        lastTime = curTime;
        curTime = System.currentTimeMillis();

        if ((startTime < curTime) && (curTime < endTime))
        {
            state = State.ANIMATED;
            return;
        }

        if (curTime > endTime)
        {
            state = State.DEAD;
            setAnimation();

            return;
        }
    }
}