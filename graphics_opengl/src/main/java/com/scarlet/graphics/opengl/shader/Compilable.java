package com.scarlet.graphics.opengl.shader;

public interface Compilable {
    void compile();
    void compile(String code);
    void compile(StringBuffer codeBuffer);
}