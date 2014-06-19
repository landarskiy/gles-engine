package by.vsu.mf.rendering.opengl.renderingAspect;

import by.vsu.mf.rendering.opengl.api.FlagAPI;
import by.vsu.mf.rendering.opengl.renderingAspect.Flag.FlagType;

public class FlagApplicator extends BaseRenderingAspectApplicator<Flag> {

    private FlagAPI flagAPI;

    public void setFlagAPI(final FlagAPI api) {
        this.flagAPI = api;
    }

    @Override
    protected void applyInternal(final Flag aspect) {
        final FlagType type = aspect.getType();
        switch (type) {
            case DEPTH_TEST:
                this.flagAPI.setDepthTestEnabled(aspect.getFlag());
                break;
            default:
                throw new IllegalArgumentException("Invalid flag type: " + type + "!");
        }
    }

}
