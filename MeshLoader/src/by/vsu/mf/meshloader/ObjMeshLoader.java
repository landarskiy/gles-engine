package by.vsu.mf.meshloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Stack;

public class ObjMeshLoader implements MeshLoader {

	private enum LineType {
		VERTEX, TEXTURE, NORMAL, FACE, UNDEFINED
	}

	private final static String PREFIX_VERTEX = "v ";
	private final static String PREFIX_TEXTURE = "vt ";
	private final static String PREFIX_NORMAL = "vn ";
	private final static String PREFIX_FACE = "f ";
	
	private Stack<Integer> elements = new Stack<Integer>();

	@Override
	public Mesh load(String fileName) throws IOException {
		return load(new File(fileName));
	}

	@Override
	public Mesh load(File file) throws IOException {
		return load(new FileInputStream(file));
	}

	@Override
	public Mesh load(InputStream stream) throws IOException {
		Mesh mesh = new Mesh();
		Scanner sc = new Scanner(stream);
		String line;
		while (sc.hasNext()) {
			line = sc.nextLine();
			switch (getLineType(line)) {
			case FACE:
				parseFace(line.substring(line.indexOf(" ")+1), mesh);
				break;
			case VERTEX:
				mesh.addVertexCoordinate(parse3DFloatPoint(line.substring(line.indexOf(" ")+1)));
				break;
			case TEXTURE:
				mesh.addTextureCoordinate(parse2DFloatPoint(line.substring(line.indexOf(" ")+1)));
				break;
			case NORMAL:
				mesh.addNormalCoordinate(parse3DFloatPoint(line.substring(line.indexOf(" ")+1)));
				break;
			}
		}
		sc.close();
		return mesh;
	}

	private LineType getLineType(String line) {
		if (line.startsWith(PREFIX_FACE)) {
			return LineType.FACE;
		}
		if (line.startsWith(PREFIX_VERTEX)) {
			return LineType.VERTEX;
		}
		if (line.startsWith(PREFIX_TEXTURE)) {
			return LineType.TEXTURE;
		}
		if (line.startsWith(PREFIX_NORMAL)) {
			return LineType.NORMAL;
		}
		return LineType.UNDEFINED;
	}

	private float[] parse2DFloatPoint(final String text) {
		float[] result = new float[2];
		parseFloatPoint(text, result);
		return result;
	}
	
	private float[] parse3DFloatPoint(final String text) {
		float[] result = new float[3];
		parseFloatPoint(text, result);
		return result;
	}
	
	private float[] parseFloatPoint(final String text, final float[] result) {
		int index = 0;

		int startIndex = 0;
		int endIndex = text.indexOf(' ', startIndex);
		while (endIndex >= 0) {
			String token = text.substring(startIndex, endIndex);
			result[index++] = Float.parseFloat(token);
			startIndex = endIndex + 1;
			endIndex = text.indexOf(' ', startIndex);
		}

		if (startIndex < text.length() - 1) {
			result[index++] = Float.parseFloat(text.substring(startIndex));
		}

		return result;
	}
	
	private void parseFace(String line, Mesh mesh) {
		elements.clear();
		int start = 0;
		char[] lineBuf = new char[line.length()];
		line.getChars(0, line.length(), lineBuf, 0);
		int splits = 0;
		for(int i=0;i<lineBuf.length;i++) {
			if(lineBuf[i] == '/') {
				splits++;
			}
			if(lineBuf[i] == '/' ||
					lineBuf[i] == ' ') {
				if(i == start) {
					start = i+1;
					continue;
				}
				elements.push(Integer.parseInt(line.substring(start, i))-1);
				start = i+1;
				lineBuf[i] = ' ';
			}
		}
		elements.push(Integer.parseInt(line.substring(start))-1);
		
		splits /= 3;
		int[] vertex;
		int[] texture;
		int[] normal;
		switch(splits) {
		case 0:
			vertex = new int[3];
			for(int i=2; i>=0; i--) {
				vertex[i] = elements.pop();
			}
			mesh.addVertexFace(vertex);
			break;
		case 1:
			texture = new int[3];
			vertex = new int[3];
			for(int i=2; i>=0; i--) {
				texture[i] = elements.pop();
				vertex[i] = elements.pop();
			}
			mesh.addTextureFace(texture);									
			mesh.addVertexFace(vertex);			
			break;
		case 2:
			normal = new int[3];
			texture = new int[3];
			vertex = new int[3];
			boolean containsTexture = elements.size() == 9;
			for(int i=2; i>=0; i--) {
				normal[i] = elements.pop();
				if(containsTexture) {
					texture[i] = elements.pop();
				}
				vertex[i] = elements.pop();
			}
			mesh.addNormalFace(normal);
			if(containsTexture) {
				mesh.addTextureFace(texture);
			}
			mesh.addVertexFace(vertex);	
			break;
		}
	}

}
