package opentouhou.com.opentouhouandroid.actor;

import java.util.Random;

import opentouhou.com.opentouhouandroid.math.Matrix4f;

public class Petal
{
    public static final Random r = new Random();

    private Matrix4f model;
    long lifeTime, curLife;

    public Petal()
    {
        model = Matrix4f.identity();

        set();
    }

    // Setter(s)
    public void setModel(Matrix4f model) { this.model = model; }

    // Getter(s)
    public Matrix4f getModel() { return model; }

    // Sets a new petal.
    public void set()
    {
        // Set lifetime.
        curLife = System.currentTimeMillis();
        lifeTime = System.currentTimeMillis() + 10000;

        // Set the matrix
        model = Matrix4f.rotateZ(90, true);

        float x_offset = -1.0f + r.nextFloat() * (1.0f - (-1.0f));
        float y_offset = 1.0f + r.nextFloat() * (2.0f - (1.0f));
        model.translate(x_offset, y_offset, 0);
    }

    // Updates a petal.
    public void update()
    {
        model.translate(-0.01f, -0.01f, 0);
        curLife = System.currentTimeMillis();
    }
}