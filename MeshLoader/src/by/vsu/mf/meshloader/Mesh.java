package by.vsu.mf.meshloader;

import java.util.ArrayList;
import java.util.List;

public class Mesh {
	protected List<float[]> vertexCoordinates;
	protected List<float[]> textureCoordinates;
	protected List<float[]> normalCoordinates;
	
	protected List<int[]> vertexFaces;
	protected List<int[]> textureFaces;
	protected List<int[]> normalFaces;
	
	public Mesh() {
		this.vertexCoordinates = new ArrayList<float[]>();
		this.textureCoordinates = new ArrayList<float[]>();
		this.normalCoordinates = new ArrayList<float[]>();
		
		this.vertexFaces = new ArrayList<int[]>();
		this.textureFaces = new ArrayList<int[]>();
		this.normalFaces = new ArrayList<int[]>();				
	}
	
	public int getFaceCount() {
		return vertexFaces.size();
	}
	
	public int getVertexCoordCount() {
		return vertexCoordinates.size();
	}
	
	public int getTextureCoordCount() {
		return textureCoordinates.size();
	}
	
	public int getNormalCoordCount() {
		return normalCoordinates.size();
	}
	
	public int[] getVertexFace(int faceNumber) {
		return vertexFaces.get(faceNumber);
	}
	
	public int[] getTextureFace(int faceNumber) {
		return textureFaces.get(faceNumber);
	}
	
	public int[] getNarmalFace(int faceNumber) {
		return normalFaces.get(faceNumber);
	}
	
	public float[] getVertexCoordinate(int faceItem) {
		return vertexCoordinates.get(faceItem);
	}
	
	public float[] getTextureCoordinate(int faceItem) {
		return textureCoordinates.get(faceItem);
	}
	
	public float[] getNormalCoordinate(int faceItem) {
		return normalCoordinates.get(faceItem);
	}
	
	public void addVertexCoordinate(float[] coordinate) {
		vertexCoordinates.add(coordinate);
	}
	
	public void addTextureCoordinate(float[] coordinate) {
		textureCoordinates.add(coordinate);
	}
	
	public void addNormalCoordinate(float[] coordinate) {
		normalCoordinates.add(coordinate);
	}
	
	public void addVertexFace(int[] face) {
		vertexFaces.add(face);
	}
	
	public void addTextureFace(int[] face) {
		textureFaces.add(face);
	}
	
	public void addNormalFace(int[] face) {
		normalFaces.add(face);
	}
}
