package com.scarlet.graphics.opengl.shader;

import com.scarlet.io.FileManager;

import java.util.HashMap;

/*
 * Manages all shaders.
 */

public abstract class ShaderManager {
    private static int INITIAL_CAPACITY = 16;

    protected HashMap<String, ShaderProgram> shaderPrograms;
    protected HashMap<String, VertexShader> vertexShaders;
    protected HashMap<String, FragmentShader> fragmentShaders;

    /*
     * Constructor(s).
     */
    public ShaderManager() {
        shaderPrograms = new HashMap<>(INITIAL_CAPACITY);
        vertexShaders = new HashMap<>(INITIAL_CAPACITY);
        fragmentShaders = new HashMap<>(INITIAL_CAPACITY);
    }

    /*
     * Getter(s).
     */
    public ShaderProgram getShaderProgram(String programName) {
        return shaderPrograms.get(programName);
    }

    public int getShaderProgramHandle(String programName) {
        return shaderPrograms.get(programName).getHandle();
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