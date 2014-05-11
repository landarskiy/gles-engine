package by.vsu.mf.simplegles20.core.object;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import by.vsu.mf.simplegles20.core.util.Matrix;
import by.vsu.mf.simplegles20.core.util.Point3D;

public class ObjectTree {
	
	private static Node STUB_ROOT_NODE;
	
	static  {
		STUB_ROOT_NODE = new Node(new DirectedAbstractObject());
		Matrix.setIdentityM(STUB_ROOT_NODE.globalRotationMatrix, 0);
		Matrix.setIdentityM(STUB_ROOT_NODE.globalTransformationMatrix, 0);		
	}
	
	private Map<DirectedAbstractObject, Node> nodes;
	
	public ObjectTree() {
		this.nodes = new HashMap<DirectedAbstractObject, Node>();
		this.nodes.put(null, STUB_ROOT_NODE);
	}
	
	public void add(DirectedAbstractObject parent, DirectedAbstractObject child) {
		Node node = new Node(child);
		nodes.put(child, node);
		Node parentNode = nodes.get(parent);
		parentNode.addChild(node);
	}
	
	public void remove(DirectedAbstractObject object) {
		nodes.remove(object);
	}
	
	public DirectedAbstractObject getParent(DirectedAbstractObject child) {
		return nodes.get(child).object;
	}
	
	public Set<DirectedAbstractObject> getChilds(DirectedAbstractObject parent) {
		return nodes.get(parent).childObjects;
	}
	
	public float[] getGlobalModelMatrix(DirectedAbstractObject object) {
		return nodes.get(object).globalTransformationMatrix;
	}
	/*public float[] getGlobalModelMatrix(DirectedAbstractObject object) {
		float[] translateMatrix = new float[16];
		float[] rotateMatrix = new float[16];
		float[] transformationMatrix = new float[16];
		float[] transofmationPoint = new float[4];
		
		Matrix.setIdentityM(translateMatrix, 0);
		Matrix.setIdentityM(rotateMatrix, 0);
		Matrix.setIdentityM(transformationMatrix, 0);	
				
		Node parent = nodes.get(object);
		Stack<Node> nodes = new Stack<Node>();
		do {
			nodes.push(parent);
			parent = parent.parent;
		} while (parent != null);
		
		while(!nodes.isEmpty()) {
			Node node = nodes.pop();
			
			Matrix.multiplyMV(transofmationPoint, 0, 
					transformationMatrix, 0, 
					node.object.position.toGomogenFloatArray(), 0);
			node.globalPosition.fromFloatArray(transofmationPoint);
			Matrix.multiplyMV(transofmationPoint, 0, 
					rotateMatrix, 0, 
					node.object.direction.toGomogenFloatArray(), 0);
			node.globalDirection.fromFloatArray(transofmationPoint);
			Matrix.multiplyMV(transofmationPoint, 0, 
					rotateMatrix, 0, 
					node.object.directionUp.toGomogenFloatArray(), 0);
			node.globalDirectionUp.fromFloatArray(transofmationPoint);
			
			Matrix.setIdentityM(translateMatrix, 0);
			Matrix.setIdentityM(rotateMatrix, 0);
			
			Matrix.translateM(translateMatrix, 0, 
					node.globalPosition.x, 
					node.globalPosition.y, 
					node.globalPosition.z);
			Matrix.setRotateM(rotateMatrix, node.globalDirection.toGomogenFloatArray(), 
					node.globalDirectionUp.toGomogenFloatArray());
			Matrix.multiplyMM(transformationMatrix, 0, 
					translateMatrix, 0, 
					rotateMatrix, 0);
		}
		
		return transformationMatrix;		
	}*/
	
	public void updateGlobalModelMatrix(DirectedAbstractObject updateModel) {
		Node updateNode = nodes.get(updateModel);
		float[] translateMatrix = new float[16];
		float[] transofmationPoint = new float[4];
		
		List<Node> parents = new LinkedList<Node>();
		List<Node> childs = new LinkedList<Node>();
		List<Node> swap;
		
		parents.add(updateNode);		
		
		while(!parents.isEmpty()) {
			for(Node node : parents) {
				childs.addAll(node.childNodes);
				Matrix.multiplyMV(transofmationPoint, 0, 
						node.parent.globalTransformationMatrix, 0, 
						node.object.position.toGomogenFloatArray(), 0);
				node.globalPosition.fromFloatArray(transofmationPoint);
				
				Matrix.multiplyMV(transofmationPoint, 0, 
						node.parent.globalRotationMatrix, 0, 
						node.object.direction.toGomogenFloatArray(), 0);
				node.globalDirection.fromFloatArray(transofmationPoint);
				
				Matrix.multiplyMV(transofmationPoint, 0, 
						node.parent.globalRotationMatrix, 0, 
						node.object.directionUp.toGomogenFloatArray(), 0);
				node.globalDirectionUp.fromFloatArray(transofmationPoint);
				
				Matrix.setIdentityM(translateMatrix, 0);
				Matrix.setIdentityM(node.globalRotationMatrix, 0);
				
				Matrix.translateM(translateMatrix, 0, 
						node.globalPosition.x, 
						node.globalPosition.y, 
						node.globalPosition.z);
				Matrix.setRotateM(node.globalRotationMatrix, node.globalDirection.toGomogenFloatArray(), 
						node.globalDirectionUp.toGomogenFloatArray());
				Matrix.multiplyMM(node.globalTransformationMatrix, 0, 
						translateMatrix, 0, 
						node.globalRotationMatrix, 0);
			}			
			parents.clear();
			swap = parents;
			parents = childs;
			childs = swap;						
		}
	}
	
	
}

class Node {
	Node parent;
	Set<Node> childNodes;
	Set<DirectedAbstractObject> childObjects;
	DirectedAbstractObject object;
	
	Point3D globalPosition;
	Point3D globalDirection;
	Point3D globalDirectionUp;
	
	float[] globalTransformationMatrix;
	float[] globalRotationMatrix;
	
	public Node(DirectedAbstractObject object) {
		this.object = object;
		this.childNodes = new HashSet<Node>();
		this.childObjects = new HashSet<DirectedAbstractObject>();
		
		globalPosition = new Point3D();
		globalDirection = new Point3D();
		globalDirectionUp = new Point3D();
		
		globalRotationMatrix = new float[16];
		globalTransformationMatrix = new float[16];
	}
	
	public void addChild(Node child) {
		child.parent = this;
		childNodes.add(child);
		childObjects.add(child.object);
	}
	
	public void removeChild(Node child) {
		childNodes.remove(child);
		childObjects.remove(child.object);
	}
}
