package com.scarlet.graphics.opengl.shader;

public interface Linkable {
    void link();
    void link(int vertexShaderHandle, int fragmentShaderHandle);
    void link(VertexShader vertexShader, FragmentShader fragmentShader);
}
