package by.vsu.mf.rendering.opengl.shader.uniform;

import by.vsu.mf.rendering.opengl.shader.ShaderInput;

public interface UniformHolder {

    void apply(final ShaderInput input);

    String getName();

}
