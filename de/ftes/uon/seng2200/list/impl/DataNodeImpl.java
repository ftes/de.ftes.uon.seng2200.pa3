package de.ftes.uon.seng2200.list.impl;

public class DataNodeImpl<T> extends BaseDataNode<T, Node<T>> implements Node<T> {
	public DataNodeImpl(T data) {
		super(data);
	}
	
	@Override
	public Node<T> createNode(T data) {
		return new DataNodeImpl<T>(data);
	}
}
