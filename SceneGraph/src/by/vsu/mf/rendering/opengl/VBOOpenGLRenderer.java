package by.vsu.mf.rendering.opengl;

import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

import by.vsu.mf.rendering.object.Geometry;
import by.vsu.mf.rendering.object.VertexAttribute;
import by.vsu.mf.rendering.object.VertexDataModel;
import by.vsu.mf.rendering.object.VertexDataType;
import by.vsu.mf.rendering.opengl.api.BufferAPI;
import by.vsu.mf.rendering.opengl.shader.ShaderInput;

public class VBOOpenGLRenderer extends OpenGLRenderer {

    protected BufferAPI bufferAPI;
    protected Map<Buffer, Integer> handles = new HashMap<>();

    public void setBufferAPI(final BufferAPI api) {
        this.bufferAPI = api;
    }

    @Override
    protected void applyGeometry(final Geometry geometry, final ShaderInput input) {
        final Buffer data = geometry.getData();
        Integer handle = this.handles.get(geometry.getData());

        final VertexDataModel model = geometry.getVertexDataModel();
        final VertexDataType dataType = model.getType();
        if (handle == null) {
            handle = this.bufferAPI.createBuffer(data, dataType);
            this.handles.put(data, handle);
        }

        this.vertexAttributeAPI.setData(handle);
        final int dataStride = model.getStride();
        for (final VertexAttribute attribute : model.getAttributes()) {
            final int location = input.getVertexAttributeLocation(attribute.getName());
            this.vertexAttributeAPI.setPointer(attribute, dataType, location, dataStride);
        }
    }

}
