#version 300 es

uniform mat4 uMVPMatrix;
uniform mat4 uMVMatrix;
uniform float uProgress;

in vec4 aVertex;
in vec4 aColor;
in vec3 aNormal;

out vec3 vVertex;
out vec4 vColor;
out vec3 vNormal;

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