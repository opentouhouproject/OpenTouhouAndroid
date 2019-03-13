package opentouhou.com.opentouhouandroid.graphics.opengl;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.FragmentShader;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.VertexShader;

public interface Linkable
{
    void link();
    void link(int vertexShaderHandle, int fragmentShaderHandle);
    void link(VertexShader vertexShader, FragmentShader fragmentShader);
}
