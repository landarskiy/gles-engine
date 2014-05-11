package by.vsu.mf.simplegles20.core.object;

import by.vsu.mf.meshloader.Mesh;

public class BaseMeshObject extends DirectedAbstractObject {
	protected Mesh mesh;

	public Mesh getMesh() {
		return mesh;
	}

	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}

}
