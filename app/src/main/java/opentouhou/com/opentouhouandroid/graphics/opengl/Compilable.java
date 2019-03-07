package opentouhou.com.opentouhouandroid.graphics.opengl;

public interface Compilable
{
    void compile();

    void compile(String code);

    void compile(StringBuffer codeBuffer);
}
