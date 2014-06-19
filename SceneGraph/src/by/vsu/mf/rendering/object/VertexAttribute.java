package by.vsu.mf.rendering.object;

public class VertexAttribute {

    private String name;
    private int dimension;
    private int offset;

    public VertexAttribute() {
    }

    public VertexAttribute(final String name, final int dimension) {
        this(name, dimension, 0);
    }

    public VertexAttribute(final String name, final int dimension, final int offset) {
        this.name = name;
        this.dimension = dimension;
        this.offset = offset;
    }

    public VertexAttribute(final VertexAttribute attribute) {
        this(attribute.name, attribute.dimension);
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getDimension() {
        return this.dimension;
    }

    public void setDimension(final int dimension) {
        this.dimension = dimension;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(final int offset) {
        this.offset = offset;
    }

}
