package com.scarlet.graphics.opengl.shader;

import com.scarlet.io.FileManager;

import java.util.concurrent.ConcurrentHashMap;

/*
 * Manages all shaders.
 */
public abstract class ShaderManager {
    private static int INITIAL_CAPACITY = 16;

    protected ConcurrentHashMap<String, ShaderProgram> shaderPrograms;
    protected ConcurrentHashMap<String, VertexShader> vertexShaders;
    protected ConcurrentHashMap<String, FragmentShader> fragmentShaders;

    /*
     * Constructor(s).
     */
    protected ShaderManager() {
        shaderPrograms = new ConcurrentHashMap<>(INITIAL_CAPACITY);
        vertexShaders = new ConcurrentHashMap<>(INITIAL_CAPACITY);
        fragmentShaders = new ConcurrentHashMap<>(INITIAL_CAPACITY);
    }

    /*
     * Getter(s).
     */
    public ShaderProgram getShaderProgram(String programName) {
        return shaderPrograms.get(programName);
    }

    public int getShaderProgramHandle(String programName) {
        ShaderProgram program = shaderPrograms.get(programName);

        if (program == null) {
            return -1;
        }

        return program.getHandle();
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