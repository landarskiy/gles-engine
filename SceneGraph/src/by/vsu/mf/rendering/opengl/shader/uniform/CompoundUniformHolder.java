package by.vsu.mf.rendering.opengl.shader.uniform;

import by.vsu.mf.rendering.opengl.shader.ShaderInput.CompoundUniformType;

public abstract class CompoundUniformHolder<T> extends DirectUniformHolder<T> {

    protected CompoundUniformType type;

    public CompoundUniformType getType() {
        return this.type;
    }

    public void setType(final CompoundUniformType type) {
        this.type = type;
    }

}
