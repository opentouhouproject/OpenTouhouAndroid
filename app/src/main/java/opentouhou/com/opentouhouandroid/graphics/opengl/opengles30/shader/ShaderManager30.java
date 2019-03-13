package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.shader;

import android.content.res.AssetManager;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.ShaderManager;

/**
 * Shader Manager implementation for OpenGL ES 3.0
 */

public class ShaderManager30 extends ShaderManager
{
    public ShaderManager30(AssetManager assetManager)
    {
        super(assetManager);
    }

    // Create shaders.
    public void createVertexShader(String name, String filePath)
    {
        VertexShader30 vertexShader30 = new VertexShader30(name);
        vertexShader30.compile(readFile(filePath));

        vertexShaders.put(name, vertexShader30);
    }

    public void createFragmentShader(String name, String filePath)
    {
        FragmentShader30 fragmentShader30 = new FragmentShader30(name);
        fragmentShader30.compile(readFile(filePath));

        fragmentShaders.put(name, fragmentShader30);
    }

    public void createShaderProgram(String name, String vertexShader, String fragmentShader)
    {
        ShaderProgram30 shaderProgram30 = new ShaderProgram30(name);
        shaderProgram30.link(vertexShaders.get(vertexShader), fragmentShaders.get(fragmentShader));

        shaderPrograms.put(name, shaderProgram30);
    }
}