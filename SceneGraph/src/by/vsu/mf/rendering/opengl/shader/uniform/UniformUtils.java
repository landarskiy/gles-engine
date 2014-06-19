package by.vsu.mf.rendering.opengl.shader.uniform;

import by.vsu.mf.rendering.ViewSpace;
import by.vsu.mf.rendering.opengl.shader.ShaderInput.CompoundUniformType;

public final class UniformUtils {

    private UniformUtils() {
    }

    public static UniformHolder getHolder(final String name, final int value) {
        final IntUniformHolder holder = new IntUniformHolder();
        holder.setName(name);
        holder.setValue(value);

        return holder;
    }

    public static UniformHolder getHolder(final String name, final float value) {
        final FloatUniformHolder holder = new FloatUniformHolder();
        holder.setName(name);
        holder.setValue(value);

        return holder;
    }

    public static UniformHolder getMatrix3Holder(final String name, final int[] value) {
        return UniformUtils.getCompoundHolder(name, CompoundUniformType.MATRIX3, value);
    }

    public static UniformHolder getMatrix4Holder(final String name, final int[] value) {
        return UniformUtils.getCompoundHolder(name, CompoundUniformType.MATRIX4, value);
    }

    public static UniformHolder getVector3Holder(final String name, final int[] value) {
        return UniformUtils.getCompoundHolder(name, CompoundUniformType.VECTOR3, value);
    }

    public static UniformHolder getVector4Holder(final String name, final int[] value) {
        return UniformUtils.getCompoundHolder(name, CompoundUniformType.VECTOR3, value);
    }

    public static UniformHolder getMatrix3Holder(final String name, final float[] value) {
        return UniformUtils.getCompoundHolder(name, CompoundUniformType.MATRIX3, value);
    }

    public static UniformHolder getMatrix4Holder(final String name, final float[] value) {
        return UniformUtils.getCompoundHolder(name, CompoundUniformType.MATRIX4, value);
    }

    public static UniformHolder getVector3Holder(final String name, final float[] value) {
        return UniformUtils.getCompoundHolder(name, CompoundUniformType.VECTOR3, value);
    }

    public static UniformHolder getVector4Holder(final String name, final float[] value) {
        return UniformUtils.getCompoundHolder(name, CompoundUniformType.VECTOR3, value);
    }

    public static UniformHolder getProjectionHolder(final String name, final ViewSpace space) {
        final ProjectionUniformHolder holder = new ProjectionUniformHolder();
        holder.setName(name);
        holder.setView(space);

        return holder;
    }

    public static UniformHolder getViewHolder(final String name, final ViewSpace space) {
        final ViewUniformHolder holder = new ViewUniformHolder();
        holder.setName(name);
        holder.setView(space);

        return holder;
    }

    private static UniformHolder getCompoundHolder(final String name, final CompoundUniformType type,
            final int[] value) {
        final IntMatrixUniformHolder holder = new IntMatrixUniformHolder();
        holder.setName(name);
        holder.setType(type);
        holder.setValue(value);

        return holder;
    }

    private static UniformHolder getCompoundHolder(final String name, final CompoundUniformType type,
            final float[] value) {
        final FloatMatrixUniformHolder holder = new FloatMatrixUniformHolder();
        holder.setName(name);
        holder.setType(type);
        holder.setValue(value);

        return holder;
    }

}
