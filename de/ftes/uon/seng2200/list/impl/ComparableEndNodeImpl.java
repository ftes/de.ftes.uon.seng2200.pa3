package de.ftes.uon.seng2200.list.impl;

public class ComparableEndNodeImpl<T extends Comparable<T>>
		extends BaseEndNode<T, ComparableNode<T>> implements ComparableNode<T> {
	public ComparableEndNodeImpl() {
		super(new ComparableDataNodeImpl<T>(null));
	}
	
	@Override
	/**
	 * If {@code toInsert} was not yet inserted, it has to go at the end of the list,
	 * so before this sentinel node.
	 */
	public void insertInOrder(T toInsert) {
		insertBefore(toInsert);
	}
}
