package by.vsu.mf.simplegles20.core.renderer.opengl.es2;

import android.opengl.GLES20;
import by.vsu.mf.rendering.opengl.api.BlendingAPI;
import by.vsu.mf.rendering.opengl.renderingAspect.Blending.BlendingFactor;

public class GLES2BlendingAPI implements BlendingAPI {

    @Override
    public void setBlendingEnabled(final boolean enabled) {
        if (enabled) {
            GLES20.glEnable(GLES20.GL_BLEND);
        } else {
            GLES20.glDisable(GLES20.GL_BLEND);
        }
    }

    @Override
    public void setBlendingFunction(final BlendingFactor sourceFactor, final BlendingFactor destinationFactor) {
        GLES20.glBlendFunc(GLES2BlendingAPI.translateFactor(sourceFactor),
                GLES2BlendingAPI.translateFactor(destinationFactor));
    }

    private static int translateFactor(final BlendingFactor factor) {
        switch (factor) {
            case ONE:
                return GLES20.GL_ONE;
            case ONE_MINUS_DST_ALPHA:
                return GLES20.GL_ONE_MINUS_DST_ALPHA;
            case ONE_MINUS_SRC_ALPHA:
                return GLES20.GL_ONE_MINUS_SRC_ALPHA;
            case ONE_MINUS_DST_COLOR:
                return GLES20.GL_ONE_MINUS_DST_COLOR;
            case ONE_MINUS_SRC_COLOR:
                return GLES20.GL_ONE_MINUS_SRC_COLOR;
            case DST_COLOR:
                return GLES20.GL_DST_COLOR;
            case SRC_COLOR:
                return GLES20.GL_SRC_COLOR;
            case DST_ALPHA:
                return GLES20.GL_DST_ALPHA;
            case SRC_ALPHA:
                return GLES20.GL_SRC_ALPHA;
            default:
                throw new IllegalArgumentException("Invalid blending factor: " + factor + "!");
        }
    }

}
