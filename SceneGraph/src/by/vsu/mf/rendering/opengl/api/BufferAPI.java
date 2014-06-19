package by.vsu.mf.rendering.opengl.api;

import java.nio.Buffer;

import by.vsu.mf.rendering.object.VertexDataType;

// TODO: that would be better to create geometry manager which hides vertex data allocation/loading and uses special
// selectors (e.g. "primitive:cube", "mesh:tower.obj") to specify geometry resources. It would hide VBO management from renderers too.
public interface BufferAPI {

    int createBuffer(final Buffer data, final VertexDataType type);

    void deleteBuffer(final int handle);

}
