package by.vsu.mf.simplegles20.core.renderer.opengl.es2;

import java.nio.Buffer;
import java.util.HashSet;
import java.util.Set;

import android.opengl.GLES20;
import by.vsu.mf.rendering.object.VertexAttribute;
import by.vsu.mf.rendering.object.VertexDataType;
import by.vsu.mf.rendering.opengl.api.VertexAttributeAPI;

public class GLES2VertexAttributeAPI implements VertexAttributeAPI {

    private State state = GLES2VertexAttributeAPI.State.RESET;
    private Buffer buffer;
    private Set<Integer> activeAttributeLocations = new HashSet<Integer>();

    @Override
    public void setData(final Buffer buffer) {
        this.reset();
        this.buffer = buffer;
        this.state = GLES2VertexAttributeAPI.State.DIRECT_BUFFER;
    }

    @Override
    public void setData(final int handle) {
        this.reset();
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, handle);
        this.state = GLES2VertexAttributeAPI.State.VBO;
    }

    @Override
    public void setPointer(final VertexAttribute attribute, final VertexDataType type, final int location,
            final int stride) {
        if (this.state == State.DIRECT_BUFFER) {
            this.buffer.position(attribute.getOffset());
            GLES20.glVertexAttribPointer(location, attribute.getDimension(),
                    GLES2VertexAttributeAPI.getGLType(type), false, stride, this.buffer);
        } else if (this.state == State.VBO) {
            GLES20.glVertexAttribPointer(location, attribute.getDimension(), GLES2VertexAttributeAPI.getGLType(type),
                    false, stride, attribute.getOffset());
        } else {
            throw new IllegalStateException("Attempt to specify vertex data when the data source is undefined!");
        }

        this.activeAttributeLocations.add(location);

        GLES20.glEnableVertexAttribArray(location);
    }

    @Override
    public void reset() {
        if (this.state == GLES2VertexAttributeAPI.State.DIRECT_BUFFER) {
            this.buffer = null;
        } else if (this.state == GLES2VertexAttributeAPI.State.VBO) {
            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        }

        for (final int location : this.activeAttributeLocations) {
            GLES20.glDisableVertexAttribArray(location);
        }
        this.activeAttributeLocations.clear();

        this.state = GLES2VertexAttributeAPI.State.RESET;
    }

    private static int getGLType(final VertexDataType type) {
        switch (type) {
            case FLOAT:
                return GLES20.GL_FLOAT;
            case SHORT:
                return GLES20.GL_SHORT;
            default:
                throw new IllegalArgumentException("Invalid vertex attribute type!");
        }
    }

    private enum State {
        RESET, VBO, DIRECT_BUFFER
    }

}
