package opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.shader;

import com.scarlet.io.FileManager;

import com.scarlet.graphics.opengl.shader.ShaderManager;

/**
 * Shader Manager implementation for OpenGL ES 2.0
 */

public class ShaderManager20 extends ShaderManager {
    public ShaderManager20() { }

    // Create shaders.
    public void createVertexShader(String name, String filePath, FileManager fileManager) {
        if (vertexShaders.containsKey(name)) {
            return;
        }

        VertexShader20 vertexShader20 = new VertexShader20(name);
        vertexShader20.compile(readFile(filePath, fileManager));

        vertexShaders.put(name, vertexShader20);
    }

    public void createFragmentShader(String name, String filePath, FileManager fileManager) {
        if (fragmentShaders.containsKey(name)) {
            return;
        }

        FragmentShader20 fragmentShader20 = new FragmentShader20(name);
        fragmentShader20.compile(readFile(filePath, fileManager));

        fragmentShaders.put(name, fragmentShader20);
    }

    public void createShaderProgram(String name, String vertexShader, String fragmentShader) {
        if (shaderPrograms.containsKey(name)) {
            return;
        }

        ShaderProgram20 shaderProgram20 = new ShaderProgram20(name);
        shaderProgram20.link(vertexShaders.get(vertexShader), fragmentShaders.get(fragmentShader));

        shaderPrograms.put(name, shaderProgram20);
    }
}