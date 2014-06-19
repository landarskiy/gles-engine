package by.vsu.mf.simplegles20.sample;

import android.opengl.Matrix;
import by.vsu.mf.simplegles20.core.object.DirectedAbstractObject;
import by.vsu.mf.simplegles20.core.object.ObjectTree;
import by.vsu.mf.simplegles20.core.util.Point3D;

public class ObjectTreeSample {
	public void run() {
		float m[] = {0, 0, 0, 0};
        
        DirectedAbstractObject root = new DirectedAbstractObject();
        Point3D direction = new Point3D(1, 1, 0);
        Point3D directionUp = new Point3D(0, 0, 1);
        Point3D position = new Point3D(1, 1, 0);
        root.setDirection(direction);
        root.setDirectionUp(directionUp);
        root.setPosition(position);
        
        
        ObjectTree tree = new ObjectTree();
        tree.add(null, root);
        Point3D pt = new Point3D(1, 1, 0);
        Matrix.multiplyMV(m, 0, tree.getGlobalModelMatrix(root), 0, pt.toGomogenFloatArray(), 0);        
        
        
        DirectedAbstractObject child1 = new DirectedAbstractObject();
        direction = new Point3D(1, 1, 0);
        directionUp = new Point3D(0, 0, 1);
        position = new Point3D(1, 1, 0);
        child1.setDirection(direction);
        child1.setDirectionUp(directionUp);
        child1.setPosition(position); 
        child1.updateModelMatrix();
        
        tree.add(root, child1);
        tree.updateGlobalModelMatrix(root);
        pt = new Point3D(1, -1, 0);
        Matrix.multiplyMV(m, 0, tree.getGlobalModelMatrix(child1), 0, pt.toGomogenFloatArray(), 0);                 
	}
}
