package by.vsu.mf.rendering.opengl.api;

import by.vsu.mf.rendering.object.GeometryPrimitiveType;

public interface DrawAPI {

    void draw(GeometryPrimitiveType type, int first, int count);

}
