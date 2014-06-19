package by.vsu.mf.rendering.opengl.shader.uniform;

import by.vsu.mf.rendering.opengl.shader.ShaderInput;

public class FloatUniformHolder extends DirectUniformHolder<Float> {

    @Override
    public void apply(final ShaderInput input) {
        input.setUniform(this.name, this.value);
    }

}
