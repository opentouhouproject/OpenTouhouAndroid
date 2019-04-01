#version 300 es

precision mediump float;

in vec4 vColor;

out vec4 fragmentColor;

void main() {
    fragmentColor = vColor;
}