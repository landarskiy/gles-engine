package by.vsu.mf.rendering.object;

import java.nio.Buffer;
import java.nio.ShortBuffer;

public class Geometry {

    private Buffer data;
    private GeometryPrimitiveType primitiveType;
    // An explicitly set primitive count is needed for the case of shared buffers.
    private int vertexCount;
    private VertexDataModel vertexDataModel;

    private ShortBuffer indexes;

    public void setIndexes(final ShortBuffer indexes) {
        this.indexes = indexes;
    }

    public ShortBuffer getIndexes() {
        return this.indexes;
    }

    public boolean usesIndexes() {
        return (this.indexes != null);
    }

    public GeometryPrimitiveType getPrimitiveType() {
        return this.primitiveType;
    }

    public void setPrimitiveType(final GeometryPrimitiveType primitiveType) {
        this.primitiveType = primitiveType;
    }

    public Buffer getData() {
        return this.data;
    }

    public void setData(final Buffer data) {
        this.data = data;
    }

    public int getVertexCount() {
        return this.vertexCount;
    }

    public void setVertexCount(final int count) {
        this.vertexCount = count;
    }

    public VertexDataModel getVertexDataModel() {
        return this.vertexDataModel;
    }

    public void setVertexDataModel(final VertexDataModel vertexDataModel) {
        this.vertexDataModel = vertexDataModel;
    }

}
