precision mediump float;

uniform vec3 uLightPos;
uniform float uProgress;

varying vec3 vVertex;
varying vec4 vColor;
varying vec3 vNormal;

void main() {
	// Will be used for attenuation.
	float distance = length(uLightPos - vVertex);

    // Get a direction vector from the light source to the vertex.
    vec3 lightVector = normalize(uLightPos - vVertex);

    // Calculate the dot product of the light vector and vertex normal.
    // If the normal and light vector are pointing in the same direction then it will get max illumination.
    float diffuse = max(dot(vNormal, lightVector), 0.1);

    // Add attenuation.
    diffuse = diffuse * (1.0 / (1.0 + (0.25 * distance * distance)));

    // Add ambient lighting.
    diffuse = diffuse + 1.0;

    // To get the final output color, multiply the color by the diffuse lighting value.
    vec4 outColor = vColor;
    outColor.w *= (1.0f - uProgress);
    gl_FragColor = outColor;
}