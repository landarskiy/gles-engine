package by.vsu.mf.rendering;

// TODO: try to eliminate direct access to the float arrays.
public interface ViewSpace {

    float[] getProjectionMatrix();

    float[] getViewMatrix();

}
