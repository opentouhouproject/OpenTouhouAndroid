package opentouhou.com.opentouhouandroid.graphics.opengl;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractFragmentShader;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractVertexShader;

public interface Linkable
{
    void link();
    void link(int vertexShaderHandle, int fragmentShaderHandle);
    void link(AbstractVertexShader vertexShader, AbstractFragmentShader fragmentShader);
}
