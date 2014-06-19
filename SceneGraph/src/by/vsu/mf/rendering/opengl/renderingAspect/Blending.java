package by.vsu.mf.rendering.opengl.renderingAspect;

public class Blending extends BaseRenderingAspect {

    private boolean enabled;
    private BlendingFactor sourceFactor;
    private BlendingFactor destinationFactor;

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public BlendingFactor getSourceFactor() {
        return this.sourceFactor;
    }

    public void setSourceFactor(final BlendingFactor sourceFactor) {
        this.sourceFactor = sourceFactor;
    }

    public BlendingFactor getDestinationFactor() {
        return this.destinationFactor;
    }

    public void setDestinationFactor(final BlendingFactor destinationFactor) {
        this.destinationFactor = destinationFactor;
    }

    public enum BlendingFactor {
        ONE,
        ONE_MINUS_SRC_ALPHA,
        ONE_MINUS_DST_ALPHA,
        ONE_MINUS_SRC_COLOR,
        ONE_MINUS_DST_COLOR,
        SRC_COLOR,
        DST_COLOR,
        SRC_ALPHA,
        DST_ALPHA
    }

}
