package by.vsu.mf.rendering.jogl.gl2.api;

import javax.media.opengl.GL;

import by.vsu.mf.rendering.opengl.api.FlagAPI;

public class GL2FlagAPI extends GL2API implements FlagAPI {

    @Override
    public void setDepthTestEnabled(final boolean enabled) {
        if (enabled) {
            this.gl.glDisable(GL.GL_DEPTH_TEST);
        } else {
            this.gl.glEnable(GL.GL_DEPTH_TEST);
        }
    }

}
