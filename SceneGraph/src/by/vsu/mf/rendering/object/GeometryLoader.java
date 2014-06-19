package by.vsu.mf.rendering.object;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import by.vsu.mf.meshloader.Mesh;
import by.vsu.mf.meshloader.MeshLoader;
import by.vsu.mf.meshloader.ObjMeshLoader;

public class GeometryLoader {

    /**
     * Это объект, описывающий, какие атрибуты используются для каждой вершины геометрической сущности.
     * Renderer сам разберется с ее применением при отрисовке, если правильно указать хар-ки всех атрибутов.
     */
    private static final VertexDataModel QUAD_VERTEX_MODEL = new VertexDataModel();
    static {
        // Атрибут, имеющий в шейдере имя a_Position. Количество компонент атрибута - 3 (вектор).
        GeometryLoader.QUAD_VERTEX_MODEL.addAttribute(new VertexAttribute("a_Position", 3));
        // Атрибут с шейдерным именем a_Normal. Размер в байтах - 3. Т.к. предыдущий атрибут - 3-мерный вектор с
        // компонентами по 4 байта (float, см. ниже), то смещение для очередного атрибута - 3 * 4 = 12.
        GeometryLoader.QUAD_VERTEX_MODEL.addAttribute(new VertexAttribute("a_Texture", 2, 3*4));
        
        GeometryLoader.QUAD_VERTEX_MODEL.addAttribute(new VertexAttribute("a_Normal", 3, 3*4 + 2*4));        
        // Шаг в байтах для одной вершины - (3 * 4) + (3 * 4) = 24.
        GeometryLoader.QUAD_VERTEX_MODEL.setStride((3 * 4) + (3 * 4) + (2*4));
        // Тип данных для компонент всей вершинной модели одинаков. В данном случае - FLOAT.
        GeometryLoader.QUAD_VERTEX_MODEL.setType(VertexDataType.FLOAT);
    }

    public Geometry load(final String modelURI) {
    	
        Geometry geometry = new Geometry();        
        try {
        	final InputStream in = new FileInputStream(modelURI);
        	geometry = load(in);
        	in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return geometry;
    }
    
    public Geometry load(final InputStream in) {
    	MeshLoader loader = new ObjMeshLoader();
    	final Geometry geometry = new Geometry();
    	try {
			Mesh mesh = loader.load(in);
			float[] data = toArray(mesh);
			FloatBuffer buffer = ByteBuffer.allocateDirect(data.length * GeometryUtils.BYTES_PER_DWORD)
					.order(ByteOrder.nativeOrder()).asFloatBuffer();
			buffer.put(data);
			buffer.position(0);
			// Способ обхода вершин при трисовке.
	        geometry.setPrimitiveType(GeometryPrimitiveType.TRIANGLE_STRIP);
	        // Буфер вершинных данных.
	        geometry.setData(buffer);
	        // Количество вершин в экземпляре геометрического объекта.
	        geometry.setVertexCount(mesh.getFaceCount()*3);
	        // Вершинная модель.
	        geometry.setVertexDataModel(GeometryLoader.QUAD_VERTEX_MODEL);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return geometry;
    }
    
    // XXX Копирование данных из меша в массив
    private float[] toArray(Mesh mesh) {
    	int vertexBufferSize = mesh.getFaceCount()*3*3;
    	int textureBufferSize = mesh.getFaceCount()*3*2;
    	int normalBufferSize = mesh.getFaceCount()*3*3;
    	
    	float[] data = new float[vertexBufferSize + textureBufferSize + normalBufferSize];
    	
		int index = 0;
		for(int i=0;i<mesh.getFaceCount();i++) {
			int[] face = mesh.getVertexFace(i);
			int[] textFace = mesh.getTextureFace(i);
			int[] normFace = mesh.getNarmalFace(i);
			
			for(int j=0;j<face.length;j++) {
				float[] vertexPoint = mesh.getVertexCoordinate(face[j]);
				for(int k = 0 ; k<vertexPoint.length; k++) {
					data[index++] = vertexPoint[k];
				}
				float[] textPoint = mesh.getTextureCoordinate(textFace[j]);
				for(int k = 0 ; k<textPoint.length; k++) {
					data[index++] = textPoint[k];
				}
				float[] normPoint = mesh.getNormalCoordinate(normFace[j]);
				for(int k = 0 ; k<normPoint.length; k++) {
					data[index++] = normPoint[k];
				}
			}							
		}
		return data;				
    }

}
