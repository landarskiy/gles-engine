package by.vsu.mf.simplegles20.core.renderer.opengl.es2;

import android.opengl.GLES20;
import by.vsu.mf.rendering.opengl.api.FlagAPI;

public class GLES2FlagAPI implements FlagAPI {

    @Override
    public void setDepthTestEnabled(final boolean enabled) {
        if (enabled) {
        	GLES20.glDisable(GLES20.GL_DEPTH_TEST);
        } else {
        	GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        }
    }

}
