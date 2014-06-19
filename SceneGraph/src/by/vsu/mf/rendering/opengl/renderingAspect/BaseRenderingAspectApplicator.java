package by.vsu.mf.rendering.opengl.renderingAspect;

import by.vsu.mf.rendering.opengl.RenderingAspectApplicator;

public abstract class BaseRenderingAspectApplicator<T extends RenderingAspect>
        implements RenderingAspectApplicator<T> {

    @Override
    public void apply(final T aspect) {
        if (aspect.active()) {
            this.applyInternal(aspect);
        }
    }

    protected abstract void applyInternal(final T aspect);

}
