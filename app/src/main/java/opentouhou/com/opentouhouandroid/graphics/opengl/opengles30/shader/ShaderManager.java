package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.shader;

import android.content.res.AssetManager;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractShaderManager;

/**
 * Shader Manager implementation for OpenGL ES 3.0
 */

public class ShaderManager extends AbstractShaderManager
{
    public ShaderManager(AssetManager assetManager)
    {
        super(assetManager);
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