#version 300 es

uniform mat4 uMVPMatrix;
//uniform mat4 uMVMatrix;

in vec2 aVertex;
in vec2 aTexCoordinate;

out vec2 textureCoordinate;

void main() {
    textureCoordinate = aTexCoordinate;

    gl_Position =  uMVPMatrix * vec4(aVertex.x, aVertex.y, 0.0, 1.0);
}