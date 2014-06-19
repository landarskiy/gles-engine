package by.vsu.mf.rendering.object;

public enum VertexDataType {

    FLOAT(4),
    INTEGER(4),
    BYTE(1),
    SHORT(2);

    private final int sizeInBytes;

    private VertexDataType(final int sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public int getSizeInBytes() {
        return this.sizeInBytes;
    }

}
