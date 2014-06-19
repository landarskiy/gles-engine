package by.vsu.mf.rendering.object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

// TODO: decide whether to use either classes or some kind of tokens to determine an appropriate renderer with
// compatible vertex model or embed vertex model into geometry objects.
public final class GeometryUtils {

    public static final int BYTES_PER_DWORD = 4;

    private static final VertexDataModel CUBE_VERTEX_MODEL = new VertexDataModel();
    static {
        GeometryUtils.CUBE_VERTEX_MODEL.addAttribute(new VertexAttribute("a_Position", 3));
        GeometryUtils.CUBE_VERTEX_MODEL.addAttribute(new VertexAttribute("a_Color", 4, 12));
        GeometryUtils.CUBE_VERTEX_MODEL.addAttribute(new VertexAttribute("a_Normal", 3, 28));
        GeometryUtils.CUBE_VERTEX_MODEL.setStride(40);
        GeometryUtils.CUBE_VERTEX_MODEL.setType(VertexDataType.FLOAT);
    }

    private static FloatBuffer CUBE_DATA;
    static {
        final float[] vertexes = {
                // Front face
                -1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                -1.0f, -1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                -1.0f, -1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                1.0f, -1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,

                // Right face
                1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
                1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
                1.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
                1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
                1.0f, -1.0f, -1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
                1.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,

                // Back face
                1.0f, 1.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, -1.0f,
                1.0f, -1.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, -1.0f,
                -1.0f, 1.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, -1.0f,
                1.0f, -1.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, -1.0f,
                -1.0f, -1.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, -1.0f,
                -1.0f, 1.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, -1.0f,

                // Left face
                -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 0.0f,
                -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 0.0f,
                -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 0.0f,
                -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 0.0f,
                -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 0.0f,
                -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 0.0f,

                // Top face
                -1.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f,
                -1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f,
                1.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f,
                -1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f,
                1.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f,

                // Bottom face
                1.0f, -1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 0.0f,
                1.0f, -1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 0.0f,
                -1.0f, -1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 0.0f,
                1.0f, -1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 0.0f,
                -1.0f, -1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 0.0f,
                -1.0f, -1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 0.0f
        };

        GeometryUtils.CUBE_DATA = ByteBuffer.allocateDirect(vertexes.length * GeometryUtils.BYTES_PER_DWORD)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        GeometryUtils.CUBE_DATA.put(vertexes);
        GeometryUtils.CUBE_DATA.position(0);
    }

    private static final VertexDataModel QUAD_VERTEX_MODEL = new VertexDataModel();
    static {
        GeometryUtils.QUAD_VERTEX_MODEL.addAttribute(new VertexAttribute("a_Position", 3));
        GeometryUtils.QUAD_VERTEX_MODEL.addAttribute(new VertexAttribute("a_TexCoord0", 2, 12));
        GeometryUtils.QUAD_VERTEX_MODEL.setStride(20);
        GeometryUtils.QUAD_VERTEX_MODEL.setType(VertexDataType.FLOAT);
    }

    private static FloatBuffer QUAD_DATA;
    static {
        final float[] vertexes = {
                -1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                -1.0f, -1.0f, 0.0f, 0.0f, 0.0f,
                1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
                1.0f, -1.0f, 0.0f, 1.0f, 0.0f
        };

        GeometryUtils.QUAD_DATA = ByteBuffer.allocateDirect(vertexes.length * GeometryUtils.BYTES_PER_DWORD)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        GeometryUtils.QUAD_DATA.put(vertexes);
        GeometryUtils.QUAD_DATA.position(0);
    }

    private GeometryUtils() {
    }

    public static Geometry createCube() {
        final Geometry geometry = new Geometry();
        geometry.setPrimitiveType(GeometryPrimitiveType.TRIANGLE);
        geometry.setData(GeometryUtils.CUBE_DATA);
        // FIXME: this is not primitive count but this is vertex count!
        geometry.setVertexCount(36);
        geometry.setVertexDataModel(GeometryUtils.CUBE_VERTEX_MODEL);

        return geometry;
    }

    public static Geometry createQuad() {
        final Geometry geometry = new Geometry();
        geometry.setPrimitiveType(GeometryPrimitiveType.TRIANGLE_STRIP);
        geometry.setData(GeometryUtils.QUAD_DATA);
        // FIXME: this is not primitive count but this is vertex count!
        geometry.setVertexCount(4);
        geometry.setVertexDataModel(GeometryUtils.QUAD_VERTEX_MODEL);

        return geometry;
    }

}
