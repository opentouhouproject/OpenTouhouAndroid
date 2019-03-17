package opentouhou.com.opentouhouandroid.actor;

import android.util.Log;

import opentouhou.com.opentouhouandroid.math.CubicBezierCurve;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsOptions;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.drawable.PetalDrawable30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh.Mesh30;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.math.Vector3f;
import opentouhou.com.opentouhouandroid.math.Vector4f;
import opentouhou.com.opentouhouandroid.scene.Scene;

/*
 * Simulates falling petals.
 */

public class PetalFall extends GameObject {
    Vector4f stemColor = new Vector4f(255f / 255f, 183f / 255f, 197f / 255f, 0.9f);
    Vector4f leafColor = new Vector4f(255f / 255f, 197f / 255f, 208f / 255f, 0.9f);
    Vector3f normal = new Vector3f(0, 0, 1);

    private PetalDrawable30 drawable, drawable2;

    private Petal[] petalList;

    private int numberOfPetals = 50;

    /*
     * Constructor(s).
     */
    public PetalFall(Renderer renderer) {
        // Generate a drawable petal.
        drawable = createDrawable(renderer);

        // Generate new petals.
        petalList = new Petal[numberOfPetals];

        for (int i = 0; i < numberOfPetals; i++) {
            petalList[i] = new Petal();
        }
    }

    /*
     * Implement update method.
     */
    public void update() {
        for (int i = 0; i < numberOfPetals; i++) {
            Petal petal = petalList[i];

            if (petal.isAnimated()) {
                petal.update();
            }

            petal.updateState();
        }
    }

    /*
     * Implement draw method.
     */
    public void draw(Scene scene) {
        for (int i = 0; i < numberOfPetals; i++) {
            Petal petal = petalList[i];

            if (petal.isAnimated()) {
                drawable.setModelMatrix(petal.getModel());
                drawable.draw(scene, petal);
            }
        }
    }

    private PetalDrawable30 createDrawable(Renderer renderer) {
        // Generate the mesh.
        float[] meshPointsTEMP = generateMesh();
        Mesh30 mesh = new Mesh30(meshPointsTEMP, renderer.getShaderManager().getShaderProgramHandle("Petal"), MeshLayout.Layout.PCN);

        // Create the drawable.
        GraphicsOptions opt = new GraphicsOptions(true, false);
        drawable = new PetalDrawable30(opt);

        // Set the mesh.
        drawable.setMesh(mesh);

        // Set the shader.
        drawable.setShader(renderer.getShaderManager().getShaderProgram("Petal"));

        // Set the model matrix.
        Matrix4f model = Matrix4f.getIdentity();
        drawable.setModelMatrix(model);

        return drawable;
    }

    private float[] generateMesh() {
        // Setup the Bezier curves.
        Vector3f[] left = {new Vector3f(0, -1, 2), new Vector3f(-1.3f, -0.03f, 2),
                           new Vector3f(-0.2f, 0.20f, 2), new Vector3f(0, 1, 2)};
        Vector3f[] right = {new Vector3f(0, -1, 2), new Vector3f(1.3f, -0.03f, 2),
                            new Vector3f(0.2f, 0.20f, 2), new Vector3f(0, 1, 2)};

        CubicBezierCurve leftCurve = new CubicBezierCurve(left);
        CubicBezierCurve rightCurve = new CubicBezierCurve(right);

        // Subdivide the bezier curves into segments.
        Vector3f[] leftPoints = leftCurve.subdivide(11);
        Vector3f[] rightPoints = rightCurve.subdivide(11);

        // Generate the stem points.
        Vector3f[] stemPoints = generateStem(leftPoints);

        // Compute the number of points to render.
        int numPoints = 2 * ((2 * 3) + (8 * 6));
        float[] mesh = new float[numPoints * 10];

        int meshIndex = 0;

        // left petal
        for (int stemIndex = 0; stemIndex < 10; stemIndex++)
        {
            if (stemIndex == 0)
            {
                addStartTriangle(mesh, stemIndex, meshIndex, stemPoints, leftPoints);
                meshIndex += 30;
            }
            else if (stemIndex == 9)
            {
                addEndTriangle(mesh, stemIndex, meshIndex, stemPoints, leftPoints);
                meshIndex += 30;
            }
            else
            {
                addQuad1(mesh, stemIndex, meshIndex, stemPoints, leftPoints);
                meshIndex += 30;
                addQuad2(mesh, stemIndex, meshIndex, stemPoints, leftPoints);
                meshIndex += 30;
            }
        }

        // right petal
        for (int stemIndex = 0; stemIndex < 10; stemIndex++)
        {
            if (stemIndex == 0)
            {
                addStartTriangleRight(mesh, stemIndex, meshIndex, stemPoints, rightPoints);
                meshIndex += 30;
            }
            else if (stemIndex == 9)
            {
                addEndTriangleRight(mesh, stemIndex, meshIndex, stemPoints, rightPoints);
                meshIndex += 30;
            }
            else
            {
                addQuad1Right(mesh, stemIndex, meshIndex, stemPoints, rightPoints);
                meshIndex += 30;
                addQuad2Right(mesh, stemIndex, meshIndex, stemPoints, rightPoints);
                meshIndex += 30;
            }
        }

        return mesh;
    }

    private Vector3f[] generateStem(Vector3f[] points)
    {
        Vector3f[] stemPoints = new Vector3f[11];

        // Compute the stem points.
        for (int i = 0; i <= 10; i++)
        {
            stemPoints[i] = new Vector3f(0, points[i].y, points[i].z);
        }

        return stemPoints;
    }

    private void addPoint(float[] mesh, int meshIndex, Vector3f position, Vector4f color, Vector3f normal)
    {
        mesh[meshIndex] = position.x;
        mesh[meshIndex + 1] = position.y;
        mesh[meshIndex + 2] = position.z;

        Log.d("PETAL DEBUG", "Point " + mesh[meshIndex] + " " + mesh[meshIndex + 1] + " " + mesh[meshIndex + 2]);

        mesh[meshIndex + 3] = color.x;
        mesh[meshIndex + 4] = color.y;
        mesh[meshIndex + 5] = color.z;
        mesh[meshIndex + 6] = color.w;

        mesh[meshIndex + 7] = normal.x;
        mesh[meshIndex + 8] = normal.y;
        mesh[meshIndex + 9] = normal.z;
    }

    private void addStartTriangle(float[] mesh, int stemIndex, int meshIndex, Vector3f[] stem, Vector3f[] leaf)
    {
        addPoint(mesh, meshIndex, stem[stemIndex], stemColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, stem[stemIndex + 1], stemColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, leaf[stemIndex + 1], leafColor, normal);
    }

    private void addStartTriangleRight(float[] mesh, int stemIndex, int meshIndex, Vector3f[] stem, Vector3f[] leaf)
    {
        addPoint(mesh, meshIndex, stem[stemIndex + 1], stemColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, stem[stemIndex], stemColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, leaf[stemIndex + 1], leafColor, normal);
    }

    private void addEndTriangle(float[] mesh, int stemIndex, int meshIndex, Vector3f[] stem, Vector3f[] leaf)
    {
        addPoint(mesh, meshIndex, stem[stemIndex], stemColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, stem[stemIndex + 1], stemColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, leaf[stemIndex], leafColor, normal);
    }

    private void addEndTriangleRight(float[] mesh, int stemIndex, int meshIndex, Vector3f[] stem, Vector3f[] leaf)
    {
        addPoint(mesh, meshIndex, stem[stemIndex + 1], stemColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, stem[stemIndex], stemColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, leaf[stemIndex], leafColor, normal);
    }

    private void addQuad1(float[] mesh, int stemIndex, int meshIndex, Vector3f[] stem, Vector3f[] leaf)
    {
        addPoint(mesh, meshIndex, stem[stemIndex], stemColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, stem[stemIndex + 1], stemColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, leaf[stemIndex], leafColor, normal);
    }

    private void addQuad1Right(float[] mesh, int stemIndex, int meshIndex, Vector3f[] stem, Vector3f[] leaf)
    {
        addPoint(mesh, meshIndex, stem[stemIndex + 1], stemColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, stem[stemIndex], stemColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, leaf[stemIndex], leafColor, normal);
    }

    private void addQuad2(float[] mesh, int stemIndex, int meshIndex, Vector3f[] stem, Vector3f[] leaf)
    {
        addPoint(mesh, meshIndex, leaf[stemIndex + 1], leafColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, leaf[stemIndex], leafColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, stem[stemIndex + 1], stemColor, normal);
    }

    private void addQuad2Right(float[] mesh, int stemIndex, int meshIndex, Vector3f[] stem, Vector3f[] leaf)
    {
        addPoint(mesh, meshIndex, leaf[stemIndex], leafColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, leaf[stemIndex + 1], leafColor, normal);
        meshIndex += 10;

        addPoint(mesh, meshIndex, stem[stemIndex + 1], stemColor, normal);
    }
}