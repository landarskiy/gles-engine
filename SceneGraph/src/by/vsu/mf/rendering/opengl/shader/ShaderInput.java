package by.vsu.mf.rendering.opengl.shader;

public interface ShaderInput {

    int getVertexAttributeLocation(final String name);

    boolean hasUniform(final String name);

    boolean hasVertexAttribute(final String name);

    void setUniform(final String name, final int value);

    void setUniform(final String name, final float value);

    void setUniform(final String name, final CompoundUniformType type, final int[] value);

    void setUniform(final String name, final CompoundUniformType type, final float[] value);

    // FIXME: remove
    @Deprecated
    int getUniformLocation(final String name);

    public enum CompoundUniformType {
        VECTOR3, VECTOR4, MATRIX3, MATRIX4
    }

}
