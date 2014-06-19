package by.vsu.mf.rendering;

public class SimpleViewSpace implements ViewSpace {

    private float[] viewMatrix = new float[16];
    private float[] projectionMatrix = new float[16];

    @Override
    public float[] getProjectionMatrix() {
        return this.projectionMatrix;
    }

    @Override
    public float[] getViewMatrix() {
        return this.viewMatrix;
    }

}
