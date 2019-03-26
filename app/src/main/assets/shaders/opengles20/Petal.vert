uniform mat4 uMVPMatrix;
uniform mat4 uMVMatrix;
uniform float uProgress;

attribute vec4 aVertex;
attribute vec4 aColor;
attribute vec3 aNormal;

varying vec3 vVertex;
varying vec4 vColor;
varying vec3 vNormal;

void main() {
    // Set the position vector before projection.
    vVertex = vec3(uMVMatrix * aVertex);

    // Set the color value.
    vColor = aColor;

    // Set the normal vector.
    vNormal = vec3(uMVMatrix * vec4(aNormal, 0.0));

    // Set the position after projection.
	gl_Position = uMVPMatrix * aVertex;
}