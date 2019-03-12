package opentouhou.com.opentouhouandroid.actor;

import android.util.Log;

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
    private static float SWAY_0_FIRST = 49;
    private static float SWAY_0_FINAL = 90;

    private static float SWAY_1_INIT = 10;
    private static float SWAY_1_FIRST = 67;
    private static float SWAY_1_FINAL = 130;

    private static float SWAY_2_INIT = 15;
    private static float SWAY_2_FIRST = 89;
    private static float SWAY_2_FINAL = 160;

    private static float SWAY_3_INIT = 25;
    private static float SWAY_3_FIRST = 90;
    private static float SWAY_3_FINAL = 145;

    private static float SWAY_4_INIT = 40;
    private static float SWAY_4_FIRST = 123;
    private static float SWAY_4_FINAL = 167;

    private static float SWAY_5_INIT = 50;
    private static float SWAY_5_FIRST = 134;
    private static float SWAY_5_FINAL = 166;

    private static float SWAY_6_INIT = 65;
    private static float SWAY_6_FIRST = 150;
    private static float SWAY_6_FINAL = 290;

    private static float SWAY_7_INIT = 72;
    private static float SWAY_7_FIRST = 190;
    private static float SWAY_7_FINAL = 199;

    private static float SWAY_8_INIT = 94;
    private static float SWAY_8_FIRST = 201;
    private static float SWAY_8_FINAL = 276;

    // STATES
    private enum State
    {
        ALIVE, ANIMATED, DEAD, R1, R2
    }

    // RNGJESUS
    private static final Random r = new Random();

    // Decide our animation.
    private Wind windAnim;
    private float windRate;

    private Flutter flutter;
    private float initAngle, firstAngle, finalAngle;

    private State state;
    private State flutterState;
    private float curAngle;
    private long flutterCycle;
    private long flutterLength;

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
                firstAngle = SWAY_0_FIRST;
                finalAngle = SWAY_0_FINAL;
                break;

            case 1:
                flutter = Flutter.SWAY_1;
                initAngle = SWAY_1_INIT;
                firstAngle = SWAY_1_FIRST;
                finalAngle = SWAY_1_FINAL;
                break;

            case 2:
                flutter = Flutter.SWAY_2;
                initAngle = SWAY_2_INIT;
                firstAngle = SWAY_2_FIRST;
                finalAngle = SWAY_2_FINAL;
                break;

            case 3:
                flutter = Flutter.SWAY_3;
                initAngle = SWAY_3_INIT;
                firstAngle = SWAY_3_FIRST;
                finalAngle = SWAY_3_FINAL;
                break;

            case 4:
                flutter = Flutter.SWAY_4;
                initAngle = SWAY_4_INIT;
                firstAngle = SWAY_4_FIRST;
                finalAngle = SWAY_4_FINAL;
                break;

            case 5:
                flutter = Flutter.SWAY_5;
                initAngle = SWAY_5_INIT;
                firstAngle = SWAY_5_FIRST;
                finalAngle = SWAY_5_FINAL;
                break;

            case 6:
                flutter = Flutter.SWAY_6;
                initAngle = SWAY_6_INIT;
                firstAngle = SWAY_6_FIRST;
                finalAngle = SWAY_6_FINAL;
                break;

            case 7:
                flutter = Flutter.SWAY_7;
                initAngle = SWAY_7_INIT;
                firstAngle = SWAY_7_FIRST;
                finalAngle = SWAY_7_FINAL;
                break;

            case 8:
                flutter = Flutter.SWAY_8;
                initAngle = SWAY_8_INIT;
                firstAngle = SWAY_8_FIRST;
                finalAngle = SWAY_8_FINAL;
                break;
        }

        curAngle = initAngle;
        flutterCycle = 0;
        flutterLength = 4000 + (long) (r.nextFloat() * 8000);
        flutterState = State.R1;
    }


    // Updates a petal.
    public void update()
    {
        long delta = curTime - lastTime;

        float progress = 0;
        float deltaAngle = 0;
        //flutterCycle += delta;
        /*
        if (flutterCycle > flutterLength)
        {
            flutterCycle = flutterLength;
        }*/

        if (flutterState == State.R1)
        {
            progress = (float)(curTime - startTime) / (0.5f * (float) (endTime - startTime));
            deltaAngle = ((firstAngle - initAngle) * progress + initAngle) - curAngle;
            if (curAngle > firstAngle)
            {
                flutterState = State.R2;
            }

            //progress = (float)flutterCycle / (float)flutterLength;
            /*
            if (flutterCycle >= flutterLength)
            {
                flutterState = State.R2;
                flutterCycle = 0;
                flutterLength = 4000 + (long) (r.nextFloat() * 8000);
                curAngle = firstAngle;
            }
            else
            {
                deltaAngle = ((firstAngle - initAngle) * progress + initAngle) - curAngle;
                curAngle += deltaAngle;curAngle += deltaAngle;
            }*/
        }
        else if(flutterState == State.R2)
        {
            if (curAngle <= finalAngle) {
                progress = (float)(curTime - (startTime + 0.5f * (float) (endTime - startTime))) / (0.5f * (float) (endTime - startTime));
                deltaAngle = ((finalAngle - firstAngle) * progress + firstAngle) - curAngle;
            }

            //progress = (float)(curTime - (startTime + 0.5f * (float) (endTime - startTime))) / (0.5f * (float) (endTime - startTime));
            //deltaAngle = ((finalAngle - firstAngle) * progress + firstAngle) - curAngle;
            //progress = (float)flutterCycle / (float)flutterLength;

            /*
            if (flutterCycle >= flutterLength)
            {
                flutterState = State.R1;
                flutterCycle = 0;
                curAngle = finalAngle;
            }
            else
            {
                deltaAngle = ((finalAngle - firstAngle) * progress + firstAngle) - curAngle;
                curAngle += deltaAngle;
            }*/
        }

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