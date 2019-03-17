package opentouhou.com.opentouhouandroid.graphics.opengl.common.shader;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import opentouhou.com.opentouhouandroid.io.FileManager;

/**
 * Manages all shaders.
 */

public abstract class ShaderManager
{
    //private AssetManager assetManager;

    protected HashMap<String, ShaderProgram> shaderPrograms;

    protected HashMap<String, VertexShader> vertexShaders;

    protected HashMap<String, FragmentShader> fragmentShaders;

    public ShaderManager()
    {
        //this.assetManager = assetManager;

        shaderPrograms = new HashMap<>(8);

        vertexShaders = new HashMap<>(8);

        fragmentShaders = new HashMap<>(8);
    }

    // Retrieve shader program.
    public ShaderProgram getShaderProgram(String name)
    {
        return shaderPrograms.get(name);
    }

    public int getShaderProgramHandle(String name)
    {
        return shaderPrograms.get(name).getHandle();
    }

    // Reads the shader code from file.
    protected String readFile(String filePath, FileManager fileManager) {
       return fileManager.openTextAsset(filePath).toString();
    }

    // Create shaders.
    abstract public void createVertexShader(String name, String filePath, FileManager fileManager);

    abstract public void createFragmentShader(String name, String filePath, FileManager fileManager);

    abstract public void createShaderProgram(String name, String vertexShader, String fragmentShader);
}