#version 300 es

precision mediump float;

uniform vec3 uLightPos;
uniform vec4 uColor;
uniform sampler2D uTexture; // interpolated texture coordinate

in vec3 vVertex;
in vec4 vColor;
in vec3 vNormal;
in vec2 vTexCoordinate;

out vec4 fragmentColor;

void main() {
	// Will be used for attenuation.
	float distance = length(uLightPos - vVertex);

    // Get a lighting direction vector from the light to the vertex.
    vec3 lightVector = normalize(uLightPos - vVertex);

    // Calculate the dot product of the light vector and vertex normal. If the normal and light vector are
    // pointing in the same direction then it will get max illumination.
    float diffuse = max(dot(vNormal, lightVector), 0.1);

    // Add attenuation.
    diffuse = diffuse * (1.0 / (1.0 + (0.25 * distance * distance)));

    // Add ambient lighting.
    diffuse = diffuse + 0.2;

    // To get the final output color, multiply the color by:
    // (1) the diffuse illumination level
    // (2) the texture value
    //vec4 tex = texture2D(uTexture, vTexCoordinate);

    // Luma conversion.
    //gl_FragColor = dot(vec3(tex.x, tex.y, tex.z), vec3(0.299, 0.587, 0.114)) * tex;
    fragmentColor = texture(uTexture, vTexCoordinate);
}