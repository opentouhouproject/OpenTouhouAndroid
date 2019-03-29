uniform mat4 uMVPMatrix;
uniform mat4 uMVMatrix;

attribute vec2 aVertex;
attribute vec2 aTexCoordinate;

varying  vec2 vTexCoords;

void main() {
    vTexCoords = aTexCoordinate;

    gl_Position =  uMVPMatrix * vec4(aVertex.x, aVertex.y, 0.0, 1.0);
}