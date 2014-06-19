package by.vsu.mf.rendering.opengl;

import by.vsu.mf.rendering.object.Geometry;
import by.vsu.mf.rendering.object.VertexAttribute;
import by.vsu.mf.rendering.object.VertexDataModel;
import by.vsu.mf.rendering.object.VertexDataType;
import by.vsu.mf.rendering.opengl.shader.ShaderInput;

public class SimpleOpenGLRenderer extends OpenGLRenderer {

    @Override
    protected void applyGeometry(final Geometry geometry, final ShaderInput input) {
        final VertexDataModel model = geometry.getVertexDataModel();
        this.vertexAttributeAPI.setData(geometry.getData());
        final int dataStride = model.getStride();
        final VertexDataType dataType = model.getType();
        for (final VertexAttribute attribute : model.getAttributes()) {
            final int location = input.getVertexAttributeLocation(attribute.getName());
            this.vertexAttributeAPI.setPointer(attribute, dataType, location, dataStride);
        }

        this.vertexAttributeAPI.reset();
    }

}
