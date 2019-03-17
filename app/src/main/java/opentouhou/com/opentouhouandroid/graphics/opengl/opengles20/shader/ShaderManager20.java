package opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.shader;

import android.content.res.AssetManager;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.ShaderManager;
import opentouhou.com.opentouhouandroid.io.FileManager;

/**
 * Shader Manager implementation for OpenGL ES 2.0
 */

public class ShaderManager20 extends ShaderManager
{
    public ShaderManager20()
    {
        //super(assetManager);
    }

    // Create shaders.
    public void createVertexShader(String name, String filePath, FileManager fileManager)
    {
        VertexShader20 vertexShader20 = new VertexShader20(name);
        vertexShader20.compile(readFile(filePath, fileManager));

        vertexShaders.put(name, vertexShader20);
    }

    public void createFragmentShader(String name, String filePath, FileManager fileManager)
    {
        FragmentShader20 fragmentShader20 = new FragmentShader20(name);
        fragmentShader20.compile(readFile(filePath, fileManager));

        fragmentShaders.put(name, fragmentShader20);
    }

    public void createShaderProgram(String name, String vertexShader, String fragmentShader)
    {
        ShaderProgram20 shaderProgram20 = new ShaderProgram20(name);
        shaderProgram20.link(vertexShaders.get(vertexShader), fragmentShaders.get(fragmentShader));

        shaderPrograms.put(name, shaderProgram20);
    }
}