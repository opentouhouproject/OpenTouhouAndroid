uniform mat4 uMVPMatrix;
uniform mat4 uMVMatrix;
uniform vec3 uLightSource;

attribute vec4 aPosition;
attribute vec4 aColor;
attribute vec3 aNormal;

varying vec4 vColor;

void main() {
    // camera space transformation
    vec3 mvVertex =  vec3(uMVMatrix * aPosition);
    vec3 mvNormal = vec3(uMVMatrix * vec4(aNormal, 0.0));

    // distance from light to vertex
    float distance = length(uLightSource - mvVertex);

    // direction from light to vertex
    vec3 lightVector = normalize(uLightSource - mvVertex);

    // diffusion of light aka the angle the light hits the vertex normal at
    float diffuse = max(dot(mvNormal, lightVector), 0.1);

    // decrease light based on distance from light source
    diffuse = diffuse * (1.0 / (1.0 + (0.01 * distance * distance)));

    // finally adjust the color based on the light
    vColor = aColor * diffuse;

	gl_Position = uMVPMatrix * aPosition;
}
