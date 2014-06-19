package by.vsu.mf.rendering.opengl.shader.uniform;

import by.vsu.mf.rendering.opengl.shader.ShaderInput;
import by.vsu.mf.rendering.opengl.shader.ShaderInput.CompoundUniformType;

public class ViewUniformHolder extends ViewSpaceUniformHolder {

    @Override
    public void apply(final ShaderInput input) {
        input.setUniform(this.name, CompoundUniformType.MATRIX4, this.view.getViewMatrix());
    }

}
