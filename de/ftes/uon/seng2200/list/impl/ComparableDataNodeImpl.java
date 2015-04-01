package de.ftes.uon.seng2200.list.impl;

class ComparableDataNodeImpl<T extends Comparable<T>> extends
		BaseDataNode<T, ComparableNode<T>> implements
		ComparableNode<T> {
	ComparableDataNodeImpl(T data) {
		super(data);
	}

	@Override
	public ComparableNode<T> createNode(T data) {
		return new ComparableDataNodeImpl<T>(data);
	}

	@Override
	public void insertInOrder(T toInsert) {
		if ((getData()).compareTo(toInsert) < 0) {
			getNext().insertInOrder(toInsert);
		} else {
			insertBefore(toInsert);
		}
	}
}
