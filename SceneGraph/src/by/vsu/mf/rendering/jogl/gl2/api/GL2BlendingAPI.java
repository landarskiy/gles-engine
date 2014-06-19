package by.vsu.mf.rendering.jogl.gl2.api;

import javax.media.opengl.GL;

import by.vsu.mf.rendering.opengl.api.BlendingAPI;
import by.vsu.mf.rendering.opengl.renderingAspect.Blending.BlendingFactor;

public class GL2BlendingAPI extends GL2API implements BlendingAPI {

    @Override
    public void setBlendingEnabled(final boolean enabled) {
        if (enabled) {
            this.gl.glEnable(GL.GL_BLEND);
        } else {
            this.gl.glDisable(GL.GL_BLEND);
        }
    }

    @Override
    public void setBlendingFunction(final BlendingFactor sourceFactor, final BlendingFactor destinationFactor) {
        this.gl.glBlendFunc(GL2BlendingAPI.translateFactor(sourceFactor),
                GL2BlendingAPI.translateFactor(destinationFactor));
    }

    private static int translateFactor(final BlendingFactor factor) {
        switch (factor) {
            case ONE:
                return GL.GL_ONE;
            case ONE_MINUS_DST_ALPHA:
                return GL.GL_ONE_MINUS_DST_ALPHA;
            case ONE_MINUS_SRC_ALPHA:
                return GL.GL_ONE_MINUS_SRC_ALPHA;
            case ONE_MINUS_DST_COLOR:
                return GL.GL_ONE_MINUS_DST_COLOR;
            case ONE_MINUS_SRC_COLOR:
                return GL.GL_ONE_MINUS_SRC_COLOR;
            case DST_COLOR:
                return GL.GL_DST_COLOR;
            case SRC_COLOR:
                return GL.GL_SRC_COLOR;
            case DST_ALPHA:
                return GL.GL_DST_ALPHA;
            case SRC_ALPHA:
                return GL.GL_SRC_ALPHA;
            default:
                throw new IllegalArgumentException("Invalid blending factor: " + factor + "!");
        }
    }

}
