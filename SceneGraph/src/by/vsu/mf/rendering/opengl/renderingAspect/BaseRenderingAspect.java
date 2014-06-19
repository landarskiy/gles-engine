package by.vsu.mf.rendering.opengl.renderingAspect;


public abstract class BaseRenderingAspect implements RenderingAspect {

    private boolean active;

    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public boolean active() {
        return this.active;
    }

}
