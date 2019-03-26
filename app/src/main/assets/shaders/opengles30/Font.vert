uniform mat4 uMVPMatrix;
uniform mat4 uMVMatrix;
uniform vec4 uColor;

attribute vec4 aVertex;
attribute vec4 aColor;
attribute vec3 aNormal;
attribute vec2 aTexCoordinate;

varying vec3 vVertex;
varying vec4 vColor;
varying vec3 vNormal;
varying vec2 vTexCoordinate;

void main() {
    vVertex = vec3(uMVMatrix * aVertex);

    vColor = aColor;

    vNormal = vec3(uMVMatrix * vec4(aNormal, 0.0));

    vTexCoordinate = aTexCoordinate;

	gl_Position = uMVPMatrix * aVertex;
}