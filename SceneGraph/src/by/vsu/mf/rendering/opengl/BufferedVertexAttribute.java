package by.vsu.mf.rendering.opengl;

import by.vsu.mf.rendering.object.VertexAttribute;

public class BufferedVertexAttribute extends VertexAttribute {

    private int offset;

    public void setOffset(final int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return this.offset;
    }

}
