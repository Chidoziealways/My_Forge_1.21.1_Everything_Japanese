#version 150

uniform sampler2D DiffuseSampler;
uniform vec4 ColorModulator;

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec4 base = texture(DiffuseSampler, texCoord);
    vec4 tint = ColorModulator; // injected by pipeline JSON
    fragColor = base * tint;
}
