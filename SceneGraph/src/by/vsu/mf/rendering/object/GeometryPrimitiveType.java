package by.vsu.mf.rendering.object;

public enum GeometryPrimitiveType {

    POINT(1),
    TRIANGLE(3),
    TRIANGLE_STRIP(3);

    private final int vertexCount;

    private GeometryPrimitiveType(final int vertexCount) {
        this.vertexCount = vertexCount;
    }

    public int getVertexCount() {
        return this.vertexCount;
    }

}
