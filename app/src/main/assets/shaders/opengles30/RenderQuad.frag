uniform sampler2D uTexture;

varying vec2 vTexCoords;

void main() {
    gl_FragColor = texture2D(uTexture, vTexCoords);

    //gl_FragColor = vec4(vec3(1.0 - texture2D(uTexture, vTexCoords)), 1.0);
}