package by.vsu.mf.rendering.opengl.shader.uniform;

public abstract class DirectUniformHolder<T> extends BaseUniformHolder {

    protected T value;

    public void setValue(final T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

}
