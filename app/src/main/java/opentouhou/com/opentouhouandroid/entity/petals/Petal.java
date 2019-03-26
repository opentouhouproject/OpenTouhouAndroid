package opentouhou.com.opentouhouandroid.entity.petals;

import android.os.SystemClock;

import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;

import java.util.Random;

/*
 * Represents a single petal.
 */
public class Petal {
    // RNGJESUS
    private static final Random r = new Random();

    // STATES
    private enum State {
        ALIVE, ANIMATED, DEAD
    }

    // Define wind animation parameters.
    private enum Wind {
        WIND_LEFT_SLOW, WIND_LEFT_FAST,
        WIND_RIGHT_SLOW, WIND_RIGHT_FAST
    }

    // units / second
    private static float LEFT_SLOW = -0.35f;
    private static float LEFT_FAST = -0.55f;
    private static float RIGHT_SLOW = 0.35f;
    private static float RIGHT_FAST = 0.55f;


    // Define flutter animation parameters.
    private enum Flutter {
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

    // Decide our animation.
    private float windRate;

    private float initAngle;
    private float curAngle;
    private float flutterRate;

    private State state;

    // MODEL TRANSFORMATION
    private Matrix4f model;
    private Vector3f position;

    // Timing variables.
    private long previousTime, currentTime, startTime, endTime;

    /*
     * Constructor(s).
     */
    public Petal() {
        state = State.DEAD;

        model = new Matrix4f();

        setup();
    }

    // Setter(s)
    public void setModel(Matrix4f model) { this.model = model; }

    // Getter(s)
    public Matrix4f getModel() { return model; }

    // Setup the petal.
    private void setup() {
        // Setup the timing.
        previousTime = SystemClock.uptimeMillis();
        currentTime = SystemClock.uptimeMillis();
        startTime = currentTime + (long) (r.nextFloat() * 8000);
        endTime = SystemClock.uptimeMillis() + 10000 + (long) (r.nextFloat() * 10000);

        // Setup the animation.
        setupWindAnimation();
        setupFlutterAnimation();

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
    private void setupWindAnimation() {
        int result = r.nextInt(4);

        switch (result) {
            case 0:
                windRate = LEFT_SLOW;
                break;

            case 1:
                windRate = LEFT_FAST;
                break;

            case 2:
                windRate = RIGHT_SLOW;
                break;

            case 3:
                windRate = RIGHT_FAST;
                break;
        }
    }

    private void setupFlutterAnimation() {
        int result = r.nextInt(9);

        switch (result) {
            case 0:
                initAngle = SWAY_0_INIT;
                flutterRate = 4;
                break;

            case 1:
                initAngle = SWAY_1_INIT;
                flutterRate = -4;
                break;

            case 2:
                initAngle = SWAY_2_INIT;
                flutterRate = 12;
                break;

            case 3:
                initAngle = SWAY_3_INIT;
                flutterRate = -12;
                break;

            case 4:
                initAngle = SWAY_4_INIT;
                flutterRate = 20;
                break;

            case 5:
                initAngle = SWAY_5_INIT;
                flutterRate = -20;
                break;

            case 6:
                initAngle = SWAY_6_INIT;
                flutterRate = 28;
                break;

            case 7:
                initAngle = SWAY_7_INIT;
                flutterRate = -28;
                break;

            case 8:
                initAngle = SWAY_8_INIT;
                flutterRate = 30;
                break;
        }

        curAngle = initAngle;
    }


    /*
     * Helpers
     */
    public float currentLife() {
        return (float)(currentTime - startTime) / (float)(endTime - startTime);
    }

    public boolean isAnimated() {
        return state == State.ANIMATED;
    }

    /*
     * Implement update method.
     */
    public void update() {
        if (isAnimated()) {
            updateAnimation();
        }

        updateState();
    }

    private void updateAnimation() {
        long delta = currentTime - previousTime;

        float deltaAngle = flutterRate * ((float)delta / 1000f);
        curAngle += deltaAngle;

        position.x += windRate * ((float)delta / 1000f);
        position.y += -0.02f;

        model.setIdentity();
        model.scale(0.25f, 0.25f, 1f);
        model.rotateZ(curAngle, true);
        model.translate(position.x, position.y, position.z);
    }

    private void updateState() {
        previousTime = currentTime;
        currentTime = SystemClock.uptimeMillis();

        if ((startTime < currentTime) && (currentTime < endTime)) {
            state = State.ANIMATED;
            return;
        }

        if (currentTime > endTime) {
            state = State.DEAD;
            setup();

            return;
        }
    }
}