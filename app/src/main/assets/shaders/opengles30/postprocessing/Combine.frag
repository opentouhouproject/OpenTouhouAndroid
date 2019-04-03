#version 300 es

uniform sampler2D scene;
uniform sampler2D bloomBlur;
uniform float exposure;

in vec2 textureCoordinate;

out vec4 fragmentColor;

void main() {
    const float gamma = 2.2;
    vec3 sceneColor = texture(scene, textureCoordinate).rgb;
    vec3 bloomColor = texture(bloomBlur, textureCoordinate).rgb;

    vec3 result;

    // additive blending
    result = sceneColor + bloomColor;


    //result = sceneColor;
    //result = bloomColor;

    // tone mapping
    //result = vec3(1.0) - exp(-result * 1.0);

    // also gamma correct while we're at it
    //result = pow(result, vec3(1.0 / gamma));

    fragmentColor = vec4(result, 1.0);
}