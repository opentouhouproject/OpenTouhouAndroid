#version 300 es

uniform mat4 uMVPMatrix;

in vec4 aVertex;
in vec4 aColor;

out vec4 vColor;

void main() {
    // Set the color value.
    vColor = aColor;

    // Set the position after projection.
	gl_Position = uMVPMatrix * aVertex;
}