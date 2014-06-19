package by.vsu.mf.rendering.opengl.shader.uniform;

import by.vsu.mf.rendering.opengl.shader.ShaderInput;

public class IntMatrixUniformHolder extends CompoundUniformHolder<int[]> {

    @Override
    public void apply(final ShaderInput input) {
        input.setUniform(this.name, this.type, this.value);
    }

}
