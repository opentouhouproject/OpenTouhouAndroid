uniform mat4 uMVPMatrix;
uniform mat4 uMVMatrix;

attribute vec4 aVertex;
attribute vec4 aColor;
attribute vec3 aNormal;

varying vec3 vVertex;
varying vec4 vColor;
varying vec3 vNormal;

void main() {
    vVertex = vec3(uMVMatrix * aVertex);

    vColor = aColor;

    vNormal = vec3(uMVMatrix * vec4(aNormal, 0.0));

	gl_Position = uMVPMatrix * aVertex;
}
