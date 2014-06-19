package by.vsu.mf.rendering.jogl.gl2.api;

import javax.media.opengl.GL2;

public abstract class GL2API {

    protected GL2 gl;

    public void setGL(final GL2 gl) {
        this.gl = gl;
    }

}
