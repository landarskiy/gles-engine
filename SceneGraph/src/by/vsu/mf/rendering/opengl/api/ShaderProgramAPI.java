package by.vsu.mf.rendering.opengl.api;

import by.vsu.mf.rendering.opengl.shader.ShaderInput;

public interface ShaderProgramAPI extends ShaderInput {

    void activate();

    int getHandle();

}
