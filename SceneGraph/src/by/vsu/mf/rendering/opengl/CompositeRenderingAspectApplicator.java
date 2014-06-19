package by.vsu.mf.rendering.opengl;

import java.util.HashMap;
import java.util.Map;

import by.vsu.mf.rendering.opengl.renderingAspect.RenderingAspect;

public class CompositeRenderingAspectApplicator implements RenderingAspectApplicator<RenderingAspect> {

    private Map<Class<? extends RenderingAspect>, RenderingAspectApplicator<?>> applicators = new HashMap<>();

    public<T extends RenderingAspect> void addApplicator(final Class<T> aspectType,
            final RenderingAspectApplicator<T> applicator) {
        this.applicators.put(aspectType, applicator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void apply(final RenderingAspect aspect) {
        final Class<? extends RenderingAspect> type = aspect.getClass();
        final RenderingAspectApplicator<RenderingAspect> applicator =
                (RenderingAspectApplicator<RenderingAspect>) this.applicators.get(type);
        applicator.apply(aspect);
    }

}
