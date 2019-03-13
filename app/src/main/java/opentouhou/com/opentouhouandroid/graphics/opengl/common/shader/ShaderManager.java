package opentouhou.com.opentouhouandroid.graphics.opengl.common.shader;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Manages all shaders.
 */

public abstract class ShaderManager
{
    private AssetManager assetManager;

    protected HashMap<String, ShaderProgram> shaderPrograms;

    protected HashMap<String, VertexShader> vertexShaders;

    protected HashMap<String, FragmentShader> fragmentShaders;

    public ShaderManager(AssetManager assetManager)
    {
        this.assetManager = assetManager;

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
    protected String readFile(String filePath)
    {
        BufferedReader reader = null;
        StringBuffer buf = new StringBuffer();

        try
        {
            InputStream stream = assetManager.open(filePath);

            if (stream != null)
            {
                reader = new BufferedReader(new InputStreamReader(stream));

                String line;
                while ((line = reader.readLine()) != null)
                {
                    buf.append(line + "\n");
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error reading shader from file.");
        }
        finally {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException e) {
                throw new RuntimeException("Could not close BufferedReader.");
            }
        }

        return buf.toString();
    }

    // Create shaders.
    abstract public void createVertexShader(String name, String filePath);

    abstract public void createFragmentShader(String name, String filePath);

    abstract public void createShaderProgram(String name, String vertexShader, String fragmentShader);
}