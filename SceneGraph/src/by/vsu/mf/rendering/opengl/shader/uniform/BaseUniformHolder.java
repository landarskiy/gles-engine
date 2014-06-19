package by.vsu.mf.rendering.opengl.shader.uniform;

public abstract class BaseUniformHolder implements UniformHolder {

    protected String name;

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
