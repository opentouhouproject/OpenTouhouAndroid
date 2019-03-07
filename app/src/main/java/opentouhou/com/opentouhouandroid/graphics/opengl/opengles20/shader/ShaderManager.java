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
        createVertexShader("Triangle", "shaders/opengles30/Triangle.vert");
        createVertexShader("Triangle2", "shaders/opengles30/Triangle2.vert");
        createVertexShader("PerFragmentLighting", "shaders/opengles30/PerFragmentLighting.vert");
        createVertexShader("TextureShader", "shaders/opengles30/TextureShader.vert");

        // Create fragment shaders.
        createFragmentShader("Triangle", "shaders/opengles30/Triangle.frag");
        createFragmentShader("Triangle2", "shaders/opengles30/Triangle2.frag");
        createFragmentShader("PerFragmentLighting", "shaders/opengles30/PerFragmentLighting.frag");
        createFragmentShader("TextureShader", "shaders/opengles30/TextureShader.frag");

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