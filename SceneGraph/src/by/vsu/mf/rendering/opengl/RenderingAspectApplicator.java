package by.vsu.mf.rendering.opengl;

import by.vsu.mf.rendering.opengl.renderingAspect.RenderingAspect;

public interface RenderingAspectApplicator<T extends RenderingAspect> {

    void apply(final T aspect);

}
