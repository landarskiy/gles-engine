package by.vsu.mf.rendering.opengl.api;

import java.nio.Buffer;

import by.vsu.mf.rendering.object.VertexAttribute;
import by.vsu.mf.rendering.object.VertexDataType;

public interface VertexAttributeAPI {

    void reset();

    void setData(final Buffer data);

    void setData(final int handle);

    void setPointer(final VertexAttribute attribute, final VertexDataType type, final int location, final int stride);

}
