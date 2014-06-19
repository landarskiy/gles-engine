package by.vsu.mf.rendering.jogl.gl2.api;

import java.nio.Buffer;
import java.util.HashSet;
import java.util.Set;

import javax.media.opengl.GL;

import by.vsu.mf.rendering.object.VertexAttribute;
import by.vsu.mf.rendering.object.VertexDataType;
import by.vsu.mf.rendering.opengl.api.VertexAttributeAPI;

public class GL2VertexAttributeAPI extends GL2API implements VertexAttributeAPI {

    private State state = GL2VertexAttributeAPI.State.RESET;
    private Buffer buffer;
    private Set<Integer> activeAttributeLocations = new HashSet<>();

    @Override
    public void setData(final Buffer buffer) {
        this.reset();
        this.buffer = buffer;
        this.state = GL2VertexAttributeAPI.State.DIRECT_BUFFER;
    }

    @Override
    public void setData(final int handle) {
        this.reset();
        this.gl.glBindBuffer(GL.GL_ARRAY_BUFFER, handle);
        this.state = GL2VertexAttributeAPI.State.VBO;
    }

    @Override
    public void setPointer(final VertexAttribute attribute, final VertexDataType type, final int location,
            final int stride) {
        if (this.state == State.DIRECT_BUFFER) {
            this.buffer.position(attribute.getOffset());
            this.gl.glVertexAttribPointer(location, attribute.getDimension(),
                    GL2VertexAttributeAPI.getGLType(type), false, stride, this.buffer);
        } else if (this.state == State.VBO) {
            this.gl.glVertexAttribPointer(location, attribute.getDimension(), GL2VertexAttributeAPI.getGLType(type),
                    false, stride, attribute.getOffset());
        } else {
            throw new IllegalStateException("Attempt to specify vertex data when the data source is undefined!");
        }

        this.activeAttributeLocations.add(location);

        this.gl.glEnableVertexAttribArray(location);
    }

    @Override
    public void reset() {
        if (this.state == GL2VertexAttributeAPI.State.DIRECT_BUFFER) {
            this.buffer = null;
        } else if (this.state == GL2VertexAttributeAPI.State.VBO) {
            this.gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
        }

        for (final int location : this.activeAttributeLocations) {
            this.gl.glDisableVertexAttribArray(location);
        }
        this.activeAttributeLocations.clear();

        this.state = GL2VertexAttributeAPI.State.RESET;
    }

    private static int getGLType(final VertexDataType type) {
        switch (type) {
            case FLOAT:
                return GL.GL_FLOAT;
            case SHORT:
                return GL.GL_SHORT;
            default:
                throw new IllegalArgumentException("Invalid vertex attribute type!");
        }
    }

    private enum State {
        RESET, VBO, DIRECT_BUFFER
    }

}
