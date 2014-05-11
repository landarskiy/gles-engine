package by.vsu.mf.simplegles20.core.util;

import java.util.LinkedList;

public class ObjectPool<T extends StoredObject> extends LinkedList<T>{
	
	private static final long serialVersionUID = 5175919068131890044L;

	public final static int DEFAULT_POOL_SIZE = 20;
	
	private int poolSize;
	
	public ObjectPool() {
		this(DEFAULT_POOL_SIZE);
	}

	public ObjectPool(int poolSize) {
		super();
		this.poolSize = poolSize;
	}

	@Override
	public boolean add(T object) {
		if(isFull()) {
			return false;
		}
		return super.add(object);
	}

	@Override
	public void addFirst(T object) {
		if(isFull()) {
			return;
		}
		super.addFirst(object);
	}

	@Override
	public void addLast(T object) {
		if(isFull()) {
			return;
		}
		super.addLast(object);
	}		
	
	public boolean isFull() {
		return size() >= poolSize;
	}
	
	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}
}
