#version 300 es

precision mediump float;

uniform vec3 uLightPos;

in vec3 vVertex;
in vec4 vColor;
in vec3 vNormal;

out vec4 fragmentColor;

void main() {
	// Will be used for attenuation.
	float distance = length(uLightPos - vVertex);

    // Get a lighting direction vector from the light to the vertex.
    vec3 lightVector = normalize(uLightPos - vVertex);

    // Calculate the dot product of the light vector and vertex normal. If the normal and light vector are
    // pointing in the same direction then it will get max illumination.
    float diffuse = max(dot(vNormal, lightVector), 0.1);

    // Apply the attenuation.
    diffuse = diffuse * (1.0 / (1.0 + (0.25 * distance * distance)));

    // Add ambient light.
    diffuse = diffuse + 0.3;

    // Multiply the color by the diffuse illumination level to get final output color.
    fragmentColor = vColor * diffuse;
}
