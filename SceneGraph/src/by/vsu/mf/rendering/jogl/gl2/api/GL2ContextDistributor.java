package by.vsu.mf.rendering.jogl.gl2.api;

import java.util.ArrayList;
import java.util.Collection;

import javax.media.opengl.GL2;

public class GL2ContextDistributor {

    private Collection<GL2API> apis = new ArrayList<>();

    public void registerAPI(final GL2API api) {
        this.apis.add(api);
    }

    public void distribute(final GL2 gl) {
        for (final GL2API api : this.apis) {
            api.setGL(gl);
        }
    }

}
