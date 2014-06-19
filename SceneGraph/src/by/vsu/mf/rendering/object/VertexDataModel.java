package by.vsu.mf.rendering.object;

import java.util.ArrayList;
import java.util.List;

public class VertexDataModel {

    private List<VertexAttribute> attributes = new ArrayList<>();
    private int stride;
    private VertexDataType type;
    private String id;

    public void setType(final VertexDataType type) {
        this.type = type;
    }

    public VertexDataType getType() {
        return this.type;
    }

    public List<VertexAttribute> getAttributes() {
        return this.attributes;
    }

    public void addAttribute(final VertexAttribute attribute) {
        this.attributes.add(attribute);
    }

    public void clearAttributes() {
        this.attributes.clear();
    }

    public int getStride() {
        return this.stride;
    }

    public void setStride(final int stride) {
        this.stride = stride;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

}
