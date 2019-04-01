#version 300 es

precision mediump float;

in vec4 vColor;

layout (location = 0) out vec4 fragmentColor;
layout (location = 1) out vec4 brightColor;

void main() {


    float brightness = dot(vColor.rgb, vec3(0.2126, 0.7152, 0.0722));
    if (brightness > 0.8) {
        fragmentColor = vec4(0.0, 0.0, 0.0, 0.0);
        brightColor = vec4(vColor.rgb, 1.0);
    } else {
        fragmentColor = vColor;
        brightColor = vec4(0.0, 0.0, 0.0, 0.0);
    }
}