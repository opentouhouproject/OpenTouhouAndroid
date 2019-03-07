package opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.shader;

import android.content.res.AssetManager;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractShaderManager;

/**
 * Shader Manager implementation for OpenGL ES 2.0
 */

public class ShaderManager extends AbstractShaderManager
{
    public ShaderManager(AssetManager assetManager)
    {
        super(assetManager);
    }

    public void load()
    {
        // Create vertex shaders.
        createVertexShader("Triangle", "Triangle.vert");
        createVertexShader("Triangle2", "Triangle2.vert");
        createVertexShader("PerFragmentLighting", "PerFragmentLighting.vert");
        createVertexShader("TextureShader", "TextureShader.vert");

        // Create fragment shaders.
        createFragmentShader("Triangle", "Triangle.frag");
        createFragmentShader("Triangle2", "Triangle2.frag");
        createFragmentShader("PerFragmentLighting", "PerFragmentLighting.frag");
        createFragmentShader("TextureShader", "TextureShader.frag");

        // Create shader programs.
        createShaderProgram("Triangle", "Triangle", "Triangle");
        createShaderProgram("Triangle2", "Triangle2", "Triangle2");
        createShaderProgram("PerFragmentLighting", "PerFragmentLighting", "PerFragmentLighting");
        createShaderProgram("TextureShader", "TextureShader", "TextureShader");
    }

    // Create shaders.
    public void createVertexShader(String name, String filePath)
    {
        VertexShader vertexShader = new VertexShader(name);
        vertexShader.compile(readFile(filePath));

        vertexShaders.put(name, vertexShader);
    }

    public void createFragmentShader(String name, String filePath)
    {
        FragmentShader fragmentShader = new FragmentShader(name);
        fragmentShader.compile(readFile(filePath));

        fragmentShaders.put(name, fragmentShader);
    }

    public void createShaderProgram(String name, String vertexShader, String fragmentShader)
    {
        ShaderProgram shaderProgram = new ShaderProgram(name);
        shaderProgram.link(vertexShaders.get(vertexShader), fragmentShaders.get(fragmentShader));

        shaderPrograms.put(name, shaderProgram);
    }
}