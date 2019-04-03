package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.shader;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.ShaderManager;
import opentouhou.com.opentouhouandroid.io.FileManager;

/**
 * Shader Manager implementation for OpenGL ES 3.0
 */

public class ShaderManager30 extends ShaderManager {
    public ShaderManager30() { }

    // Create shaders.
    public void createVertexShader(String name, String filePath, FileManager fileManager) {
        if (vertexShaders.containsKey(name)) {
            return;
        }

        VertexShader30 vertexShader30 = new VertexShader30(name);
        vertexShader30.compile(readFile(filePath, fileManager));

        vertexShaders.put(name, vertexShader30);
    }

    public void createFragmentShader(String name, String filePath, FileManager fileManager) {
        if (fragmentShaders.containsKey(name)) {
            return;
        }

        FragmentShader30 fragmentShader30 = new FragmentShader30(name);
        fragmentShader30.compile(readFile(filePath, fileManager));

        fragmentShaders.put(name, fragmentShader30);
    }

    public void createShaderProgram(String name, String vertexShader, String fragmentShader) {
        if (shaderPrograms.containsKey(name)) {
            return;
        }

        ShaderProgram30 shaderProgram30 = new ShaderProgram30(name);
        shaderProgram30.link(vertexShaders.get(vertexShader), fragmentShaders.get(fragmentShader));

        shaderPrograms.put(name, shaderProgram30);
    }
}