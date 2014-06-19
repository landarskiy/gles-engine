package by.vsu.mf.rendering.opengl.api;

import by.vsu.mf.rendering.opengl.renderingAspect.Blending.BlendingFactor;

public interface BlendingAPI {

    void setBlendingEnabled(final boolean enabled);

    void setBlendingFunction(final BlendingFactor sourceFactor, final BlendingFactor destinationFactor);

}
