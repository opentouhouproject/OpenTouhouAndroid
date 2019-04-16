package opentouhou.com.opentouhouandroid.entity.petals;

import android.opengl.GLES30;

import com.scarlet.math.CubicBezierCurve;
import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

import com.scarlet.graphics.opengl.GraphicsOptions;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.mesh.MeshLayout;
import com.scarlet.graphics.opengl.shader.ShaderProgram;
import com.scarlet.opengles30.GraphicsObject30;
import com.scarlet.opengles30.mesh.Mesh30;

/*
 * Holds the information needed to draw a petal in OpenGL.
 */

public class PetalDrawable30 extends GraphicsObject30 {
    private static Vector4f stemColor = new Vector4f(255f / 255f, 183f / 255f, 197f / 255f, 0.9f);
    private static Vector4f leafColor = new Vector4f(255f / 255f, 197f / 255f, 208f / 255f, 0.9f);
    private static Vector3f normal = new Vector3f(0, 0, 1);

    /*
     * Constructor(s).
     */
    public PetalDrawable30(GraphicsOptions options, Renderer renderer) {
        super(options);

        setup(renderer);
    }

    /*
     * Setups the drawable data.
     */
    private void setup(Renderer renderer) {
        // Retrieve the needed variables.
        ShaderProgram program = renderer.getShaderManager().getShaderProgram("Petal");

        // Set the mesh.
        float[] meshPoints = generateMesh();
        Mesh30 mesh = new Mesh30(meshPoints, MeshLayout.Layout.PCN);
        mesh.createVAO(program.getHandle());
        setMesh(mesh);

        // Set the shader program.
        setShader(program);

        // Set the model matrix.
        setModelMatrix(Matrix4f.identityMatrix());
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

        //Log.d("PETAL DEBUG", "Point " + mesh[meshIndex] + " " + mesh[meshIndex + 1] + " " + mesh[meshIndex + 2]);

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

    /*
     * Draw this object.
     */
    public void draw(Renderer renderer, Petal petal) {
        // Set the shader program to use.
        int shaderHandle = shaderProgram.getHandle();
        GLES30.glUseProgram(shaderHandle);

        // Set the transformation matrices.
        setTransformationMatrices(shaderHandle, renderer.getCamera());

        // Set the light source(s).
        if (option.lightingSetting()) setLightPosition(shaderHandle, renderer.getCamera(), renderer.getLight());

        // Set the texture.
        if (option.textureSetting()) setTexture(shaderHandle);

        // Set the lifetime.
        int progressHandle = GLES30.glGetUniformLocation(shaderHandle, "uProgress");
        GLES30.glUniform1f(progressHandle, petal.currentLife());

        // Set the mesh.
        setMesh();

        // Draw the object.
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, mesh.getVertexCount());
    }
}
