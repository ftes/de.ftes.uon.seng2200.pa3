package de.ftes.uon.seng2200.list.impl;

public class ComparableStartNodeImpl<T extends Comparable<T>> extends
		BaseStartNode<T, ComparableNode<T>> implements ComparableNode<T> {
	public ComparableStartNodeImpl() {
		super(new ComparableDataNodeImpl<T>(null));
	}

	@Override
	public void insertInOrder(T toInsert) {
		wrappedNode.insertInOrder(toInsert);
	}

}
