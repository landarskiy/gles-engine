package by.vsu.mf.rendering.opengl.renderingAspect;

import by.vsu.mf.rendering.opengl.api.BlendingAPI;

public class BlendingApplicator extends BaseRenderingAspectApplicator<Blending> {

    private BlendingAPI blendingAPI;

    public void setBlendingAPI(final BlendingAPI api) {
        this.blendingAPI = api;
    }

    @Override
    protected void applyInternal(final Blending aspect) {
        this.blendingAPI.setBlendingEnabled(aspect.isEnabled());
        this.blendingAPI.setBlendingFunction(aspect.getSourceFactor(), aspect.getDestinationFactor());
    }

}
