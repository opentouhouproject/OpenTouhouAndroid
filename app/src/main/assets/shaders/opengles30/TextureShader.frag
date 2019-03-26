precision mediump float;

uniform vec3 uLightPos;
uniform sampler2D uTexture; // interpolated texture coordinate

varying vec3 vVertex;
varying vec4 vColor;
varying vec3 vNormal;
varying vec2 vTexCoordinate;

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
    diffuse = diffuse + 1.0;

    // To get the final output color, multiply the color by:
    // (1) the diffuse illumination level
    // (2) the texture value
    gl_FragColor = diffuse * vColor * texture2D(uTexture, vTexCoordinate);
}