#version 300 es

uniform mat4 uMVPMatrix;
uniform mat4 uMVMatrix;

in vec4 aVertex;
in vec4 aColor;
in vec3 aNormal;

out vec3 vVertex;
out vec4 vColor;
out vec3 vNormal;

void main() {
    vVertex = vec3(uMVMatrix * aVertex);

    vColor = aColor;

    vNormal = vec3(uMVMatrix * vec4(aNormal, 0.0));

	gl_Position = uMVPMatrix * aVertex;
}
