#version 300 es

uniform sampler2D image;
uniform float isHorizontal;

in vec2 textureCoordinate;

out vec4 fragmentColor;

void main() {
    float weight[5] = float[] (0.227027, 0.1945946, 0.1216216, 0.054054, 0.016216);
    //float weight[5] = float[] (0.40, 0.30, 0.20, 0.10, 0.5);

    // Get the size of single texel.
    vec2 tex_offset = 1.0 / vec2(textureSize(image, 0));
    tex_offset = 1.0f * tex_offset;

    // Current fragment's contribution.
    vec3 result = texture(image, textureCoordinate).rgb * weight[0];

    if (isHorizontal < 0.5) {
        for (int i = 1; i < 5; ++i) {
            float iteration = float(i);

            result += texture(image, textureCoordinate + vec2(tex_offset.x * iteration, 0.0)).rgb * weight[i];
            result += texture(image, textureCoordinate - vec2(tex_offset.x * iteration, 0.0)).rgb * weight[i];
        }
    } else {
        for (int i = 1; i < 5; ++i) {
            float iteration = float(i);

            result += texture(image, textureCoordinate + vec2(0.0, tex_offset.y * iteration)).rgb * weight[i];
            result += texture(image, textureCoordinate - vec2(0.0, tex_offset.y * iteration)).rgb * weight[i];
        }
    }

    fragmentColor = vec4(result, 1.0);
}